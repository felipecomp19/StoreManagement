package com.textTI.storeManagement.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.UserRole;
import com.textTI.storeManagement.utils.HibernateUtil;

@Repository
public class UserRoleDAO extends BaseDAO {

	public List<UserRole> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<UserRole> roles = session.createQuery("from UserRole").list();
		
		session.close();

		return roles;
	}

}
