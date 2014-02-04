package com.textTI.storeManagement.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.textTI.storeManagement.manager.ClientTypeManager;
import com.textTI.storeManagement.model.ClientType;

@Controller
@RequestMapping(value="/clientType")
public class ClientTypeController extends BaseController{
	
	@Autowired
	private ClientTypeManager clientTypeManager;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		logger.info("Accessed the client type list view", locale);
		
		List<ClientType> cliTypes = this.clientTypeManager.getAll();

		model.addAttribute("cliTypes", cliTypes);
		
		return "clientType/list";
	}
	
	@RequestMapping(value = "/newClientType", method = RequestMethod.GET)
	public String newClientType(Locale locale, Model model) {
		logger.info("Accessed the new client type view", locale);
		
		model.addAttribute("clientType", new ClientType());
		
		return "clientType/newClientType";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("clientType") ClientType cliType, HttpServletRequest request)
	{
		logger.info("Creating a Client Type");

		//TODO validate Client Type 
		
		if(cliType.getId() != null)
			this.clientTypeManager.update(cliType);
		else
			this.clientTypeManager.insert(cliType);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id, Model model)
	{
		ClientType cliType = this.clientTypeManager.getById(id);
		
		model.addAttribute("clientType", cliType);
		
		return "clientType/editClientType";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id)
	{
		ClientType clientType = this.clientTypeManager.getById(id);
		this.clientTypeManager.delete(clientType);
		
		return "redirect:/clientType/list";
	}
}
