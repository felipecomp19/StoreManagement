package com.textTI.storeManagement.manager;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.UserRoleDAO;
import com.textTI.storeManagement.model.UserRole;

@Component
public class UserRoleManager {
	
	@Autowired
	private UserRoleDAO userRoleDAO; 
	
	@Autowired
	private MessageSource msgSrc;

	public List<UserRole> getAll(Locale locale) {
		List<UserRole> roles = this.userRoleDAO.getAll();
		
		for (UserRole userRole : roles) {
			userRole.setRole(msgSrc.getMessage(userRole.getRole(),null, locale));
		}
		
		return roles;
	}

	public UserRole getById(long id) {
		return (UserRole) this.userRoleDAO.getById(id, UserRole.class);
	}

}
