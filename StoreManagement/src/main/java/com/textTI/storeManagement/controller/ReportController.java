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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.textTI.storeManagement.manager.IndicatorManager;
import com.textTI.storeManagement.manager.ReportManager;
import com.textTI.storeManagement.manager.StoreManager;
import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.Store;
import com.textTI.storeManagement.model.constants.ReportConstants;
import com.textTI.storeManagement.model.report.EvolutionOfIndicatorReportData;
import com.textTI.storeManagement.model.report.IndicatorsSummary;
import com.textTI.storeManagement.model.report.excelView.ExcelReportData;
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
			
			List<Indicator> indicators = this.reportManager.generateReportResultOfMonth(this.getLoggedUser(request), reportVM.getSelectedMonth(), reportVM.getSelectedYear(), reportVM.getStore().getId());
			Indicator totals = this.reportManager.generateReportResultOfMonthTotals(this.getLoggedUser(request), reportVM.getSelectedMonth(), reportVM.getSelectedYear(), reportVM.getStore().getId());
			
			IndicatorsSummary result = new IndicatorsSummary();
			
			populateResult(result, indicators, totals);
			
			reportVM.setReportData(result);
			model.addAttribute("reportVM", reportVM);
			return "/report/reportResultOfMonth";

		}else if(reportVM.getSelectedReport().getCode() == (ReportConstants.REP_CUMULATIVE_RESULT_CODE)){
			logger.info("Selected report" + ReportConstants.REP_CUMULATIVE_RESULT_DESC + " month:" + reportVM.getSelectedMonth());
			
			List<Indicator> indicators = this.reportManager.generateReportCumulativeResult(this.getLoggedUser(request), reportVM.getMonthFrom(), reportVM.getYearFrom(), reportVM.getMonthTo(), reportVM.getYearTo(), reportVM.getStore().getId());
			Indicator totals = this.reportManager.generateReportCumulativeResultTotals(this.getLoggedUser(request), reportVM.getMonthFrom(), reportVM.getYearFrom(), reportVM.getMonthTo(), reportVM.getYearTo(), reportVM.getStore().getId());
			
			IndicatorsSummary result = new IndicatorsSummary();
			result.setUserData(totals);
			result.setIndicators(indicators);
			
			reportVM.setReportData(result);
			model.addAttribute("reportVM", reportVM);
			return "/report/reportCumulativeResult";
		
		}else if(reportVM.getSelectedReport().getCode() == (ReportConstants.REP_EVOLUTION_OF_INDICATORS_CODE)){
			logger.info("Selected report" + ReportConstants.REP_EVOLUTION_OF_INDICATORS_DESC + " month:" + reportVM.getSelectedMonth());
			
			List<EvolutionOfIndicatorReportData> reportData = this.reportManager.generateReportevolutionOfIndicators(this.getLoggedUser(request), reportVM.getMonthFrom(), reportVM.getYearFrom(), reportVM.getMonthTo(), reportVM.getYearTo(), reportVM.getStore().getId());
			reportVM.setEvolutionOfIndicatorReportData(reportData);
			
			EvolutionOfIndicatorReportData totals = this.reportManager.generateReportevolutionOfIndicatorsTotals(this.getLoggedUser(request), reportVM.getMonthFrom(), reportVM.getYearFrom(), reportVM.getMonthTo(), reportVM.getYearTo(), reportVM.getStore().getId());
			reportVM.setEvolutionOfIndicatorReportDataTotals(totals);
			
			model.addAttribute("reportVM", reportVM);
			return "/report/reportEvolutionOfIndicators";
		}else
			System.out.println("Unkown the report");
		
		return "/report/index";
	}
	
	@RequestMapping(value="/exportReportResultOfMonth/{store}/{month}/{year}")
	public ModelAndView exportReportResultOfMonth(@PathVariable("store") int storeId, @PathVariable("month") int month, @PathVariable("year") int year, HttpServletRequest request, Model model) {
		
		logger.info("export report" + ReportConstants.REP_RESULT_OF_MONTH_DESC);
		
		List<Indicator> indicators = this.reportManager.generateReportResultOfMonth(this.getLoggedUser(request), month, year, storeId);
		Indicator totals = this.reportManager.generateReportResultOfMonthTotals(this.getLoggedUser(request), reportVM.getSelectedMonth(), reportVM.getSelectedYear(), reportVM.getStore().getId());

		ExcelReportData reportData = new ExcelReportData("Result of month", null, indicators, totals);		
		
		return new ModelAndView("IndicatorsExcelReport","reportData", reportData);
	}
	
	@RequestMapping(value="/exportReportCumulativeResult/{store}/{monthFrom}/{yearFrom}/{monthTo}/{yearTo}")
	public ModelAndView exportReportCumulativeResult(@PathVariable("store") long storeId, @PathVariable("monthFrom") String monthFrom, @PathVariable("yearFrom") String yearFrom, @PathVariable("monthTo") String monthTo, @PathVariable("yearTo") String yearTo, HttpServletRequest request, Model model) {
		
		logger.info("export report" + ReportConstants.REP_CUMULATIVE_RESULT_DESC);
		
		List<Indicator> indicators = this.reportManager.generateReportCumulativeResult(this.getLoggedUser(request), monthFrom, yearFrom, monthTo, yearTo, storeId);
		Indicator totals = this.reportManager.generateReportCumulativeResultTotals(this.getLoggedUser(request), monthFrom, yearFrom, monthTo, yearTo, storeId);
		
		ExcelReportData reportData = new ExcelReportData("Cumulative Result", null, indicators, totals);
		
		return new ModelAndView("IndicatorsExcelReport","reportData", reportData);
	}
	
	@RequestMapping(value = "/exportReportToExcel", method = RequestMethod.POST)
	public ModelAndView exportReportToExcel(@ModelAttribute("reportVM") ReportViewModel reportVM, HttpServletRequest request, Model model)
	{
		IndicatorsSummary result = new IndicatorsSummary();
		if(reportVM.getSelectedReport().getCode() == (ReportConstants.REP_RESULT_OF_MONTH_CODE))
		{
			logger.info("export report" + ReportConstants.REP_RESULT_OF_MONTH_DESC);
			
			List<Indicator> indicators = this.reportManager.generateReportResultOfMonth(this.getLoggedUser(request), reportVM.getSelectedMonth(), reportVM.getSelectedYear(), reportVM.getStore().getId());
			Indicator totals = this.reportManager.generateReportResultOfMonthTotals(this.getLoggedUser(request), reportVM.getSelectedMonth(), reportVM.getSelectedYear(), reportVM.getStore().getId());
			
			this.populateResult(result, indicators, totals);
			
		}else if(reportVM.getSelectedReport().getCode() == (ReportConstants.REP_CUMULATIVE_RESULT_CODE)){
			logger.info("export report" + ReportConstants.REP_CUMULATIVE_RESULT_DESC);
			
			List<Indicator> indicators = this.reportManager.generateReportCumulativeResult(this.getLoggedUser(request), reportVM.getMonthFrom(), reportVM.getYearFrom(), reportVM.getMonthTo(), reportVM.getYearTo(), reportVM.getStore().getId());
			Indicator totals = this.reportManager.generateReportCumulativeResultTotals(this.getLoggedUser(request), reportVM.getMonthFrom(), reportVM.getYearFrom(), reportVM.getMonthTo(), reportVM.getYearTo(), reportVM.getStore().getId());
			
			this.populateResult(result, indicators, totals);
		
		}else if(reportVM.getSelectedReport().getCode() == (ReportConstants.REP_EVOLUTION_OF_INDICATORS_CODE)){
			logger.info("export report" + ReportConstants.REP_EVOLUTION_OF_INDICATORS_DESC);
			
			List<EvolutionOfIndicatorReportData> reportData = this.reportManager.generateReportevolutionOfIndicators(this.getLoggedUser(request), reportVM.getMonthFrom(), reportVM.getYearFrom(), reportVM.getMonthTo(), reportVM.getYearTo(), reportVM.getStore().getId());
			reportVM.setEvolutionOfIndicatorReportData(reportData);
			
			EvolutionOfIndicatorReportData totals = this.reportManager.generateReportevolutionOfIndicatorsTotals(this.getLoggedUser(request), reportVM.getMonthFrom(), reportVM.getYearFrom(), reportVM.getMonthTo(), reportVM.getYearTo(), reportVM.getStore().getId());
			reportVM.setEvolutionOfIndicatorReportDataTotals(totals);

		}else
			System.out.println("Unkown the report");
		
		return new ModelAndView("IndicatorsExcelReport","reportData",result);
	}

	private void populateResult(IndicatorsSummary result,
			List<Indicator> indicators, Indicator totals) {
		if(indicators.size() > 0){
			result.setIndicators(indicators);
			result.setUserData(totals);
		}
	}
	
	@RequestMapping(value="/getIndicatorsByStoreMonthAndYear/{store}/{month}/{year}")
	public @ResponseBody IndicatorsSummary getIndicatorsByStoreMonthAndYear(@PathVariable("store") int store, @PathVariable("month") int month, @PathVariable("year") int year, HttpServletRequest request, Model model) {
		
		List<Indicator> indicators = this.reportManager.getIndicatorsByStoreMonthAndYear(this.getLoggedUser(request), store, month, year);
		
		IndicatorsSummary result = new IndicatorsSummary();

		result.setIndicators(indicators);
		
		return result;
	}
	
	@RequestMapping(value="/getIndicatorsByStoreAndRangeOfMonthAndYear/{store}/{monthFrom}/{yearFrom}/{monthTo}/{yearTo}")
	public @ResponseBody IndicatorsSummary getIndicatorsByStoreAndRangeOfMonthAndYear(@PathVariable("store") long storeId, @PathVariable("monthFrom") String monthFrom, @PathVariable("yearFrom") String yearFrom, @PathVariable("monthTo") String monthTo, @PathVariable("yearTo") String yearTo, HttpServletRequest request, Model model) {
		
		List<Indicator> indicators = this.reportManager.generateReportCumulativeResult(this.getLoggedUser(request), monthFrom, yearFrom, monthTo, yearTo, storeId);
		
		IndicatorsSummary result = new IndicatorsSummary();

		result.setIndicators(indicators);
		
		return result;
	}
}
