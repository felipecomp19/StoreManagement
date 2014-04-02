package com.textTI.storeManagement.model.viewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.textTI.storeManagement.model.Employee;
import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.Report;
import com.textTI.storeManagement.model.Store;
import com.textTI.storeManagement.model.utils.Month;

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
	
	private List<Month> monthList = new ArrayList<Month>(12);
	
	//private List<String> yearList = new ArrayList<String>(5);
	
	private int monthFrom;
	private int monthTo;
	
	private String yearFrom;
	private String yearTo;
	
	private List<String> headers;
	
	public ReportViewModel()
	{
		this.populateMonthList();
		//this.populateYearList();
	}

	/*private void populateYearList() {
		yearList.add("2013");
		yearList.add("2014");
		yearList.add("2015");
		yearList.add("2016");
		yearList.add("2017");
	}*/

	private void populateMonthList() {
		Month jan = new Month(1, "month.jan");
		Month feb = new Month(2, "month.feb");
		Month mar = new Month(3, "month.mar");
		Month apr = new Month(4, "month.apr");
		Month may = new Month(5, "month.may");
		Month jun = new Month(6, "month.jun");
		Month jul = new Month(7, "month.jul");
		Month ago = new Month(8, "month.ago");
		Month set = new Month(9, "month.set");
		Month oct = new Month(10, "month.oct");
		Month nov = new Month(11, "month.nov");
		Month dez = new Month(12, "month.dez");
		monthList.add(jan);
		monthList.add(feb);
		monthList.add(mar);
		monthList.add(apr);
		monthList.add(may);
		monthList.add(jun);
		monthList.add(jul);
		monthList.add(ago);
		monthList.add(set);
		monthList.add(oct);
		monthList.add(nov);
		monthList.add(dez);
	}

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

	public List<Month> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<Month> monthList) {
		this.monthList = monthList;
	}

	/*public List<String> getYearList() {
		return yearList;
	}

	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}*/

	public int getMonthFrom() {
		return monthFrom;
	}

	public void setMonthFrom(int monthFrom) {
		this.monthFrom = monthFrom;
	}

	public int getMonthTo() {
		return monthTo;
	}

	public void setMonthTo(int monthTo) {
		this.monthTo = monthTo;
	}

	public String getYearFrom() {
		return yearFrom;
	}

	public void setYearFrom(String yearFrom) {
		this.yearFrom = yearFrom;
	}

	public String getYearTo() {
		return yearTo;
	}

	public void setYearTo(String yearTo) {
		this.yearTo = yearTo;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}
	
	
}
