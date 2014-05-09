package com.textTI.storeManagement.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_status")
public class Status extends BaseModel {
	
	private static final long serialVersionUID = -1438594602674529179L;
	
	private String description;
	
	private Date changeDate;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
}
