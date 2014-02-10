package com.textTI.storeManagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.textTI.storeManagement.manager.CampaignManager;
import com.textTI.storeManagement.manager.MailingListManager;
import com.textTI.storeManagement.model.Campaign;
import com.textTI.storeManagement.model.MailingList;

@Controller
@RequestMapping(value="/campaign")
public class CampaignController extends BaseController {
	
	@Autowired
	private CampaignManager campaignManager;
	
	@Autowired
	private MailingListManager mailingListManager;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model)
	{
		Campaign campaign = new Campaign();
		model.addAttribute("campaing", campaign);
		
		return "campaign/create";
	}
	
	@ModelAttribute("mailingLists")
	public List<MailingList> populateClientTypes()
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
		model.addAttribute("campaing", campaign);
		
		return "campaign/edit";
	}
	
	@RequestMapping(value = "/celete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id)
	{
		Campaign campaign = this.campaignManager.getById(id);
		this.campaignManager.delete(campaign);
		
		return "redirect:/campaign/list";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("campaign") Campaign campaign, HttpServletRequest request)
	{
		if(campaign.getId() != null)
			this.campaignManager.update(campaign);
		else
			this.campaignManager.insert(campaign);
		
		return "redirect:/campaign/list";
	}
}
