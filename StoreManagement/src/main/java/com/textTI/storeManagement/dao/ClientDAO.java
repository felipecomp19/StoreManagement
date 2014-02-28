package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.utils.HibernateUtil;

@Repository
public class ClientDAO extends BaseDAO {

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

	public List<Client> getClientsCreatedInAYear() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<Client> clients = session
		.createQuery(
				"select count(cli) as count "
				+"from Client as cli group by year(cli.createdOn), month(cli.createdOn)"
				+"ORDER BY cli.createdOn DESC").setMaxResults(12)
		.list();

		session.close();

		return clients;
	}
	
	public List<?> getTotalOfClientByStore()
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		Query q = session.createQuery("select cliStore.store_id, count(cliStore.store_id) as totalOfClients"
				+ " from db_storeManager_dev.tb_client_store as cliStore"
				+ " group by cliStore.store_id"); 
		
		List<?> result = q.list();
		
		session.close();
		
		return result;
	}

	public List<Client> getByClientTypeId(long cliTypeId) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
//		String hql = "SELECT c.name, c.cpf, c.email, c.telephone, c.cellPhone, c.createdOn, c.month_birthday"
//				+ " FROM Client c INNER JOIN c.clientType as ct WHERE ct.id= :cliTypeId)";
		String hql = "FROM Client c WHERE c.clientType.id = :cliTypeId)";
		Query query = session.createQuery(hql);
		query.setParameter("cliTypeId", cliTypeId);
		
		@SuppressWarnings("unchecked")
		List<Client> clients = query.list();
		
		session.close();
		
		return clients;
	}

	public List<Client> getByClientBirthdayMonth(int month) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "FROM Client c WHERE c.month_birthday = :month)";
		Query query = session.createQuery(hql);

		query.setParameter("month", month);
		
		@SuppressWarnings("unchecked")
		List<Client> clients = query.list();
		
		
		session.close();
		
		return clients;
	}
}
