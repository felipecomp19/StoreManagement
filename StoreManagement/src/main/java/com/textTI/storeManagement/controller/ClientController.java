package com.textTI.storeManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.textTI.storeManagement.exception.ClientException;
import com.textTI.storeManagement.manager.ClientManager;
import com.textTI.storeManagement.manager.ClientTypeManager;
import com.textTI.storeManagement.manager.StoreManager;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.ClientType;
import com.textTI.storeManagement.model.Store;

@Controller
@RequestMapping(value="/client")
public class ClientController extends BaseController{
	
	@Autowired
	private ClientManager clientManager;
	
	@Autowired
	private ClientTypeManager clientTypeManager;
	
	@Autowired
	private StoreManager storeManager;
	
	private Map<String, Store> storeCache;
	
	@ModelAttribute("clientTypes")
	public List<ClientType> populateClientTypes()
	{
		return this.clientTypeManager.getAll();
	}
	
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
		
		populateStores(model);
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
		
		populateStores(model);
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
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Set.class, "stores", new CustomCollectionEditor(Set.class) {
			protected Object convertElement(Object element) {
				if (element instanceof Store) {
					System.out.println("Converting from store to store: " + element);
					return element;
				}
				if (element instanceof String) {
					Store store = storeCache.get(element);
					System.out.println("Looking up store for id " + element + ": " + store);
					return store;
				}
				System.out.println("Don't know what to do with: " + element);
				return null;
			}
		});
	}
	
	private void populateStores(Model model) {
		List<Store> stores = this.storeManager.getAll();
		this.storeCache = new HashMap<String,Store>();
		for (Store store : stores) {
			this.storeCache.put(store.getIdAsString(), store);
		}
		model.addAttribute("stores", stores);
	}
}
