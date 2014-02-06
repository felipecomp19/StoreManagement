package com.textTI.storeManagement.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.ClientDAO;
import com.textTI.storeManagement.exception.ClientException;
import com.textTI.storeManagement.exception.ExceptionCode;
import com.textTI.storeManagement.model.Client;

@Component
public class ClientManager {

	@Autowired
	private ClientDAO clientDAO;
	
	public void insert(Client newClient) throws ClientException 
	{
		this.validate(newClient);
		newClient.setCreatedOn(new Date());
		this.clientDAO.insert(newClient);
	}
	
	public void delete(Client client)
	{
		this.clientDAO.delete(client);
	}

	public Client getById(Long id) 
	{
		return this.clientDAO.getById(id);
	}

	public void update(Client newClient) 
	{
		this.clientDAO.update(newClient);
	}
	
	private void validate(Client newClient) throws ClientException {
		if(newClient.getCpf() == null)
			throw new ClientException(ExceptionCode.CPF_INVALIDO);
		if(newClient.getEmail() == null)
			throw new ClientException(ExceptionCode.EMAIL_INVALIDO);

		Client _cli = this.clientDAO.getByCPF(newClient.getCpf());
		if(_cli != null)
			throw new ClientException(ExceptionCode.CPF_JA_EXISTE);
	}

	public List<Client> getAll() {
		return this.clientDAO.getAll();
	}	
}
