package com.textTI.storeManagement.converter;

import org.springframework.core.convert.converter.Converter;

import com.textTI.storeManagement.model.Employee;

public class EmployeeConverter implements Converter<String, Employee>{

	@Override
	public Employee convert(String id) {
		Employee emp = new Employee();
		emp.setId(Long.parseLong(id));
		
		return emp;
	}

}
