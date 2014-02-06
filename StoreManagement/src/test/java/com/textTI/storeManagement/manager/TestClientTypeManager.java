package com.textTI.storeManagement.manager;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.model.ClientType;

public class TestClientTypeManager extends BaseManagerTestCase {
	
	@Autowired
	private ClientTypeManager cliTypaManager;
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testClientTypeCRUD () throws Exception
	{
		logger.info("starting testClientTypeCRUD ...");
		
		ClientType cliType = new ClientType();
		cliType.setName("normal");
		cliType.setDescription("cliente normal");
		
		//create
		createClientType(cliType);
		
		cliType = this.cliTypaManager.getById(cliType.getId());
		
		//update
		updateClientType(cliType);
		
		//delete
		deleteClientType(cliType);
	}
	
	private void deleteClientType(ClientType cliType) {
		logger.info("deleting clientType");
		this.cliTypaManager.delete(cliType);
		logger.info("DONE!");
	}

	private void updateClientType(ClientType cliType) {
		logger.info("updating clientType");
		String newName = "Novo tipo";
		String newDesc = "Nova descrição";
		cliType.setName(newName);
		cliType.setDescription(newDesc);
		
		this.cliTypaManager.update(cliType);
		
		ClientType _ct = this.cliTypaManager.getById(cliType.getId());
		Assert.assertEquals(newName, _ct.getName());
		Assert.assertEquals(newDesc, _ct.getDescription());
		Assert.assertEquals(cliType.getId(), _ct.getId());
		
		logger.info("DONE!");
	}

	private void createClientType(ClientType cliType) {
		logger.info("creating clientType");
		this.cliTypaManager.insert(cliType);
		Assert.assertTrue(cliType.getId() > 0);
		logger.info("DONE!");
	}
	
	@Test
	public void getAllClientType()
	{
		ClientType cliType = new ClientType();
		cliType.setName("normal 1");
		cliType.setDescription("cliente normal");
		
		//create 1
		createClientType(cliType);
		
		ClientType cliType2 = new ClientType();
		cliType2.setName("normal 2");
		cliType2.setDescription("cliente normal 2");
		
		//create 2
		createClientType(cliType2);
		
		List<ClientType> types = this.cliTypaManager.getAll();
		Assert.assertNotNull(types);
		Assert.assertEquals(2, types.size());
		
		this.cliTypaManager.delete(cliType);
		this.cliTypaManager.delete(cliType2);
	}
}
