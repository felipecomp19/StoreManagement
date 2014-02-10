package com.textTI.storeManagement.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController extends BaseController {

	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/error404.html", method = RequestMethod.GET)
	public String error404(Locale locale, Model model) {
		logger.error("ERROR 404");
		
		return "error/error404";
	}
}
