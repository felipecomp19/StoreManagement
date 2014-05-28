package com.textTI.storeManagement.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.textTI.ecommerce.manager.AccountManager;
import com.textTI.ecommerce.models.Account;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {
	
	@Autowired
	private AccountManager accountManager;
	
	@RequestMapping(value= "/edit" , method = RequestMethod.GET)
	public String edit(HttpServletRequest request, Model model, Locale locale)
	{
		Account account = this.getLoggedUser(request).getAccount();
		
		model.addAttribute("account", account);
		
		return "/account/edit";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("account") Account account, HttpServletRequest request, Model model)
	{
		try{
			if(account.getId() != null){
				this.accountManager.update(account);
				this.getLoggedUser(request).setAccount(account);
			}
			else
				this.accountManager.insert(account);
			
			model.addAttribute("successfull", true);
			logger.info("Successfully updated the account: " + account.toString());
		}catch(Exception e)
		{
			model.addAttribute("successful", true);
			logger.error("Error updating the account: " + account.toString());
		}
		
		return "/account/edit";
	}

}
