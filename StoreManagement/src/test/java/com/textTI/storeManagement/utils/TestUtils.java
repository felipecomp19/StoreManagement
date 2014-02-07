package com.textTI.storeManagement.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.exception.ClientException;
import com.textTI.storeManagement.manager.ClientManager;
import com.textTI.storeManagement.manager.ClientTypeManager;
import com.textTI.storeManagement.manager.StoreManager;
import com.textTI.storeManagement.model.Address;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.ClientType;
import com.textTI.storeManagement.model.Store;

public class TestUtils {

	@Autowired
	private static ClientManager clientManager;
	
	@Autowired
	private static ClientTypeManager clientTypeManager;
	
	@Autowired
	private static StoreManager storeManager;
	
	public static Client createAndInsertOneClient() {
		Store st = new Store();
		st.setName("Morana");
		Set<Store> stores = new HashSet<Store>();
		stores.add(st);
		storeManager.insert(st);
		
		ClientType ct = new ClientType();
		ct.setName("PF");
		ct.setDescription("Pessoa Física");
		clientTypeManager.insert(ct);
		
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
		c1.setClientType(ct);

		try {
			clientManager.insert(c1);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		
		return c1;
	}
}
