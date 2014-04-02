package com.textTI.storeManagement.model.report;

import java.util.Date;
import java.util.Map;

import com.textTI.storeManagement.model.Employee;
import com.textTI.storeManagement.model.Store;

public class ReportData {
	
	private Store store;
	
	private Employee employee;
	
	private Map<Date,String> indicatorMap;

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

	public Map<Date, String> getIndicatorMap() {
		return indicatorMap;
	}

	public void setIndicatorMap(Map<Date, String> indicatorMap) {
		this.indicatorMap = indicatorMap;
	}
}
