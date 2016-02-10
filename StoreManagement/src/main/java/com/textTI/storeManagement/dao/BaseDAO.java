package com.textTI.storeManagement.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.textTI.storeManagement.model.BaseModel;
import com.textTI.utils.HibernateUtil;

public abstract class BaseDAO {

	protected static final Logger logger = LoggerFactory
			.getLogger(BaseDAO.class);

	public BaseModel insert(BaseModel model) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		try {
			
			session.beginTransaction();
			model.setEnabled(true); // TODO remove IT. SHOULD BE IN SERVICE LAYER
			session.save(model);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			logger.error("error inserting model: " + e.getMessage());
		} finally {
			session.close();
		}

		return model;
	}

	public void delete(BaseModel model) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		try {
			session.beginTransaction();

			session.delete(model);

			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("error deleting model: " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public BaseModel update(BaseModel model) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		try {
			session.beginTransaction();

			session.merge(model);

			session.getTransaction().commit();

		} catch (Exception e) {
			logger.error("error updating model: " + e.getMessage());
		} finally {
			session.close();
		}
		
		return model;
	}

	public BaseModel getById(Long id, Class<?> modelClass) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		BaseModel model = null;

		try {

			model = (BaseModel) session.get(modelClass, id);

		} catch (Exception e) {
			logger.error("error getting model by id: " + e.getMessage());
		} finally {
			session.close();
		}

		return model;
	}
}
