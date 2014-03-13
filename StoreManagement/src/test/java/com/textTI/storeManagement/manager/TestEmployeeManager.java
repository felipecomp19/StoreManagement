package com.textTI.storeManagement.manager;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.model.Employee;
import com.textTI.storeManagement.model.Store;

public class TestEmployeeManager extends BaseManagerTestCase {

	@Autowired
	private EmployeeManager employeeManager;
	
	@Autowired
	private StoreManager storeManager;
	
	@Test
	public void testCreateEmployee()
	{
		Store st = this.createAndInsertStore("Morana");
		
		Employee emp = new Employee();
		emp.setName("emp 1");
		emp.setEnabled(true);
		emp.setStore(st);
		
		try{
			this.employeeManager.insert(emp);
			Assert.assertTrue(emp.getId() > 0);
			Assert.assertTrue(emp.isEnabled());
		}catch(Exception e)
		{
			Assert.fail(e.getMessage());
		}finally
		{
			this.employeeManager.delete(emp);
			this.storeManager.delete(st);
		}
	}
	
	@Test
	public void testEditEmployee()
	{
		Store st = this.createAndInsertStore("Morana");
		
		Employee emp = new Employee();
		emp.setName("emp 1");
		emp.setEnabled(true);
		emp.setStore(st);
		
		try{
			this.employeeManager.insert(emp);
			
			Employee _emp = this.employeeManager.getById(emp.getId());
			Assert.assertNotNull(_emp);
			Assert.assertTrue(emp.getId() > 0);
			Assert.assertTrue(emp.isEnabled());
			Assert.assertEquals("emp 1", _emp.getName());
			
			String newName = "emp 2";
			_emp.setName(newName);
			
			this.employeeManager.update(_emp);
			_emp = null;
			
			_emp = this.employeeManager.getById(emp.getId());
			Assert.assertEquals(newName, _emp.getName());
		}catch(Exception e)
		{
			Assert.fail(e.getMessage());
		}finally
		{
			this.employeeManager.delete(emp);
			this.storeManager.delete(st);
		}
	}
	
	
	@Test
	public void testDisableEmployee()
	{
		Store st = this.createAndInsertStore("Morana");
		
		Employee emp = new Employee();
		emp.setName("emp 1");
		emp.setEnabled(true);
		emp.setStore(st);
		
		try{
			this.employeeManager.insert(emp);
			
			Employee _emp = this.employeeManager.getById(emp.getId());
			_emp.setEnabled(false);
			
			this.employeeManager.update(_emp);
			
			List<Employee> employees = this.employeeManager.getAll();
			Assert.assertTrue(employees.size() == 0);
			
		}catch(Exception e)
		{
			Assert.fail(e.getMessage());
		}finally
		{
			this.employeeManager.delete(emp);
			this.storeManager.delete(st);
		}
	}
}
