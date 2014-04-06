package com.textTI.storeManagement.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String list(HttpServletRequest request, Model model)
	{
		List<Indicator> indicators = this.indicatorManager.getAllByUser(this.getLoggedUser(request));
		
		model.addAttribute("indicators", indicators);
		
		return "/indicator/list";
	}
	
	@RequestMapping(value= "/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request, Model model)
	{
		populateStoreAndEmployeeList(request,model);
		
		Indicator indicator = new Indicator();
		indicator.setEnabled(true);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		indicator.setYear(currentYear);
		
		model.addAttribute("indicator", indicator);
		
		return "/indicator/create";
	}
	
	@RequestMapping(value= "/edit/{id}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id,HttpServletRequest request, Model model)
	{
		populateStoreAndEmployeeList(request,model);
		
		Indicator indicator = this.indicatorManager.getById(id);
		
		model.addAttribute("indicator", indicator);
		
		return "/indicator/edit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("indicator") Indicator indicator, HttpServletRequest request, Model model)
	{
			if(indicator.getId() != null)
				if(this.validate(indicator, model, request))
					this.indicatorManager.update(indicator);
				else{
					model.addAttribute("indicator", indicator);
					return "/indicator/edit";
				}
			else
				if(this.validate(indicator, model, request))
					this.indicatorManager.insert(indicator);
				else{
					model.addAttribute("indicator", indicator);
					return "/indicator/create";
				}
		
		return "redirect:/indicator/list";
	}
	
	@RequestMapping(value= "/delete/{id}" , method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id, Model model)
	{
		Indicator indicator = this.indicatorManager.getById(id);
		
		this.indicatorManager.delete(indicator);

		return "redirect:/indicator/list";
	}
	
	@RequestMapping(value= "/filterEmployeeSL/{storeId}" , method = RequestMethod.GET)
	public @ResponseBody List<Employee> filterEmployeeSL(@PathVariable("storeId") long storeId)
	{
		List<Employee> employees = this.storeManager.getById(storeId).getEmployees();
		
		return employees;
	}
	
	private boolean validate(Indicator indicator, Model model, HttpServletRequest request) {
		Indicator _ind = this.indicatorManager.getByMonthAndYear(indicator.getEmployee().getId(), indicator.getMonth(),indicator.getYear());
		if(_ind != null && _ind.getId() != indicator.getId()){
			model.addAttribute("showMessage", true);
			populateStoreAndEmployeeList(request,model);
			model.addAttribute("validMessage", "validMessage.indAlreadyExist");
			model.addAttribute("indAlreadyExist",_ind);
			return false;
		}
		return true;
	}
	
	private void populateStoreAndEmployeeList(HttpServletRequest request, Model model) {
		List<Store> stores = this.storeManager.getAllByUser(this.getLoggedUser(request));
		List<Employee> employees = this.employeeManager.getAllByUser(this.getLoggedUser(request));
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
