package com.textTI.storeManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import com.textTI.storeManagement.model.Report;
import com.textTI.storeManagement.model.Store;
import com.textTI.storeManagement.model.constants.ReportConstants;
import com.textTI.storeManagement.model.report.EvolutionOfIndicatorReportData;
import com.textTI.storeManagement.model.report.IndicatorsSummary;
import com.textTI.storeManagement.model.report.excelView.EvolutionOfIndicatorsExcelReportData;
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
	
	@Autowired
	private MessageSource msgSrc;
	
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
	public ModelAndView exportReportResultOfMonth(@PathVariable("store") long storeId, @PathVariable("month") String month, @PathVariable("year") String year, HttpServletRequest request, Model model,Locale locale) {
		
		logger.info("export report" + ReportConstants.REP_RESULT_OF_MONTH_DESC);
		
		List<Indicator> indicators = this.reportManager.generateReportResultOfMonth(this.getLoggedUser(request), month, year, storeId);
		Indicator totals = this.reportManager.generateReportResultOfMonthTotals(this.getLoggedUser(request), month, year, storeId);
		
		String reportName = msgSrc.getMessage("report.name.resultOfMonth",null, locale) + "_" + month + "_" + year;
		
		Report report = new Report();
		report.setCode(ReportConstants.REP_RESULT_OF_MONTH_CODE);
		report.setDescription(reportName);
		
		ExcelReportData reportData = new ExcelReportData(report, this.populateReportHeaders(report, locale), indicators, totals, locale);		
		
		return new ModelAndView("ResultOfMonthExcelReport","reportData", reportData);
	}
	
	@RequestMapping(value="/exportReportCumulativeResult/{store}/{monthFrom}/{yearFrom}/{monthTo}/{yearTo}")
	public ModelAndView exportReportCumulativeResult(@PathVariable("store") long storeId, @PathVariable("monthFrom") String monthFrom, @PathVariable("yearFrom") String yearFrom, @PathVariable("monthTo") String monthTo, @PathVariable("yearTo") String yearTo, HttpServletRequest request, Model model,Locale locale) {
		
		logger.info("export report" + ReportConstants.REP_CUMULATIVE_RESULT_DESC);
		
		List<Indicator> indicators = this.reportManager.generateReportCumulativeResult(this.getLoggedUser(request), monthFrom, yearFrom, monthTo, yearTo, storeId);
		Indicator totals = this.reportManager.generateReportCumulativeResultTotals(this.getLoggedUser(request), monthFrom, yearFrom, monthTo, yearTo, storeId);
		
		String reportName = msgSrc.getMessage("report.cumulativeResult",null, locale) + "_" + monthFrom.concat(yearFrom) + "_" + monthTo.concat(yearTo);
		
		Report report = new Report();
		report.setCode(ReportConstants.REP_CUMULATIVE_RESULT_CODE);
		report.setDescription(reportName);
		
		ExcelReportData reportData = new ExcelReportData(report, this.populateReportHeaders(report,locale), indicators, totals, locale);
		
		return new ModelAndView("CumulativeResultExcelReport","reportData", reportData);
	}
	
	@RequestMapping(value="/exportReportEvolutionOfIndicators/{store}/{monthFrom}/{yearFrom}/{monthTo}/{yearTo}")
	public ModelAndView exportReportEvolutionOfIndicators(@PathVariable("store") long storeId, @PathVariable("monthFrom") String monthFrom, @PathVariable("yearFrom") String yearFrom, @PathVariable("monthTo") String monthTo, @PathVariable("yearTo") String yearTo, HttpServletRequest request, Model model,Locale locale) {
		
		logger.info("export report" + ReportConstants.REP_EVOLUTION_OF_INDICATORS_DESC);
		
		List<EvolutionOfIndicatorReportData> indicators = this.reportManager.generateReportevolutionOfIndicators(this.getLoggedUser(request), monthFrom, yearFrom, monthTo, yearTo, storeId);
		EvolutionOfIndicatorReportData totals = this.reportManager.generateReportevolutionOfIndicatorsTotals(this.getLoggedUser(request), monthFrom, yearFrom, monthTo, yearTo, storeId);
		
		String reportName = msgSrc.getMessage("report.name.evolutionOfIndicators",null, locale) + "_" + monthFrom.concat(yearFrom) + "_" + monthTo.concat(yearTo);
		
		Report report = new Report();
		report.setCode(ReportConstants.REP_EVOLUTION_OF_INDICATORS_CODE);
		report.setDescription(reportName);
		
		List<String> headers = this.populateReportHeaders(report, locale);
		headers.addAll(totals.getKeys());
		
		EvolutionOfIndicatorsExcelReportData reportData = new EvolutionOfIndicatorsExcelReportData(report, this.populateReportSheets(locale), headers, indicators, totals, locale);
		
		return new ModelAndView("EvolutionOfIndicatorsExcelReport","reportData", reportData);
	}
	
	private List<String> populateReportHeaders(Report report, Locale locale)
	{
		List<String> headers = new ArrayList<String>();
		
		if(report.getCode() == ReportConstants.REP_RESULT_OF_MONTH_CODE){
			headers.add(msgSrc.getMessage("label.store",null, locale));
			headers.add(msgSrc.getMessage("report.label.employee",null, locale));
			headers.add(msgSrc.getMessage("label.year",null, locale));
			headers.add(msgSrc.getMessage("report.label.month",null, locale));
			headers.add(msgSrc.getMessage("label.workedDaysT",null, locale));
			headers.add(msgSrc.getMessage("label.goal",null, locale));
			headers.add(msgSrc.getMessage("label.valueOfSalesT",null, locale));
			headers.add(msgSrc.getMessage("label.numberOfAttendancesT",null, locale));
			headers.add(msgSrc.getMessage("label.numberOfSalesT",null, locale));
			headers.add(msgSrc.getMessage("label.numberOfItemsSoldT",null, locale));
			headers.add(msgSrc.getMessage("label.achievementOfGoalsT",null, locale));
			headers.add(msgSrc.getMessage("label.averageValueOfTheProductT",null, locale));
			headers.add(msgSrc.getMessage("report.label.averageTicketT",null, locale));
			headers.add(msgSrc.getMessage("label.itemsPerSaleT",null, locale));
			headers.add(msgSrc.getMessage("label.conversionRateT",null, locale));
			headers.add(msgSrc.getMessage("label.averageSalesPerDayT",null, locale));
		}else if(report.getCode() == ReportConstants.REP_CUMULATIVE_RESULT_CODE){
			headers.add(msgSrc.getMessage("label.store",null, locale));
			headers.add(msgSrc.getMessage("report.label.employee",null, locale));
			headers.add(msgSrc.getMessage("label.workedDaysT",null, locale));
			headers.add(msgSrc.getMessage("label.goal",null, locale));
			headers.add(msgSrc.getMessage("label.valueOfSalesT",null, locale));
			headers.add(msgSrc.getMessage("label.numberOfAttendancesT",null, locale));
			headers.add(msgSrc.getMessage("label.numberOfSalesT",null, locale));
			headers.add(msgSrc.getMessage("label.numberOfItemsSoldT",null, locale));
			headers.add(msgSrc.getMessage("label.achievementOfGoalsT",null, locale));
			headers.add(msgSrc.getMessage("label.averageValueOfTheProductT",null, locale));
			headers.add(msgSrc.getMessage("report.label.averageTicketT",null, locale));
			headers.add(msgSrc.getMessage("label.itemsPerSaleT",null, locale));
			headers.add(msgSrc.getMessage("label.conversionRateT",null, locale));
			headers.add(msgSrc.getMessage("label.averageSalesPerDayT",null, locale));
		}else if(report.getCode() == ReportConstants.REP_EVOLUTION_OF_INDICATORS_CODE){
			headers.add(msgSrc.getMessage("label.store",null, locale));
			headers.add(msgSrc.getMessage("report.label.employee",null, locale));
		}
		
		return headers;
	}
	
	private List<String> populateReportSheets(Locale locale)
	{
		List<String> sheets = new ArrayList<String>();
		sheets.add(msgSrc.getMessage("label.achievementOfGoals",null, locale));
		sheets.add(msgSrc.getMessage("report.sheet.averageValueOfTheProduct",null, locale));
		sheets.add(msgSrc.getMessage("report.sheet.averageTicket",null, locale));
		sheets.add(msgSrc.getMessage("label.itemsPerSale",null, locale));
		sheets.add(msgSrc.getMessage("report.sheet.conversionRate",null, locale));
		sheets.add(msgSrc.getMessage("report.sheet.averageSalesPerDay",null, locale));
		
		return sheets;
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
