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

import com.textTI.storeManagement.manager.EmployeeManager;
import com.textTI.storeManagement.manager.StoreManager;
import com.textTI.storeManagement.model.Employee;
import com.textTI.storeManagement.model.Store;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController extends BaseController {
	
	@Autowired
	private EmployeeManager employeeManager;
	
	@Autowired
	private StoreManager storeManager;
	
	@RequestMapping(value= "/list")
	public String list(HttpServletRequest request, Locale locale, Model model)
	{
		List<Employee> employees = this.employeeManager.getAllByUser(this.getLoggedUser(request));
		
		model.addAttribute("employees", employees);
		
		return "/employee/list";
	}
	
	@RequestMapping(value= "/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request, Locale locale,Model model)
	{
		List<Store> stores = this.storeManager.getAllByUser(this.getLoggedUser(request)); 
		
		Employee employee = new Employee();
		employee.setEnabled(true);
		
		model.addAttribute("employee", employee);
		model.addAttribute("stores", stores);
		
		return "/employee/create";
	}
	
	@RequestMapping(value= "/edit/{id}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id,HttpServletRequest request, Model model, Locale locale)
	{
		Employee employee = this.employeeManager.getById(id);
		List<Store> stores = this.storeManager.getAllByUser(this.getLoggedUser(request));
		
		model.addAttribute("employee", employee);
		model.addAttribute("stores",stores);
		
		return "/employee/edit";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("employee") Employee employee, HttpServletRequest request)
	{
		if(employee.getId() != null)
			this.employeeManager.update(employee);
		else
			this.employeeManager.insert(employee);
		
		return "redirect:/employee/list";
	}
	
	@RequestMapping(value= "/delete/{id}" , method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id, Model model)
	{
		Employee employee = this.employeeManager.getById(id);
		
		this.employeeManager.delete(employee);

		return "redirect:/employee/list";
	}
}
