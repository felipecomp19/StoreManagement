package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.Imagen;
import com.textTI.storeManagement.utils.HibernateUtil;

@Repository
public class ImagenDAO extends BaseDAO {

	public List<Imagen> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<Imagen> imagens = session.createQuery("from Imagen").list();

		session.close();

		return imagens;
	}
}
