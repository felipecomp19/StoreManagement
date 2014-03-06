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

import com.textTI.storeManagement.manager.UserManager;
import com.textTI.storeManagement.manager.UserRoleManager;
import com.textTI.storeManagement.model.User;
import com.textTI.storeManagement.model.UserRole;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private UserRoleManager roleManager;
	
	@RequestMapping(value= "/list")
	public String list(Locale locale, Model model)
	{
		List<User> users = this.userManager.getAll(locale);
		
		model.addAttribute("users", users);
		
		return "/user/list";
	}
	
	@RequestMapping(value= "/create", method = RequestMethod.GET)
	public String create(Locale locale,Model model)
	{
		List<UserRole> roles = this.roleManager.getAll(locale); 
		User user = new User();
		user.setActive(true);
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		
		return "/user/create";
	}
	
	@RequestMapping(value= "/edit/{id}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id, Model model, Locale locale)
	{
		User user = this.userManager.getById(id);
		List<UserRole> roles = this.roleManager.getAll(locale);
		
		model.addAttribute("user", user);
		model.addAttribute("roles",roles);
		
		return "/user/edit";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("user") User user, HttpServletRequest request)
	{
		if(user.getId() != null)
			this.userManager.update(user);
		else
			this.userManager.insert(user);
		
		return "redirect:/user/list";
	}
}
