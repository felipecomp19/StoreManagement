package com.textTI.storeManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_users")
public class User extends BaseModel {

	private static final long serialVersionUID = -5692288105690927661L;

	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name = "userName", nullable = false)
	private String userName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "isActive", nullable = false)
	private boolean active;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userRole", nullable = false)
	private UserRole userRole;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean isActive) {
		this.active = isActive;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}
