package com.textTI.storeManagement.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
		if(client.getId() != null)
			this.clientManager.update(client);
		else
			this.clientManager.insert(client);
		
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
