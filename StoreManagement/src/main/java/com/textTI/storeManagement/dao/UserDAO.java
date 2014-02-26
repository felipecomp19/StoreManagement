package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

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
}
