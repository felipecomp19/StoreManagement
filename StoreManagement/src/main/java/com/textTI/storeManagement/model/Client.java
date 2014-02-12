package com.textTI.storeManagement.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_client")
public class Client extends BaseModel {

	private static final long serialVersionUID = 4340932225688335396L;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "cpf", length = 15, unique = false, nullable = false)
	private String cpf;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "createdOn")
	private Date createdOn;
	
	@Column(name="birthday")
	private Date birthday;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clientType", nullable = false)
	private ClientType clientType;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_client_store", 
				joinColumns = { @JoinColumn(name = "client_id", nullable = false, updatable = false) }, 
				inverseJoinColumns = { @JoinColumn(name = "store_id", nullable = false, updatable = false) })
	private Set<Store> stores;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "address")
	private Address address;
	
	@Transient
	private int count;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getCreatedOn() {
		return createdOn;
	}
	
	public String getFormatedCreatedOn() {
		if(this.createdOn != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			return sdf.format(createdOn);
		}
		return "";
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getFormatedBirthday() {
		if(this.birthday != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
			return sdf.format(birthday);
		}
		return "";
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
