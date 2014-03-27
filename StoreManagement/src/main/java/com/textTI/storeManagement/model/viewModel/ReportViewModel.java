package com.textTI.storeManagement.model.viewModel;

import java.util.Date;
import java.util.List;

import com.textTI.storeManagement.model.Employee;
import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.Report;
import com.textTI.storeManagement.model.Store;

public class ReportViewModel {

	private List<Report> reportsSL;
	
	private Report selectedReport;
	
	private Store store;
	
	private Employee employee;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private String selectedMonth;
	
	private String selectedYear;
	
	private List<Indicator> indicators;

	public List<Report> getReportsSL() {
		return reportsSL;
	}

	public void setReportsSL(List<Report> reportsSL) {
		this.reportsSL = reportsSL;
	}
	
	public Report getSelectedReport() {
		return selectedReport;
	}

	public void setSelectedReport(Report selectedReport) {
		this.selectedReport = selectedReport;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getSelectedMonth() {
		return selectedMonth;
	}
	
	public String getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(String selectedYear) {
		this.selectedYear = selectedYear;
	}

	public void setSelectedMonth(String selectedMonth) {
		this.selectedMonth = selectedMonth;
	}

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}
}
