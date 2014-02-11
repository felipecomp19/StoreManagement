package com.textTI.storeManagement.manager;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.CampaignDAO;
import com.textTI.storeManagement.file.CustomFileReader;
import com.textTI.storeManagement.file.CustomFileWriter;
import com.textTI.storeManagement.file.strategy.TXTStrategy;
import com.textTI.storeManagement.model.Campaign;

@Component
public class CampaignManager {
	
	private final String filePath = "/home/felipe/workspace/StoreManagement/StoreManagement/src/main/webapp/upload/emailTemplate/";
	
	@Autowired
	private CampaignDAO campaignDAO;

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
}
