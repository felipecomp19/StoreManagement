package com.textTI.storeManagement.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.ClientTypeDAO;
import com.textTI.storeManagement.model.ClientType;

@Component
public class ClientTypeManager {
	
	@Autowired
	private ClientTypeDAO clientTypeDAO;

	public void insert(ClientType newCliType) {
		this.clientTypeDAO.insert(newCliType);
	}
	
	public void update(ClientType newClientType)
	{
		this.clientTypeDAO.update(newClientType);
	}
	
	public void delete(ClientType clientType)
	{
		this.clientTypeDAO.delete(clientType);
	}
	
	public ClientType getById(Long id)
	{
		return this.clientTypeDAO.getById(id);
	}

	public List<ClientType> getAll() {
		return this.clientTypeDAO.getAll();
	}
}
