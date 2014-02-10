package com.textTI.storeManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_user_roles")
public class UserRole extends BaseModel {

	private static final long serialVersionUID = -8781112592960387672L;

	@Column(name="role")
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
