package com.textTI.storeManagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_audit")
public class Audit extends BaseModel {

	private static final long serialVersionUID = 5871263944802358192L;

	@Column(name="operation")
	private String Operation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user", nullable = false)
	private User user;
	
	@Column(name="date")
	private Date date;

	public String getOperation() {
		return Operation;
	}

	public void setOperation(String operation) {
		Operation = operation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
