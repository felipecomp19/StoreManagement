package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Query;
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

	public List<Employee> getAllByUser(List<Long> storesId) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "SELECT DISTINCT e FROM Employee e WHERE e.store.id IN (:storesId)";
		Query query = session.createQuery(hql);

		query.setParameterList("storesId", storesId);
		
		@SuppressWarnings("unchecked")
		List<Employee> employees = query.list();
		
		session.close();
		
		return employees;
	}

}
