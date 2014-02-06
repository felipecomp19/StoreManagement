package com.textTI.storeManagement.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_mailingList")
public class MailingList extends BaseModel {

	private static final long serialVersionUID = 530674444960715214L;

	@Column(name = "name")
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_mailingList_client", 
				joinColumns = { @JoinColumn(name = "mailingList_id", updatable = false) }, 
				inverseJoinColumns = { @JoinColumn(name = "client_id", updatable = false) })
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
