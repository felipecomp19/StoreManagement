package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.utils.HibernateUtil;

@Repository
public class ClientDAO extends BaseDAO{
	
	public Client getById(Long id) {

		return (Client) this.getById(id, Client.class);
	}

	public Client getByCPF(String cpf) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		String hql = "FROM Client c WHERE c.cpf = :clientCPF)";
		Query query = session.createQuery(hql);

		query.setParameter("clientCPF", cpf);
		
		Client client = (Client) query.uniqueResult();

		session.close();

		return client;
	}

	public List<Client> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<Client> clients = session.createQuery("from Client").list();

		session.close();

		return clients;
	}
}
