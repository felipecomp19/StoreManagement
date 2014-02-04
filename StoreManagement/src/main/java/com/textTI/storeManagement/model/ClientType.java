package com.textTI.storeManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_clientType")
public class ClientType extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	@Column(name="name", nullable=false, unique=true)	
	private String name;

	@Column(name="description")
	private String description;
	
	public ClientType() {
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
}
