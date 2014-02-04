package com.textTI.storeManagement.manager;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
