package com.textTI.storeManagement.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.ClientType;
import com.textTI.storeManagement.model.Store;

public class TestStoreManager extends BaseManagerTestCase{
	
	@Autowired
	private StoreManager storeManager;
	
	@Test
	public void testCRUDStoreManager()
	{
		logger.info("starting CRUD StoreManager...");
		
		Store store = createStore("Morana", "Shopping Collinas");
		
		insertStore(store);

		getByID(store);
		
		edit(store);
		
		delete(store);
	}
	
	@Test
	public void testGetAllStores()
	{
		logger.info("start testGetAllStores...");
		
		Store store1 = createStore("Morana 1", "Shopping Collinas");
		Store store2 = createStore("Morana 2", "Shopping Vale Sul");
		
		this.insertStore(store1);
		this.insertStore(store2);
		
		List<Store> stores = this.storeManager.getAll();
		Assert.assertNotNull(stores);
		Assert.assertEquals(2, stores.size());
	}
	
	@Test
	public void testGetTotalOfClientsBySotre()
	{
		Client cli1 = null;
		Client cli2 = null;
		Client cli3 = null;
		Client cli4 = null;
		Client cli5 = null;
		Client cli6 = null;
		Client cli7 = null;
		Client cli8 = null;
		Set<Store> stores = new HashSet<Store>();
		ClientType cliType = this.createAndInsertClientType();
		try{
			stores = new HashSet<Store>();
			Store store1 = this.createAndInsertStore("Morana 1");
			stores.add(store1);
			
			cli1 = this.createAndInsertOneClient("1", stores, cliType);
			cli2 = this.createAndInsertOneClient("2", stores, cliType);
			cli3 = this.createAndInsertOneClient("3", stores, cliType);
			cli4 = this.createAndInsertOneClient("4", stores, cliType);
			cli5 = this.createAndInsertOneClient("5", stores, cliType);
			
			stores = new HashSet<Store>();
			Store store2 = this.createAndInsertStore("Morana 2"); 
			stores.add(store2);
			cli6 = this.createAndInsertOneClient("6", stores, cliType);
			cli7 = this.createAndInsertOneClient("7", stores, cliType);
			cli8 = this.createAndInsertOneClient("8", stores, cliType);
			
			List<Store> _stores = this.storeManager.getAll();
			for (Store store : _stores ) {
				if(store.getId() == store1.getId())
					Assert.assertEquals(5, store.getClientsSize());
				if(store.getId() == store2.getId())
					Assert.assertEquals(3, store.getClientsSize());
			}
			
		}catch(Exception e){
			Assert.fail();
		}finally
		{
			this.deleteClient(cli1);
			this.deleteClient(cli2);
			this.deleteClient(cli3);
			this.deleteClient(cli4);
			this.deleteClient(cli5);
			this.deleteClient(cli6);
			this.deleteClient(cli7);
			this.deleteClient(cli8);
			this.deleteClientType(cliType);
			this.deleteStores(stores);
		}
	}
	
	private Store createStore(String name, String description) {
		Store store = new Store();
		store.setName(name);
		store.setDescription(description);
		return store;
	}
	
	private void insertStore(Store store) {
		logger.info("insert store");
		this.storeManager.insert(store);
		Assert.assertTrue(store.getId() > 0);
		logger.info("insert store DONE!");
	}

	private void getByID(Store store) {
		logger.info("getById store...");
		Store _store = this.storeManager.getById(store.getId());
		Assert.assertEquals(store.getName(), _store.getName());
		Assert.assertEquals(store.getDescription(), _store.getDescription());
		logger.info("getById DONE!");
	}

	private void edit(Store store) {
		logger.info("edit store...");
		String newName = "Morana 2";
		String newDesc = "Guará";
		store.setName(newName);
		store.setDescription(newDesc);
		this.storeManager.update(store);
		Store newStore = this.storeManager.getById(store.getId());
		Assert.assertEquals(newName, newStore.getName());
		Assert.assertEquals(newDesc, newStore.getDescription());
		logger.info("edit DONE!");
	}

	private void delete(Store store) {
		logger.info("delete store...");
		this.storeManager.delete(store);
		logger.info("delte DONE!");
	}
}
