package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.utils.HibernateUtil;

@Repository
public class IndicatorDAO extends BaseDAO {

	public List<Indicator> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<Indicator> indicators = session.createQuery("from Indicator").list();

		session.close();

		return indicators;
	}

}
