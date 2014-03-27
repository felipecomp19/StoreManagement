package com.textTI.storeManagement.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.textTI.storeManagement.manager.ReportManager;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.Report;
import com.textTI.storeManagement.model.constants.ReportConstants;
import com.textTI.storeManagement.model.viewModel.ReportViewModel;

@Controller
@RequestMapping(value = "/report")
public class ReportController extends BaseController {
	
	@Autowired
	private ReportManager reportManager;
	
	@ModelAttribute("reporList")
	public List<Report> getReportList(Locale locale)
	{
		return this.reportManager.getAll(locale);
	}
	
	@RequestMapping(value= "/")
	public String list(Locale locale, Model model)
	{
		ReportViewModel reportVM = new ReportViewModel();
		model.addAttribute("reportVM", reportVM);
		
		return "/report/index";
	}
	
	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	public String save(@ModelAttribute("reportVM") ReportViewModel reportVM, HttpServletRequest request, Model model)
	{
		if(reportVM.getSelectedReport().getCode() == (ReportConstants.REP_RESULT_OF_MONTH_CODE))
		{
			logger.info("Generated report" + ReportConstants.REP_RESULT_OF_MONTH_DESC + " month:" + reportVM.getSelectedMonth());
			model.addAttribute("reportVM", reportVM);
			
			return "/report/reportResultOfMonth";
		}
			
		else if(reportVM.getSelectedReport().getCode() == (ReportConstants.REP_CUMULATIVE_RESULT_CODE))
			System.out.println(ReportConstants.REP_CUMULATIVE_RESULT_DESC);
		else if(reportVM.getSelectedReport().getCode() == (ReportConstants.REP_RESULT_OF_MONTH_CODE))
			System.out.println(ReportConstants.REP_RESULT_OF_MONTH_DESC);
		else
			System.out.println("Unkown the report");
		
		return "/report/index";
	}
	
	@RequestMapping(value="/generateReportResultOfMonth/{month}/{year}")
	public @ResponseBody List<Indicator> generateReportResultOfMonth(@PathVariable("month") String month, @PathVariable("year") String year, HttpServletRequest request, Model model) {
		List<Indicator> indicators = this.reportManager.generateReportResultOfMonth(this.getLoggedUser(request), month, year);
		
		return indicators;
	}
}
