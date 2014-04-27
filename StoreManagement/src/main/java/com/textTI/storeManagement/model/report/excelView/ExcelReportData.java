package com.textTI.storeManagement.model.report.excelView;

import java.util.List;
import java.util.Locale;

import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.Report;

public class ExcelReportData {
	
	private Report report;
	
	private List<String> reportHeaders;
	
	private List<String> reportSheets;
	
	private List<Indicator> indicators;
	
	private Indicator totals;
	
	private Locale locale;
	
	public ExcelReportData(Report report, List<String> reportHeaders, List<Indicator> indicators,
			Indicator totals, Locale locale) {
		super();
		this.report = report;
		this.reportHeaders = reportHeaders;
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

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}

	public Indicator getTotals() {
		return totals;
	}

	public void setTotals(Indicator totals) {
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

	public List<String> getReportSheets() {
		return reportSheets;
	}

	public void setReportSheets(List<String> reportSheets) {
		this.reportSheets = reportSheets;
	}
}
