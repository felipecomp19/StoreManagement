package com.textTI.storeManagement.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.textTI.storeManagement.manager.IndicatorManager;
import com.textTI.storeManagement.manager.StoreManager;
import com.textTI.storeManagement.model.Employee;
import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.Store;

@Controller
@RequestMapping(value = "/indicator")
public class IndicatorController extends BaseController {
	
	@Autowired
	private IndicatorManager indicatorManager;
	
	@Autowired
	private StoreManager storeManager;
	
	@Autowired
	private EmployeeManager employeeManager;
	
	@RequestMapping(value= "/list")
	public String list(Locale locale, Model model)
	{
		List<Indicator> indicators = this.indicatorManager.getAll();
		
		model.addAttribute("indicators", indicators);
		
		return "/indicator/list";
	}
	
	@RequestMapping(value= "/create", method = RequestMethod.GET)
	public String create(Locale locale,Model model)
	{
		populateStoreAndEmployeeList(model);
		
		Indicator indicator = new Indicator();
		indicator.setEnabled(true);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		indicator.setYear(currentYear);
		
		model.addAttribute("indicator", indicator);
		
		return "/indicator/create";
	}
	
	@RequestMapping(value= "/edit/{id}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id, Model model, Locale locale)
	{
		populateStoreAndEmployeeList(model);
		
		Indicator indicator = this.indicatorManager.getById(id);
		
		model.addAttribute("indicator", indicator);
		
		return "/indicator/edit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("indicator") Indicator indicator, HttpServletRequest request)
	{
		if(indicator.getId() != null)
			this.indicatorManager.update(indicator);
		else
			this.indicatorManager.insert(indicator);
		
		return "redirect:/indicator/list";
	}
	
	@RequestMapping(value= "/delete/{id}" , method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id, Model model)
	{
		Indicator indicator = this.indicatorManager.getById(id);
		
		this.indicatorManager.delete(indicator);

		return "redirect:/indicator/list";
	}
	
	private void populateStoreAndEmployeeList(Model model) {
		List<Store> stores = this.storeManager.getAll();
		List<Employee> employees = this.employeeManager.getAll();
		List<Integer> years = new ArrayList<Integer>(5);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		years.add(currentYear - 2);
		years.add(currentYear - 1);
		years.add(currentYear);
		years.add(currentYear + 1);
		years.add(currentYear + 2);
		
		model.addAttribute("stores",stores);
		model.addAttribute("employees", employees);
		model.addAttribute("years", years);
	}
}
