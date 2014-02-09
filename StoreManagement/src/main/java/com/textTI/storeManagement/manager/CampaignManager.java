package com.textTI.storeManagement.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.CampaignDAO;
import com.textTI.storeManagement.model.Campaign;

@Component
public class CampaignManager {
	
	@Autowired
	private CampaignDAO campaignDAO;

	public void insert(Campaign newCampaign) 
	{
		newCampaign.setCreatedOn(new Date());
		this.campaignDAO.insert(newCampaign);
	}
	
	public void delete(Campaign campaign)
	{
		this.campaignDAO.delete(campaign);
	}

	public Campaign getById(Long id) 
	{
		return this.campaignDAO.getById(id);
	}

	public void update(Campaign newCampaign) 
	{
		this.campaignDAO.update(newCampaign);
	}
	
	public List<Campaign> getAll()
	{
		return this.campaignDAO.getAll();
	}
}
