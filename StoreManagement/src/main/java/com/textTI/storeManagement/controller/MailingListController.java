package com.textTI.storeManagement.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.textTI.storeManagement.form.MailingListForm;
import com.textTI.storeManagement.manager.ClientManager;
import com.textTI.storeManagement.manager.ClientTypeManager;
import com.textTI.storeManagement.manager.MailingListManager;
import com.textTI.storeManagement.manager.StoreManager;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.ClientType;
import com.textTI.storeManagement.model.MailingList;
import com.textTI.storeManagement.model.Store;
import com.textTI.storeManagement.utils.SelectListUtils;

@Controller
@RequestMapping(value="/mailingList")
public class MailingListController extends BaseController {
	
	@Autowired
	private MailingListManager mlManager;
	
	@Autowired
	private ClientManager cliManager;
	
	@Autowired
	private ClientTypeManager cliTypeManager;
	
	@Autowired
	private StoreManager storeManager;
	
	@ModelAttribute("clientTypes")
	public List<ClientType> populateClientTypes()
	{
		return this.cliTypeManager.getAll();
	}
	
	@ModelAttribute("stores")
	public List<Store> populateStores(HttpServletRequest request)
	{
		return this.storeManager.getAllByUser(this.getLoggedUser(request));
	}
	
	@ModelAttribute("days")
	public List<Long> populateDays()
	{
		return SelectListUtils.populateWithSequencialNumber(1, 31);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model)
	{
		logger.info("Accessed the mailing list view");
		
		List<MailingList> list = this.mlManager.getAll();

		model.addAttribute("mailingLists", list);
		
		return "/mailingList/list";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request,Model model)
	{
		logger.info("Accesses the create mailing list view");
		
		List<Client> clientsList = this.cliManager.getAllByUser(this.getLoggedUser(request));
		
		MailingListForm mlForm = createMailingListFormModel(clientsList);
		
		model.addAttribute("mlForm", mlForm);
		
		return "mailingList/create";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("mlForm") MailingListForm mlForm, Model model)
	{
		MailingList mailingList = mlForm.getMailingList();
		mailingList.setClients(new ArrayList<Client>());
		for (Client client : mlForm.getAllClients()) {
			if(client.isChecked())
				mailingList.getClients().add(client);
		}
		
		logger.info("Create mailing list");
		if(mailingList.getId() != null && mailingList.getId() > 0)
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
		
		for (Client mlClient : mailingList.getClients()) {
			for (Client client : clientsList) {
				if(client.getId() == mlClient.getId())
				{
					client.setChecked(true);
					break;
				}
			}
		}
		
		MailingListForm mlForm = new MailingListForm();
		mlForm.setAllClients(clientsList);
		mlForm.setMailingList(mailingList);
		
		model.addAttribute("mlForm", mlForm);
		
		return "mailingList/edit";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id)
	{
		MailingList mailingList = this.mlManager.getById(id);
		this.mlManager.delete(mailingList);
		
		return "redirect:/mailingList/list";
	}
	
	@RequestMapping(value = "/filterByClientType", method = RequestMethod.POST)
	public String filterByClientType(@ModelAttribute MailingList mailingList, Model model,HttpServletRequest request)
	{
		long cliTypeId = Long.parseLong(request.getParameter("cliTypeSL"));
		
		List<Client> clientsList = this.cliManager.getByClientTypeId(cliTypeId, this.getLoggedUser(request));
		
		MailingListForm mlForm = createMailingListFormModel(clientsList, mailingList);
		
		model.addAttribute("mlForm", mlForm);
		
		return "/mailingList/create";
	}
	
	@RequestMapping(value = "/filterByBirthdayMonth", method = RequestMethod.POST)
	public String filterByBirthdayMonth(@ModelAttribute MailingList mailingList, Model model,HttpServletRequest request)
	{
		int month = Integer.parseInt(request.getParameter("monthSL"));
		int dayFrom = Integer.parseInt(request.getParameter("dayFromSL"));
		int dayTo = Integer.parseInt(request.getParameter("dayToSL"));
		
		List<Client> clientsList = this.cliManager.getByClientBirthdayMonth(month, dayFrom, dayTo, this.getLoggedUser(request));
		
		MailingListForm mlForm = createMailingListFormModel(clientsList, mailingList);
		
		model.addAttribute("mlForm", mlForm);
		
		return "/mailingList/create";
	}
	
	@RequestMapping(value = "/filterByStore", method = RequestMethod.POST)
	public String filterByStore(@ModelAttribute MailingList mailingList,Model model,HttpServletRequest request)
	{
		long storeId = Long.parseLong(request.getParameter("storeSL"));
		
		List<Client> clientsList = this.cliManager.getByClientsByStoreId(storeId);
		
		MailingListForm mlForm = createMailingListFormModel(clientsList, mailingList);
		
		model.addAttribute("mlForm", mlForm);
		
		return "/mailingList/create";
	}
	
	private MailingListForm createMailingListFormModel(
			List<Client> clientsList, MailingList mailingList) {
		MailingListForm mlf = this.createMailingListFormModel(clientsList);
		mlf.setMailingList(mailingList);
		
		return mlf;
	}

	private MailingListForm createMailingListFormModel(List<Client> clientsList) {
		MailingList ml = new MailingList();
		MailingListForm mlForm = new MailingListForm();
		mlForm.setAllClients(clientsList);
		mlForm.setMailingList(ml);
		return mlForm;
	}
}
