package com.textTI.storeManagement.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.textTI.storeManagement.manager.ClientManager;
import com.textTI.storeManagement.model.Client;

@Controller
public class DashboardController extends BaseController{
	
	@Autowired
	private ClientManager clienteManager;
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(Locale locale, Model model) {
		logger.info("Accessed the dashboard", locale);
		
		List<Client> clients = this.clienteManager.getAll();
		model.addAttribute("clientsSize", clients.size());
		
		return "dashboard/dashboard";
	}

}
