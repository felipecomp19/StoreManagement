package com.textTI.storeManagement.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_store")
public class Store extends BaseModel {

	private static final long serialVersionUID = 2240699832588471227L;
	
	@Column(name="name", nullable=false)	
	private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "telephone", length=30)
	private String telephone;

	@ManyToMany(mappedBy = "stores")
	private Set<Client> clients;
	
	public Store() {
		super();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
}