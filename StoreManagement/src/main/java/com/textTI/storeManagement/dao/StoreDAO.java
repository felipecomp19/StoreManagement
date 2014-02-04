package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.Store;
import com.textTI.storeManagement.utils.HibernateUtil;

@Repository
public class StoreDAO extends BaseDAO {

	public Store getById(Long id) {
		return (Store) super.getById(id, Store.class);
	}

	public List<Store> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<Store> stores = session.createQuery("from Store").list();
		
		//TODO return the clients

		session.close();

		return stores;
	}
}
