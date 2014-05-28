package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.ClientType;
import com.textTI.utils.HibernateUtil;

@Repository
public class ClientTypeDAO extends BaseDAO {

	public ClientType getById(Long id) {
		return (ClientType) super.getById(id, ClientType.class);
	}

	public List<ClientType> getAll() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<ClientType> types = session.createQuery("from ClientType").list();

		session.close();

		return types;
	}
}
