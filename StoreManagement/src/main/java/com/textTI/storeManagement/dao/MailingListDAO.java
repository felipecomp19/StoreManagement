package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.MailingList;
import com.textTI.utils.HibernateUtil;

@Repository
public class MailingListDAO extends BaseDAO {

	public List<MailingList> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<MailingList> lists = session.createQuery("from MailingList").list();

		session.close();

		return lists;
	}

}
