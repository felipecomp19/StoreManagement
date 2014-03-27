package com.textTI.storeManagement.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
	

}
