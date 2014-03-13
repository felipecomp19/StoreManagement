package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.Store;
import com.textTI.storeManagement.model.chart.TotalClientsByMonth;
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

		session.close();

		return stores;
	}

	public List<TotalClientsByMonth> getTotalClientsByMonthInAYear(int year) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		Query query = session.createSQLQuery(
				"CALL getTotalClientsByMonthInAYear(:year)")
				.setResultTransformer(
						Transformers.aliasToBean(TotalClientsByMonth.class));
		
		query.setParameter("year", year);

		@SuppressWarnings("unchecked")
		List<TotalClientsByMonth> proc_result = query.list();

		session.close();

		return proc_result;
	}

	public List<Store> getAllByUser(List<Long> storesId) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "SELECT DISTINCT s FROM Store s WHERE s.id IN (:storesId)";
		Query query = session.createQuery(hql);

		query.setParameterList("storesId", storesId);
		
		@SuppressWarnings("unchecked")
		List<Store> stores = query.list();
		
		session.close();
		
		return stores;
	}
}
