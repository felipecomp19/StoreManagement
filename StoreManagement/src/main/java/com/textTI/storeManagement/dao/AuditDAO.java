package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.Audit;
import com.textTI.storeManagement.utils.HibernateUtil;

@Repository
public class AuditDAO extends BaseDAO {

	public List<Audit> getTOP50() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<Audit> audits = session.createQuery("from Audit a ORDER BY a.id desc").setMaxResults(50).list();
		
		session.close();

		return audits;
	}

}
