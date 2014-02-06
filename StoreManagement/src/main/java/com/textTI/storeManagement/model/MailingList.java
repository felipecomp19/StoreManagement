package com.textTI.storeManagement.model;

import java.util.List;

@Entity
@Table(name = "tb_mailingList")
public class MailingList {

	@Column(name = "name")
	private String name;
	
	private List<Client> clients;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}
