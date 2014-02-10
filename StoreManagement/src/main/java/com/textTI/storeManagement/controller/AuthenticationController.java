package com.textTI.storeManagement.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends BaseController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Accessed the login form");
		
		return "login";
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(Model model) {
 
		model.addAttribute("error", "true");
		return "login";
 
	}
}
