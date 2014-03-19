package com.textTI.storeManagement.controller;

import java.util.Locale;

import com.textTI.storeManagement.manager.IndicatorManager;
import com.textTI.storeManagement.model.viewModel.ReportViewModel;

@Controller
@RequestMapping(value = "/report")
public class ReportController extends BaseController {
	
	@Autowired
	private IndicatorManager indicatorManager;
	
	@RequestMapping(value= "/")
	public String list(Locale locale, Model model)
	{
		ReporViewModel reporVM = new ReportViewModel();
		model.addAttribute("reporVM", reporVM);
		
		return "/report/index";
	}
}
