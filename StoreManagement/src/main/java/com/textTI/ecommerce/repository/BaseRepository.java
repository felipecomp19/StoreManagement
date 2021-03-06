package com.textTI.ecommerce.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.textTI.storeManagement.model.BaseModel;
import com.textTI.utils.HibernateUtil;

public class BaseRepository {

	public BaseModel insert(BaseModel model) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		session.save(model);

		session.getTransaction().commit();

		session.close();

		return model;
	}

	public void delete(BaseModel model) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		session.beginTransaction();

		session.delete(model);

		session.getTransaction().commit();

		session.close();
	}

	public BaseModel update(BaseModel model) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		session.beginTransaction();

		session.merge(model);

		session.getTransaction().commit();

		session.close();
		return model;
	}

	public BaseModel getById(Long id, Class<?> modelClass) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		BaseModel model = (BaseModel) session.get(modelClass, id);

		session.close();
		return model;
	}
}
