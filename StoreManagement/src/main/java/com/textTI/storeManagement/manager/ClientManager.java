package com.textTI.storeManagement.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.ClientDAO;
import com.textTI.storeManagement.dao.StoreDAO;
import com.textTI.storeManagement.exception.ClientException;
import com.textTI.storeManagement.exception.ExceptionCode;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.User;

@Component
public class ClientManager {

	@Autowired
	private ClientDAO clientDAO;
	
	@Autowired
	private StoreDAO storeDAO;
	
	public void insert(Client newClient) throws ClientException 
	{
		this.validate(newClient);
		newClient.setCreatedOn(new Date());
		this.parseCPF(newClient);
		this.clientDAO.insert(newClient);
	}

	private void parseCPF(Client newClient) {
		String cpf = newClient.getCpf().replace(".", "").replace("-",	"");
		newClient.setCpf(cpf);
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
		this.parseCPF(newClient);
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

	public List<Client> getClientsCreatedInAYear() {
		return this.clientDAO.getClientsCreatedInAYear();
	}

	public Client getClientByCPF(String cpf) {
		return this.clientDAO.getByCPF(cpf);
	}
	
	public Map<String, String> getTotalOfClientsByStore()
	{
		Map<String,String> result = new HashMap<String,String>();
		
		return result;
		
	}

	public List<Client> getByClientTypeId(long cliTypeId) {
		return this.clientDAO.getByClientTypeId(cliTypeId);
	}

	public List<Client> getByClientBirthdayMonth(int month) {
		return this.clientDAO.getByClientBirthdayMonth(month);
	}

	public List<Client> getByClientsByStoreId(long storeId) {
		
		Set<Client> clients = this.storeDAO.getById(storeId).getClients(); 
		
		return new ArrayList<Client>(clients); 
	}

	public List<Client> getAllByUser(User loggedUser) {

		return this.clientDAO.getAllByUser(loggedUser.getStoresId());
	}
}
