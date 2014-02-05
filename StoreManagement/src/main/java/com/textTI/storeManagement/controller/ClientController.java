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

import com.textTI.storeManagement.exception.ClientException;
import com.textTI.storeManagement.manager.ClientManager;
import com.textTI.storeManagement.model.Client;

@Controller
@RequestMapping(value="/client")
public class ClientController extends BaseController{
	
	@Autowired
	private ClientManager clientManager;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		logger.info("Accessed the clients list view", locale);
		
		List<Client> clients = this.clientManager.getAll();

		int totalOfClients = clients.size();
		
		model.addAttribute("totalOfClients", totalOfClients );
		model.addAttribute("clients", clients);
		
		return "client/list";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Locale locale, Model model) {
		logger.info("Accessed the create client view", locale);
		
		model.addAttribute("client", new Client());
		
		return "client/create";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("client") Client client, HttpServletRequest request)
	{
		logger.info("Creating a client");

		//TODO validate Client
		try{
		if(client.getId() != null)
			this.clientManager.update(client);
		else
			this.clientManager.insert(client);
		}catch(ClientException ce)
		{
			//TODO create a validator
			logger.error(ce.getMessage());
		}
		
		return "redirect:/client/list";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id, Model model)
	{
		Client client = this.clientManager.getById(id);
		
		model.addAttribute("client", client);
		
		return "client/edit";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id)
	{
		Client client = this.clientManager.getById(id);
		this.clientManager.delete(client);
		
		return "redirect:/client/list";
	}
}
