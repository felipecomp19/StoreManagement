package com.textTI.storeManagement.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.exception.ClientException;
import com.textTI.storeManagement.model.Address;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.ClientType;
import com.textTI.storeManagement.model.MailingList;
import com.textTI.storeManagement.model.Store;
import com.textTI.storeManagement.utils.TestUtils;

public class TestMailingListManager extends BaseManagerTestCase {
	
	@Autowired
	private MailingListManager mailingListManager; 
	
	@Autowired
	private ClientManager clientManager;
	
	@Autowired
	private ClientTypeManager clientTypeManager;
	
	@Autowired
	private StoreManager storeManager;
	
	@Test
	public void testMailingListManagerCRUD()
	{
		logger.info("starting CRUD MailingList...");
		
		List<Client> clients = new ArrayList<Client>();
		
		Set<Store> stores = createAndInsertStores();
		ClientType clientType = createAndInsertClientType();
		Client cli = this.createAndInsertOneClient(stores,clientType); 
		
		clients.add(cli);
		
		MailingList mailingList = createMailingList("Morana", clients);
		
		insertMailingList(mailingList);

		mailingList = getByID(mailingList);
		
		edit(mailingList);
		
		delete(mailingList);
		
		this.clientManager.delete(cli);
		this.clientTypeManager.delete(clientType);
		for (Store store : stores) {
			this.storeManager.delete(store);
		}
	}

	private MailingList createMailingList(String string, List<Client> clients) {
		MailingList ml = new MailingList();
		ml.setName(string);
		ml.setClients(clients);
		return ml;
	}
	
	private void insertMailingList(MailingList mailingList) {
		logger.info("inserting mailing list");
		this.mailingListManager.insert(mailingList);
		Assert.assertTrue(mailingList.getId() > 0);
		logger.info("done");
	}
	
	private MailingList getByID(MailingList mailingList) {
		logger.info("getById Mailing list ..");
		MailingList ml = this.mailingListManager.getById(mailingList.getId());
		Assert.assertNotNull(ml);
		logger.info("done");
		return ml;
	}
	
	private void edit(MailingList mailingList) {
		logger.info("updating mailingList");
		String newName = "Morana 2";
		mailingList.setName(newName);
		this.mailingListManager.update(mailingList);
		
		MailingList _ml = this.mailingListManager.getById(mailingList.getId());
		Assert.assertEquals(newName, _ml.getName());
		logger.info("done");
	}
	
	private void delete(MailingList mailingList) {
		logger.info("deleting maillist");
		this.mailingListManager.delete(mailingList);
		logger.info("DONE");
	}
	
	private Client createAndInsertOneClient(Set<Store> stores, ClientType clientType) {
		
		Address address = new Address();
		address.setStreet("Rua 1");
		address.setNumber(1);
		address.setCep("12421410");
		
		Client c1 = new Client();
		c1.setName("Felipe Teixeira");
		c1.setCpf("33067630807");
		c1.setEmail("felipecomp19@gmail.com");
		c1.setAddress(address);
		c1.setStores(stores);
		c1.setClientType(clientType);

		try {
			this.clientManager.insert(c1);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		
		return c1;
	}

	private ClientType createAndInsertClientType() {
		ClientType client = new ClientType();
		client.setName("PF");
		client.setDescription("Pessoa Física");
		this.clientTypeManager.insert(client);
		return client;
	}

	private Set<Store> createAndInsertStores() {
		Store st = new Store();
		st.setName("Morana");
		Set<Store> stores = new HashSet<Store>();
		stores.add(st);
		this.storeManager.insert(st);
		return stores;
	}
}
