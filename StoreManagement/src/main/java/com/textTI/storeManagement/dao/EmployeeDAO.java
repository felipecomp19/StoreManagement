package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.Employee;
import com.textTI.storeManagement.utils.HibernateUtil;

@Repository
public class EmployeeDAO extends BaseDAO {

	public List<Employee> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<Employee> employees = session.createQuery("from Employee e where e.enabled = true").list();

		session.close();

		return employees;
	}

}
