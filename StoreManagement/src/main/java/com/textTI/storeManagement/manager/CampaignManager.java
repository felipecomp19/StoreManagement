package com.textTI.storeManagement.manager;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.CampaignDAO;
import com.textTI.storeManagement.file.CustomFileReader;
import com.textTI.storeManagement.file.CustomFileWriter;
import com.textTI.storeManagement.file.strategy.TXTStrategy;
import com.textTI.storeManagement.model.Campaign;
import com.textTI.storeManagement.model.Status;
import com.textTI.storeManagement.model.constants.SatusConstants;

@Component
public class CampaignManager {
	
	//private final String filePath = "/home/felipe/app/jboss-6.1.0.Final/server/default/deploy/ROOT.war/sm/morana/emailTemplate/"; //desenv
	private final String filePath = "/usr/share/jboss-6.1.0.Final/server/default/deploy/ROOT.war/sm/morana/emailTemplate/"; //production
	
	protected static final Logger logger = LoggerFactory.getLogger(CampaignManager.class);
	
	@Autowired
	private MailManager mailManager;
	
	@Autowired
	private CampaignDAO campaignDAO;
	
	@Autowired
	private TaskScheduler taskScheduler;

	public void insert(Campaign newCampaign) 
	{
		newCampaign.setCreatedOn(new Date());
		this.campaignDAO.insert(newCampaign);
		
		String fileName = "emailTemplate" + newCampaign.getId() + ".html";
		newCampaign.setEmailFileName(fileName);
		
		String fullPath = this.filePath + fileName;
		
		this.campaignDAO.update(newCampaign);
		
		CustomFileWriter fileWriter = new CustomFileWriter(new File(fullPath));
		fileWriter.write(newCampaign.getEmailContent());
	}
	
	public void delete(Campaign campaign)
	{
		this.campaignDAO.delete(campaign);
	}

	public Campaign getById(Long id) 
	{
		Campaign campaign = this.campaignDAO.getById(id);
		
		String fullPath = this.filePath + campaign.getEmailFileName();
		
		CustomFileReader fileReader = new CustomFileReader(new File(fullPath), new TXTStrategy());
		campaign.setEmailContent(fileReader.readSingleFile());
		
		return campaign;
	}

	public void update(Campaign newCampaign) 
	{
		this.campaignDAO.update(newCampaign);
		
		String fullPath = this.filePath + newCampaign.getEmailFileName();
		
		CustomFileWriter fileWriter = new CustomFileWriter(new File(fullPath));
		fileWriter.write(newCampaign.getEmailContent());
	}
	
	public List<Campaign> getAll()
	{
		return this.campaignDAO.getAll();
	}

	public void submit(Campaign campaign) throws MessagingException {
		//TODO remover
		//campaign.getMailingList().setDefaultFromEmail("morana@moranavale.com.br");
		//campaign.getMailingList().setDefaultFromName("Morana@moranavale.com.br");
		
		if (campaign.getMailingList().getClientsListSize() > 3) {
			Calendar todayCal = Calendar.getInstance();
			todayCal.set(todayCal.get(Calendar.YEAR), todayCal.get(Calendar.MONTH), todayCal.get(Calendar.DATE), 00, 1);
			todayCal.add(Calendar.DATE, 1);
			Date startTime = todayCal.getTime();
			
			taskScheduler.schedule(new AsyncMailSender(campaign.getId()), startTime);
			
			Status status = new Status();
			status.setId(SatusConstants.CAMPAIGN_SCHEDULED);
			this.update(campaign);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			logger.info("**scheduled to: " + dateFormat.format(startTime));
		}else{
			this.mailManager.sendHTMLMail(campaign);

			Status status = new Status();
			status.setId(SatusConstants.CAMPAIGN_SUBMITTED);
			campaign.setStatus(status);
			
			//TODO remover
			campaign.setSubmitted(true);
			campaign.setSubmittedDate(new Date());
			
			this.update(campaign);
		}
	}
	
	private class AsyncMailSender implements Runnable {
		private long campaignId;

		public AsyncMailSender(long campaignId) {
			this.campaignId = campaignId;
		}

		@Override
		public void run() {
			logger.info("**start** Sending campaign e-mails. ID: " + campaignId);
			Campaign campaign = getById(this.campaignId);
			
			Status status = new Status();
			status.setId(SatusConstants.CAMPAIGN_PROCESSING);
			update(campaign);

			try {
				mailManager.sendHTMLMail(campaign);
				
				//TODO remover
				campaign.setSubmitted(true);
				campaign.setSubmittedDate(new Date());
				
				status.setId(SatusConstants.CAMPAIGN_SUBMITTED);
				campaign.setStatus(status);
			} catch (MessagingException e) {
				status.setId(SatusConstants.ERROR);
				campaign.setStatus(status);
				logger.error("Error sending async emails: " + e.getMessage());
				e.printStackTrace();
			}finally
			{
				update(campaign);
				logger.info("**fineshed** Sending campaign e-mails. ID: " + campaignId + " with" + campaign.getMailingList().getClients().size() + " clients.");
			}
		}
	}

	public TaskScheduler getTaskScheduler() {
		return taskScheduler;
	}

	public void setTaskScheduler(TaskScheduler taskScheduler) {
		this.taskScheduler = taskScheduler;
	}
}
