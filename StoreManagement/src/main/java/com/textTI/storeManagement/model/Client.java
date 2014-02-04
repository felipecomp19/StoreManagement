package com.textTI.storeManagement.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_client")
public class Client extends BaseModel {

	private static final long serialVersionUID = 4340932225688335396L;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "cpf", length = 11, unique = true, nullable = false)
	private String cpf;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clientType", nullable = false)
	private ClientType clientType;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_client_store", 
				joinColumns = { @JoinColumn(name = "client_id", nullable = false, updatable = false) }, 
				inverseJoinColumns = { @JoinColumn(name = "store_id", nullable = false, updatable = false) })
	private Set<Store> stores;

	public Client() {
		super();
	};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public Set<Store> getStores() {
		return stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}
}
