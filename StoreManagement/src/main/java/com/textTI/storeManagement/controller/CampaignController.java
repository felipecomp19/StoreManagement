package com.textTI.storeManagement.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.textTI.storeManagement.manager.CampaignManager;
import com.textTI.storeManagement.manager.ImagensManager;
import com.textTI.storeManagement.manager.MailManager;
import com.textTI.storeManagement.manager.MailingListManager;
import com.textTI.storeManagement.model.Campaign;
import com.textTI.storeManagement.model.Imagen;
import com.textTI.storeManagement.model.MailingList;
import com.textTI.storeManagement.model.Status;

@Controller
@RequestMapping(value="/campaign")
public class CampaignController extends BaseController {
	
	@Autowired
	private CampaignManager campaignManager;
	
	@Autowired
	private MailManager mailManager;
	
	@Autowired
	private MailingListManager mailingListManager;
	
	@Autowired
	private ImagensManager imgManager;
	
	private final String relativePath = "/storeManager/morana/images/";
	//private final String relativePath = "/storeManager/tutti/images/";
	
	@RequestMapping(value = "/bulding", method = RequestMethod.GET)
	public String bulding()
	{
		return "error/bulding";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model)
	{
		Campaign campaign = new Campaign();
		Status status = new Status();
		campaign.setStatus(status);
		
		model.addAttribute("campaign", campaign);
		
		List<Imagen> imagens = this.imgManager.getAllImagens();
        model.addAttribute("imagens", imagens);
        model.addAttribute("imagensSize", imagens.size());
        model.addAttribute("relativePath",this.relativePath);
		
		return "campaign/create";
	}
	
	@ModelAttribute("mailingLists")
	public List<MailingList> populateMailingLists()
	{
		return this.mailingListManager.getAll();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model)
	{
		List<Campaign> campaigns = this.campaignManager.getAll();
		model.addAttribute("campaigns", campaigns);
		
		return "campaign/list";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id, Model model)
	{
		Campaign campaign = this.campaignManager.getById(id);
		model.addAttribute("campaign", campaign);
		
		return "campaign/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id)
	{
		Campaign campaign = this.campaignManager.getById(id);
		this.campaignManager.delete(campaign);
		
		return "redirect:/campaign/list";
	}
	
	/*@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("campaign") Campaign campaign, HttpServletRequest request)
	{
		if(campaign.getId() != null)
			this.campaignManager.update(campaign);
		else
			this.campaignManager.insert(campaign);
		
		return "redirect:/campaign/list";
	}*/
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String save(@ModelAttribute("campaign") Campaign campaign, HttpServletRequest request)
	{
		Campaign newCampaign = createNewCampaign(request);
		
		if(newCampaign.getId() != null)
			this.campaignManager.update(newCampaign);
		else
			this.campaignManager.insert(newCampaign);
		

		return newCampaign.getIdAsString();
	}

	private Campaign createNewCampaign(HttpServletRequest request) {
		Campaign newCampaign = new Campaign();
		String sId = request.getParameter("id");
		if(sId != null && sId != "")
			newCampaign.setId(Long.valueOf(sId));
		
		newCampaign.setName(request.getParameter("name"));
		newCampaign.setDescription(request.getParameter("description"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			String createdOn = request.getParameter("createdOn");
			if(createdOn == null || createdOn == "")
				newCampaign.setCreatedOn(new Date());
			else
				newCampaign.setCreatedOn(sdf.parse(createdOn));
			
			String submitedDate = request.getParameter("submittedDate");
			if(submitedDate != null && submitedDate != "")
				newCampaign.setSubmittedDate(sdf.parse(submitedDate));
		} catch (ParseException e) {
			logger.error("error parsing string date 'createdOn' or 'submittedDate' value to Date");
		}
		
		newCampaign.setEmailContent(request.getParameter("emailContent"));
		newCampaign.setEmailFileName(request.getParameter("emailFileName"));
		
		MailingList mailingList = new MailingList();
		String smlId = request.getParameter("mailingList");
		if(smlId != null && smlId != "")
			mailingList.setId(Long.valueOf(smlId));
		newCampaign.setMailingList(mailingList);
		
		newCampaign.setSubmitted(Boolean.valueOf(request.getParameter("submitted")));
		Status status = new Status();
		String sStatusId = request.getParameter("statusId");
		if(sStatusId != null && sStatusId != "")
			status.setId(Long.valueOf(sStatusId));
		
		newCampaign.setStatus(status);
		
		newCampaign.setSubject(request.getParameter("subject"));
		
		return newCampaign;
	}
	
	@RequestMapping(value = "/getMailingListById/{id}", method = RequestMethod.GET)
	public @ResponseBody MailingList getMailingListById(@PathVariable("id") long id) {
		MailingList mailingList = this.mailingListManager.getById(id);
		
		return mailingList;
	}
	
	@RequestMapping(value = "/sendEmails", method = RequestMethod.POST)
	public String sendEmails(@ModelAttribute("campaign") Campaign campaign, HttpServletRequest request) 
	{
		long selectedMLId = campaign.getMailingList().getId();
		if(campaign.getId() != null){
			campaign = this.campaignManager.getById(campaign.getId());
			if(selectedMLId != campaign.getMailingList().getId()){
				campaign.setMailingList(this.mailingListManager.getById(selectedMLId));
			}
			
			try {
				this.campaignManager.submit(campaign);
			} catch (MessagingException e) {
				logger.error("Error submiting the campaign: " + e.getMessage());
				e.printStackTrace();
			}
			//this.mailManager.sendHTMLMail(campaign);
		}
		
		return "redirect:/campaign/list";
	}
}
