package com.textTI.storeManagement.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.EmployeeDAO;
import com.textTI.storeManagement.model.Employee;
import com.textTI.storeManagement.model.User;

@Component
public class EmployeeManager {
	
	@Autowired
	private EmployeeDAO employeeDAO;

	public List<Employee> getAll() {
		return this.employeeDAO.getAll();
	}

	public Employee getById(long id) {

		return (Employee) this.employeeDAO.getById(id, Employee.class);
	}

	public void update(Employee employee) {
		this.employeeDAO.update(employee);
	}

	public void insert(Employee employee) {
		employee.setEnabled(true);
		this.employeeDAO.insert(employee);
	}

	public void delete(Employee emp) {
		this.employeeDAO.delete(emp);
	}

	public List<Employee> getAllByUser(User loggedUser) {
		return this.employeeDAO.getAllByUser(loggedUser.getStoresId());
	}
}
