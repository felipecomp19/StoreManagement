package com.textTI.storeManagement.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_status")
public class Status extends BaseModel {
	
	private static final long serialVersionUID = -1438594602674529179L;
	
	private String description;
	
	public Status() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
