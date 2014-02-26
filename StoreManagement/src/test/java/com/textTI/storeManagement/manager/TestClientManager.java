package com.textTI.storeManagement.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.exception.ClientException;
import com.textTI.storeManagement.exception.ExceptionCode;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.ClientType;
import com.textTI.storeManagement.model.Store;

public class TestClientManager extends BaseManagerTestCase {
	
	@Autowired
	private ClientManager clientManager;
	@Autowired
	private ClientTypeManager clientTypeManager;
	@Autowired
	private StoreManager storeManager;
	
	private Client client;
	private ClientType cliType;
	private Set<Store> stores;
	
	@Before
	public void setUp()
	{
		this.cliType = this.createClientType();
		this.stores = this.createStore();
	}
	
	@After
	public void setDown()
	{
		if(this.client != null)
			this.clientManager.delete(this.client);
		
		this.clientTypeManager.delete(this.cliType);
		
		for (Store store : this.stores) {
			this.storeManager.delete(store);	
		}
	}
	
	@Test
	public void testCreateClient() throws Exception
	{
		logger.info("starting test createClient ...");
		this.createAndInsertClient("33067630807");
		
		Assert.assertTrue(this.client.getId() > 0);
	}

	@Test
	public void testGetClientById() throws Exception
	{
		logger.info("starting test testGetClientById ...");
		
		this.createAndInsertClient("33067630801");
		
		Client _cli = this.clientManager.getById(this.client.getId());
		Assert.assertEquals(this.client.getName(), _cli.getName());
		Assert.assertEquals(this.client.getCpf(), _cli.getCpf());
		
		logger.info("DONE testGetClientById!");
	}
	
	@Test
	public void testDeleteClient() throws Exception
	{
		logger.info("startin test deleteClient ...");
		
		this.createAndInsertClient("33067630802");
		
		Client _cli = this.clientManager.getById(this.client.getId());
		
		this.clientManager.delete(_cli);
		this.client = null;
	}
	
	@Test
	public void testEditClient() throws Exception
	{
		logger.info("startin test editClient ...");
		
		this.createAndInsertClient("33067630803");

		Client _cli = this.clientManager.getById(this.client.getId());
		String newName = "Flavia Ianzini Carnielli";
		_cli.setName(newName);
		
		this.clientManager.update(_cli);
		
		Client updatedCli = this.clientManager.getById(_cli.getId());
		Assert.assertEquals(newName, updatedCli.getName());
	}
	
	@Test
	public void testCreateClientWithExistingCPF() throws Exception
	{
		logger.info("startin test CreateClientwithExistingCPF ...");
		Client bkup = new Client(); //pequena gambi para não perder a referência do cliente que foi persistido
		try{
			this.createAndInsertClient("33067630804");
			 bkup = this.client;
			this.createAndInsertClient("33067630804");
		}catch(ClientException ce)
		{
			Assert.assertEquals(ExceptionCode.CPF_JA_EXISTE, ce.getCode());
		}catch (Exception e) {
			throw e;
		}finally
		{
			this.client = bkup;
		}
		
		logger.info("DONE!");
	}
	
	@Test
	public void testCreateClientWithoutCPF() throws Exception
	{
		logger.info("startin test CreateClientWithoutCPF ...");
		try{
			this.createAndInsertClient(null);
		}catch(ClientException ce)
		{
			Assert.assertEquals(ExceptionCode.CPF_INVALIDO, ce.getCode());
		}catch (Exception e) {
			throw e;
		}
		logger.info("DONE!");
	}
	
	@Test
	public void testCreateClientWithoutEmail() throws Exception
	{
		logger.info("startin test CreateClientWithoutEmail ...");
		try{
			this.createAndInsertClient("33067630805");
		}catch(ClientException ce)
		{
			Assert.assertEquals(ExceptionCode.EMAIL_INVALIDO, ce.getCode());
		}catch (Exception e) {
			Assert.fail(e.getMessage());
			throw e;
		}
		logger.info("DONE!");
	}
	
	private void createAndInsertClient(String cpf) throws ClientException {
		this.client = createClient(cpf);
		this.clientManager.insert(this.client);
	}
	
	private Client createClient(String cpf) {
		Client newClient = new Client();
		
		newClient.setName("Felipe Bottan Teixeira");
		newClient.setCpf(cpf);
		newClient.setEmail("felipecomp19@gmail.com");
		
		newClient.setClientType(this.cliType);
		newClient.setStores(this.stores);

		return newClient;
	}

	private ClientType createClientType() {
		logger.info("creating a client type");
		ClientType cliType = new ClientType();
		cliType.setName("PF");
		cliType.setDescription("Pessoa Física");
		this.clientTypeManager.insert(cliType);
		logger.info("DONE!");
		return cliType;
	}
	
	private Set<Store> createStore()
	{
		Set<Store> stores = new HashSet<Store>();
		
		logger.info("creating a store");
		Store st = new Store();
		st.setName("Morana");
		st.setDescription("Shopping Colinas");
		this.storeManager.insert(st);
		Assert.assertTrue(st.getId() > 0);
		stores.add(st);
		
		logger.info("Done!");
		
		return stores;
	}
}
