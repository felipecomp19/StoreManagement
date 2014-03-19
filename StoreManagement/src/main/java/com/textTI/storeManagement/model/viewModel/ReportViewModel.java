package com.textTI.storeManagement.model.viewModel;

import java.util.Date;
import java.util.List;

import com.textTI.storeManagement.model.Employee;
import com.textTI.storeManagement.model.Report;
import com.textTI.storeManagement.model.Store;

public class ReportViewModel {

	private List<Report> reportsSL;
	
	private Store store;
	
	private Employee employee;
	
	private Date dateFrom;
	
	private Date dateTo;

	public List<Report> getReportsSL() {
		return reportsSL;
	}

	public void setReportsSL(List<Report> reportsSL) {
		this.reportsSL = reportsSL;
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
}
