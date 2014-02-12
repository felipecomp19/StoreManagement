package com.textTI.storeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.textTI.storeManagement.manager.ClientManager;
import com.textTI.storeManagement.manager.MailingListManager;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.MailingList;

@Controller
@RequestMapping(value="/mailingList")
public class MailingListController extends BaseController {
	
	@Autowired
	private MailingListManager mlManager;
	
	@Autowired
	private ClientManager cliManager;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model)
	{
		logger.info("Accessed the mailing list view");
		
		List<MailingList> list = this.mlManager.getAll();

		model.addAttribute("mailingLists", list);
		
		return "/mailingList/list";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model)
	{
		logger.info("Accesses the create mailing list view");
		
		MailingList ml = new MailingList();
		List<Client> clientsList = this.cliManager.getAll();
		
//		ml.setClients(this.cliManager.getAll());
		model.addAttribute("mailingList", ml);
		model.addAttribute("clients", clientsList);
		
		return "mailingList/create";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("mailingList") MailingList mailingList)
	{
		logger.info("Create mailing list");
		if(mailingList.getId() != null)
			this.mlManager.update(mailingList);
		else
			this.mlManager.insert(mailingList);
		
		return "redirect:/mailingList/list";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id, Model model)
	{
		MailingList mailingList = this.mlManager.getById(id);
		List<Client> clientsList = this.cliManager.getAll();
		
		model.addAttribute("clients", clientsList);
		model.addAttribute("mailingList", mailingList);
		
		return "mailingList/edit";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id)
	{
		MailingList mailingList = this.mlManager.getById(id);
		this.mlManager.delete(mailingList);
		
		return "redirect:/mailingList/list";
	}
}
