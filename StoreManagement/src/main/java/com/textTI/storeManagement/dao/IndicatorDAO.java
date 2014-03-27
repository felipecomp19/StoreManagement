package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Query;
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

	public List<Indicator> getAllByUser(List<Long> storesId) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "SELECT DISTINCT i FROM Indicator i WHERE i.employee.store.id IN (:storesId)";
		Query query = session.createQuery(hql);

		query.setParameterList("storesId", storesId);
		
		@SuppressWarnings("unchecked")
		List<Indicator> indicators = query.list();
		
		session.close();
		
		return indicators;
	}

	public List<Indicator> getAllByUserAndMonth(List<Long> storesId, String selectedMonth, String selectedYear) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "SELECT DISTINCT i FROM Indicator i WHERE i.employee.store.id IN (:storesId) AND i.month = :month AND i.year = :year order by i.employee.store.name";
		
		Query query = session.createQuery(hql);

		query.setParameterList("storesId", storesId);
		query.setParameter("month", Integer.parseInt(selectedMonth));
		query.setParameter("year", Integer.parseInt(selectedYear));
		
		@SuppressWarnings("unchecked")
		List<Indicator> indicators = query.list();
		
		session.close();
		
		return indicators;
	}

}
