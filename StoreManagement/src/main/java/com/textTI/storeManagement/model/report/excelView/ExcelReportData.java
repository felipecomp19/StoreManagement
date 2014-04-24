package com.textTI.storeManagement.model.report.excelView;

import java.util.List;

import com.textTI.storeManagement.model.Indicator;

public class ExcelReportData {
	
	private String reportName;
	
	private List<String> headers;
	
	private List<Indicator> indicators;
	
	private Indicator totals;
	
	public ExcelReportData(String reportName, List<String> headers,
			List<Indicator> indicators, Indicator totals) {
		super();
		this.reportName = reportName;
		this.headers = headers;
		this.indicators = indicators;
		this.totals = totals;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
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
}
