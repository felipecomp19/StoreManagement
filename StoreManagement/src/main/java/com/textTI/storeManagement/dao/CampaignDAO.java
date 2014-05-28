package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.Campaign;
import com.textTI.utils.HibernateUtil;

@Repository
public class CampaignDAO extends BaseDAO {

	protected static final Logger logger = LoggerFactory.getLogger(CampaignDAO.class);
	
	public Campaign getById(Long id) {

		return (Campaign) this.getById(id, Campaign.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Campaign> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		List<Campaign> campaigns = null;
		try{
			campaigns = session.createQuery("from Campaign").list();
		}catch(Exception ex){
			logger.error("Error in getAll()" + ex.getMessage());
			ex.printStackTrace();
		}finally{
			session.close();
		}

		return campaigns;
	}
}
