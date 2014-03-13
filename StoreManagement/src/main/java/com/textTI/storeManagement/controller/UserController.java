package com.textTI.storeManagement.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.textTI.storeManagement.manager.StoreManager;
import com.textTI.storeManagement.manager.UserManager;
import com.textTI.storeManagement.manager.UserRoleManager;
import com.textTI.storeManagement.model.Store;
import com.textTI.storeManagement.model.User;
import com.textTI.storeManagement.model.UserRole;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private UserRoleManager roleManager;
	
	@Autowired
	private StoreManager storeManager;
	
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
		User user = new User();
		user.setActive(true);
		
		this.prepareUserForm(locale, model, user);
		
		return "/user/create";
	}

	@RequestMapping(value= "/edit/{id}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id, Model model, Locale locale)
	{
		User user = this.userManager.getById(id);
		
		this.prepareUserForm(locale, model, user);
		
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
	
	
	@RequestMapping(value= "/delete/{id}" , method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id, Model model, Locale locale)
	{
		User user = this.userManager.getById(id);
		user.setEnabled(false);
		
		this.userManager.delete(user);
		
		return "redirect:/user/list";
	}
	
	private void prepareUserForm(Locale locale, Model model, User user) {
		List<UserRole> roles = this.roleManager.getAll(locale);
		List<Store> stores = this.storeManager.getAll();
		
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		model.addAttribute("stores", stores);
	}
}
