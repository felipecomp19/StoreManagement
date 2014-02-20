package com.textTI.storeManagement.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.model.Campaign;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.ClientType;
import com.textTI.storeManagement.model.MailingList;
import com.textTI.storeManagement.model.Store;

public class TestCampaignManager extends BaseManagerTestCase {

	private Set<Store> stores;
	
	private ClientType clientType;
	
	private Client client;
	
	private MailingList mailingList;
	
	@Autowired
	private CampaignManager campaignManager;
	
	@Test
	public void testCampaignCRUD()
	{
		logger.info("starting CRUD Campaign...");
		this.setUP();
		
		Campaign campaign = new Campaign();
		campaign.setName("Campanha teste");
		campaign.setDescription("Uma campanha para teste");
		campaign.setMailingList(this.mailingList);
		campaign.setEmailContent("<h1>Hello world!!!</h1>");
		
		logger.info("Create Campaign...");
		this.campaignManager.insert(campaign);
		Assert.assertTrue(campaign.getId() > 0);
		
		logger.info("get Campaign by id...");
		Campaign _campaign = this.campaignManager.getById(campaign.getId());
		String newName = "Nova campanha";
		String newDesc = "Nova desc";
		_campaign.setName(newName);
		_campaign.setDescription(newDesc);
		
		logger.info("Update Campaign...");
		this.campaignManager.update(_campaign);
		campaign = this.campaignManager.getById(_campaign.getId());
		Assert.assertEquals(newDesc, campaign.getDescription());
		Assert.assertEquals(newName, campaign.getName());
		
		logger.info("Delete Campaign...");
		this.campaignManager.delete(campaign);
		
		this.setDown();
	}

	private void setUP() {
		logger.info("SetUP...");
		List<Client> clients = new ArrayList<Client>();
		this.stores = createAndInsertStores();
		this.clientType = createAndInsertClientType();
		this.client = this.createAndInsertOneClient("33067630807", stores,clientType); 
		clients.add(this.client);
		this.mailingList = createAndInsertMailingList("Morana", clients);
	}

	private void setDown() 
	{
		logger.info("SetDown...");
		this.deleteMailingList(this.mailingList);
		this.deleteClient(this.client);
		this.deleteClientType(this.clientType);
		this.deleteStores(this.stores);
	}
}
