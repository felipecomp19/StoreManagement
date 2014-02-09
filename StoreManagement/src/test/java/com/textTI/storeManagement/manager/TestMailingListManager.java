package com.textTI.storeManagement.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.ClientType;
import com.textTI.storeManagement.model.MailingList;
import com.textTI.storeManagement.model.Store;

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
		Set<Store> stores = this.createAndInsertStores();
		ClientType clientType = createAndInsertClientType();
		Client cli = this.createAndInsertOneClient("33067630807", stores,clientType); 
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
	
	@Test
	public void testGetAllMailingList()
	{
		List<Client> clients = new ArrayList<Client>();
		Set<Store> stores = createAndInsertStores();
		ClientType clientType = createAndInsertClientType();
		Client cli = this.createAndInsertOneClient("33067630801",stores,clientType); 
		Client cli2 = this.createAndInsertOneClient("33067630802",stores,clientType);
		clients.add(cli);
		clients.add(cli2);
		
		MailingList mailingList = this.createMailingList("Morana", clients);
		MailingList mailingList2 = this.createMailingList("Morana 2", clients);
		this.insertMailingList(mailingList);
		this.insertMailingList(mailingList2);
		
		List<MailingList> lists = this.mailingListManager.getAll();
		Assert.assertNotNull(lists);
		Assert.assertEquals(2, lists.size());
		for (MailingList ml : lists) {
			Assert.assertEquals(2,ml.getClients().size());
		}
		
		delete(mailingList);
		delete(mailingList2);
		
		this.clientManager.delete(cli);
		this.clientManager.delete(cli2);
		this.clientTypeManager.delete(clientType);
		for (Store store : stores) {
			this.storeManager.delete(store);
		}
	}

	private MailingList createMailingList(String listName, List<Client> clients) {
		MailingList ml = new MailingList();
		ml.setName(listName);
		ml.setClients(clients);
		ml.setDefaultFromEmail("felipecomp19@gmail.com");
		ml.setDefaultFromName("Felipe Teixeira");
		ml.setDefaultSubject("Promoção Morana");

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
}
