package com.textTI.storeManagement.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.UserRoleDAO;
import com.textTI.storeManagement.model.UserRole;

@Component
public class UserRoleManager {
	
	@Autowired
	private UserRoleDAO userRoleDAO; 

	public List<UserRole> getAll() {
		return this.userRoleDAO.getAll();
	}

}
