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

import com.textTI.storeManagement.manager.StoreManager;
import com.textTI.storeManagement.model.Store;

@Controller
@RequestMapping(value="/store")
public class StoreController extends BaseController{
	
	@Autowired
	private StoreManager storeManager;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		logger.info("Accessed the stores list view", locale);
		
		List<Store> stores = this.storeManager.getAll();

		int totalOfStores = stores.size();
		
		model.addAttribute("totalOfStores", totalOfStores );
		model.addAttribute("stores", stores);
		
		return "store/list";
	}
	
	@RequestMapping(value = "/newStore", method = RequestMethod.GET)
	public String newStore(Locale locale, Model model) {
		logger.info("Accessed the new store view", locale);
		
		model.addAttribute("store", new Store());
		
		return "store/newStore";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("store") Store store, HttpServletRequest request)
	{
		logger.info("Creating a store");

		//TODO validate STORE 
		
		if(store.getId() != null)
			this.storeManager.update(store);
		else
			this.storeManager.insert(store);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id, Model model)
	{
		Store store = this.storeManager.getById(id);
		
		model.addAttribute("store", store);
		
		return "store/editStore";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id)
	{
		Store store = this.storeManager.getById(id);
		this.storeManager.delete(store);
		
		return "redirect:/store/list";
	}
}
