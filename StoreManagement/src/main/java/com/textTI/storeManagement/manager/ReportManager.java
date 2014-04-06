package com.textTI.storeManagement.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.Report;
import com.textTI.storeManagement.model.User;
import com.textTI.storeManagement.model.constants.ReportConstants;

@Component
public class ReportManager {

	@Autowired
	private MessageSource msgSrc;
	
	@Autowired
	private IndicatorManager indicatorManager;
	
	protected static final Logger logger = LoggerFactory.getLogger(ReportManager.class);
	
	public List<Report> getAll(Locale locale) {
		List<Report> reports = new ArrayList<Report>();
		
		Report report1 = new Report();
		report1.setCode(ReportConstants.REP_RESULT_OF_MONTH_CODE);
		report1.setDescription(msgSrc.getMessage(ReportConstants.REP_RESULT_OF_MONTH_DESC,null, locale));
		reports.add(report1);
		
		Report report2 = new Report();
		report2.setCode(ReportConstants.REP_CUMULATIVE_RESULT_CODE);
		report2.setDescription(msgSrc.getMessage(ReportConstants.REP_CUMULATIVE_RESULT_DESC,null, locale));
		reports.add(report2);
		
		Report report3 = new Report();
		report3.setCode(ReportConstants.REP_EVOLUTION_OF_INDICATORS_CODE);
		report3.setDescription(msgSrc.getMessage(ReportConstants.REP_EVOLUTION_OF_INDICATORS_DESC,null, locale));
		reports.add(report3);

		return reports;
	}

	public List<Indicator> generateReportResultOfMonth(User loggedUser, String selectedMonth, String selectedYear) {
		
		List<Indicator> result = this.indicatorManager.getAllByUserAndMonth(loggedUser, selectedMonth, selectedYear); 
		
		return result;
	}
	
	public Indicator generateReportResultOfMonthTotals(User loggedUser,
			String month, String year) {
		
		return this.indicatorManager.generateReportResultOfMonthTotals(loggedUser, month, year);
	}

	public List<Indicator> generateReportevolutionOfIndicators(User loggedUser,
			String monthFrom, String yearFrom, String monthTo, String yearTo) {
		
		List<Indicator> result = this.indicatorManager.generateReportevolutionOfIndicators(loggedUser, monthFrom, yearFrom, monthTo, yearTo);
		
		return result;
	}

	public List<Indicator> generateReportCumulativeResult(User loggedUser,
			String monthFrom, String yearFrom, String monthTo, String yearTo) {
		
		List<Indicator> result = this.indicatorManager.generateReportCumulativeResult(loggedUser, monthFrom, yearFrom, monthTo, yearTo);
		
		return result;
	}

	public Indicator generateReportCumulativeResultTotals(User loggedUser,
			String monthFrom, String yearFrom, String monthTo, String yearTo) {
		
		return this.indicatorManager.generateReportCumulativeResultTotals(loggedUser, monthFrom, yearFrom, monthTo, yearTo);
	}

	public List<Indicator> getIndicatorsByStoreMonthAndYear(User loggedUser,
			int store, int month, int year) {
		return this.indicatorManager.getIndicatorsByStoreMonthAndYear(store,month,year);
	}

	public Indicator getIndicatorsByStoreMonthAndYearTotals(User loggedUser,
			int store, int month, int year) {
		return this.indicatorManager.getIndicatorsByStoreMonthAndYearTotals(store,month,year);
	}
}
