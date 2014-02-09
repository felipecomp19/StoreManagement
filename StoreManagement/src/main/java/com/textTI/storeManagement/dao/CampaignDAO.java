package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.Campaign;
import com.textTI.storeManagement.utils.HibernateUtil;

@Repository
public class CampaignDAO extends BaseDAO {

	public Campaign getById(Long id) {

		return (Campaign) this.getById(id, Campaign.class);
	}
	
	public List<Campaign> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<Campaign> campaigns = session.createQuery("from Campaign").list();

		session.close();

		return campaigns;
	}
}
