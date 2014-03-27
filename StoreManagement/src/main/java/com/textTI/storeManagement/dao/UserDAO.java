package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.User;
import com.textTI.storeManagement.utils.HibernateUtil;

@Repository
public class UserDAO extends BaseDAO {

	public List<User> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<User> users = session.createQuery("from User").list();

		session.close();

		return users;
	}

	public User getByUserName(String userName) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		User user = (User) session.createQuery("from User u where u.userName = :userName ").setParameter("userName", userName).uniqueResult();

		session.close();

		return user;
	}

	public List<User> getAllByUser(List<Long> storesId) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "SELECT DISTINCT u FROM User u JOIN u.stores us WHERE us.id IN (:storesId)";
		Query query = session.createQuery(hql);

		query.setParameterList("storesId", storesId);
		
		@SuppressWarnings("unchecked")
		List<User> users = query.list();
		
		session.close();
		
		return users;
	}
}
