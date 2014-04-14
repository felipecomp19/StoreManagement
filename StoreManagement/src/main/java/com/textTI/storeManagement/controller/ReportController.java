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
import org.springframework.web.bind.annotation.ResponseBody;

import com.textTI.storeManagement.manager.IndicatorManager;
import com.textTI.storeManagement.manager.ReportManager;
import com.textTI.storeManagement.manager.StoreManager;
import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.Store;
import com.textTI.storeManagement.model.constants.ReportConstants;
import com.textTI.storeManagement.model.report.EvolutionOfIndicatorReportData;
import com.textTI.storeManagement.model.report.IndicatorsSummary;
import com.textTI.storeManagement.model.viewModel.ReportViewModel;

@Controller
@RequestMapping(value = "/report")
public class ReportController extends BaseController {
	
	@Autowired
	private ReportManager reportManager;
	
	@Autowired
	private IndicatorManager indicatorManager;
	
	@Autowired
	private StoreManager storeManager;
	
	/*@ModelAttribute("reporList")
	public List<Report> getReportList(Locale locale)
	{
		return this.reportManager.getAll(locale);
	}*/
	
	@ModelAttribute("yearList")
	public List<String> getYearList()
	{
		return this.indicatorManager.getDistinctYears();
	}
	
	@ModelAttribute("storeList")
	public List<Store> getStoreList(HttpServletRequest request)
	{
		return this.storeManager.getAllByUser(this.getLoggedUser(request));
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
			logger.info("Selected report" + ReportConstants.REP_RESULT_OF_MONTH_DESC + " month:" + reportVM.getSelectedMonth());
			model.addAttribute("reportVM", reportVM);
			return "/report/reportResultOfMonth";

		}else if(reportVM.getSelectedReport().getCode() == (ReportConstants.REP_CUMULATIVE_RESULT_CODE)){
			logger.info("Selected report" + ReportConstants.REP_CUMULATIVE_RESULT_DESC + " month:" + reportVM.getSelectedMonth());
			model.addAttribute("reportVM", reportVM);
			return "/report/reportCumulativeResult";
		
		}else if(reportVM.getSelectedReport().getCode() == (ReportConstants.REP_EVOLUTION_OF_INDICATORS_CODE)){
			logger.info("Selected report" + ReportConstants.REP_EVOLUTION_OF_INDICATORS_DESC + " month:" + reportVM.getSelectedMonth());
			
			List<EvolutionOfIndicatorReportData> reportData = this.reportManager.generateReportevolutionOfIndicators(this.getLoggedUser(request), reportVM.getMonthFrom(), reportVM.getYearFrom(), reportVM.getMonthTo(), reportVM.getYearTo());
			reportVM.setEvolutionOfIndicatorReportData(reportData);
			
			model.addAttribute("reportVM", reportVM);
			return "/report/reportEvolutionOfIndicators";
		}else
			System.out.println("Unkown the report");
		
		return "/report/index";
	}
	
	@RequestMapping(value="/generateReportResultOfMonth/{month}/{year}")
	public @ResponseBody IndicatorsSummary generateReportResultOfMonth(@PathVariable("month") String month, @PathVariable("year") String year, HttpServletRequest request, Model model) {
		logger.info("gerentinh report" + ReportConstants.REP_RESULT_OF_MONTH_DESC + " month:" + month + "/" + year);
		
		List<Indicator> indicators = this.reportManager.generateReportResultOfMonth(this.getLoggedUser(request), month, year);
		Indicator totals = this.reportManager.generateReportResultOfMonthTotals(this.getLoggedUser(request), month, year);
		
		IndicatorsSummary result = new IndicatorsSummary();
		
		if(indicators.size() > 0){
			result.setIndicators(indicators);
			result.setUserData(totals);
		}
		
		return result;
	}
	
	@RequestMapping(value="/generateReportCumulativeResult/{mf}/{yf}/{mt}/{yt}")
	public @ResponseBody IndicatorsSummary generateReportCumulativeResult(@PathVariable("mf") String monthFrom, @PathVariable("yf") String yearFrom, @PathVariable("mt") String monthTo, @PathVariable("yt") String yearTo, HttpServletRequest request, Model model) {
		logger.info("gerentinh report" + ReportConstants.REP_RESULT_OF_MONTH_DESC + " month:" + monthFrom + "/" + yearFrom);
		
		List<Indicator> indicators = this.reportManager.generateReportCumulativeResult(this.getLoggedUser(request), monthFrom, yearFrom, monthTo, yearTo);
		Indicator totals = this.reportManager.generateReportCumulativeResultTotals(this.getLoggedUser(request), monthFrom, yearFrom, monthTo, yearTo);
		
		ReportViewModel reportVM = new ReportViewModel();
		model.addAttribute("reportVM",reportVM);
		
		IndicatorsSummary result = new IndicatorsSummary();
		result.setUserData(totals);

		result.setIndicators(indicators);
		
		return result;
		
	}
	
	@RequestMapping(value="/generateReportevolutionOfIndicators/{mf}/{yf}/{mt}/{yt}")
	public @ResponseBody List<Indicator> generateReportevolutionOfIndicators(@PathVariable("mf") String monthFrom, @PathVariable("yf") String yearFrom, @PathVariable("mt") String monthTo, @PathVariable("yt") String yearTo, HttpServletRequest request, Model model) {
		logger.info("gerentinh report" + ReportConstants.REP_RESULT_OF_MONTH_DESC + " month:" + monthFrom + "/" + yearFrom);
		
		//EvolutionOfIndicatorReportData reportData = this.reportManager.generateReportevolutionOfIndicators(this.getLoggedUser(request), monthFrom, yearFrom, monthTo, yearTo);
		
		return null;
	}
	
	@RequestMapping(value="/getIndicatorsByStoreMonthAndYear/{store}/{month}/{year}")
	public @ResponseBody IndicatorsSummary getIndicatorsByStoreMonthAndYear(@PathVariable("store") int store, @PathVariable("month") int month, @PathVariable("year") int year, HttpServletRequest request, Model model) {
		
		List<Indicator> indicators = this.reportManager.getIndicatorsByStoreMonthAndYear(this.getLoggedUser(request), store, month, year);
		//Indicator totals = this.reportManager.getIndicatorsByStoreMonthAndYearTotals(this.getLoggedUser(request), store, month, year);
		
		IndicatorsSummary result = new IndicatorsSummary();
		//result.setUserData(totals);

		result.setIndicators(indicators);
		
		return result;
	}
	
	
	

	private List<String> populateHeaderList(String monthFrom, String yearFrom,
			String monthTo, String yearTo) {
		Calendar dtFrom = Calendar.getInstance();
		dtFrom.set(Integer.parseInt(yearFrom), (Integer.parseInt(monthFrom)-1),1);
		
		Calendar dtTo = Calendar.getInstance();
		dtTo.set(Integer.parseInt(yearTo), (Integer.parseInt(monthTo)- 1),1);
		
		List<String> headers = new ArrayList<String>();
		while (dtFrom.before(dtTo)) {
			//System.out.println(dtFrom.get(Calendar.MONTH) + "/" + dtFrom.get(Calendar.YEAR));
			
			headers.add((dtFrom.get(Calendar.MONTH) + 1) + "/" + dtFrom.get(Calendar.YEAR));
			dtFrom.add(Calendar.MONTH, 1);
		}
		return headers;
	}
}
