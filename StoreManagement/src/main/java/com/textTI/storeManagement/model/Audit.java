package com.textTI.storeManagement.model;

import java.util.Date;

public class Audit extends BaseModel {

	private static final long serialVersionUID = 1L;
	
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
