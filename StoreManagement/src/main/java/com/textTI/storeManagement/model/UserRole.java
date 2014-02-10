package com.textTI.storeManagement.model;

@Entity
@Table(name="tb_user_roles")
public class UserRole extends BaseModel {

	@Column(name="role")
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
