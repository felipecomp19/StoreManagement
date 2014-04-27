package com.textTI.storeManagement.model.report.excelView;

import java.util.List;
import java.util.Locale;

import com.textTI.storeManagement.model.Report;
import com.textTI.storeManagement.model.report.EvolutionOfIndicatorReportData;

public class EvolutionOfIndicatorsExcelReportData {
	
	private Report report;
	
	private List<String> reportSheets;
	
	private List<String> reportHeaders;
	
	private List<EvolutionOfIndicatorReportData> indicators;
	
	private EvolutionOfIndicatorReportData totals;
	
	private Locale locale;

	public EvolutionOfIndicatorsExcelReportData(Report report,
			List<String> reportSheets,
			List<String> headers, List<EvolutionOfIndicatorReportData> indicators,
			EvolutionOfIndicatorReportData totals, Locale locale) {
		super();
		this.report = report;
		this.reportSheets = reportSheets;
		this.reportHeaders = headers;
		this.indicators = indicators;
		this.totals = totals;
		this.locale = locale;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public List<String> getReportSheets() {
		return reportSheets;
	}

	public void setReportSheets(List<String> reportSheets) {
		this.reportSheets = reportSheets;
	}

	public List<EvolutionOfIndicatorReportData> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<EvolutionOfIndicatorReportData> indicators) {
		this.indicators = indicators;
	}

	public EvolutionOfIndicatorReportData getTotals() {
		return totals;
	}

	public void setTotals(EvolutionOfIndicatorReportData totals) {
		this.totals = totals;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public List<String> getReportHeaders() {
		return reportHeaders;
	}

	public void setReportHeaders(List<String> reportHeaders) {
		this.reportHeaders = reportHeaders;
	}
}
