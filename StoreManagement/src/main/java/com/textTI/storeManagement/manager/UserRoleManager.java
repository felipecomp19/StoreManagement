package com.textTI.storeManagement.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.UserRoleDAO;
import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.User;
import com.textTI.storeManagement.model.UserRole;

@Component
public class UserRoleManager {
	
	@Autowired
	private UserRoleDAO userRoleDAO; 
	
	@Autowired
	private MessageSource msgSrc;

	public List<UserRole> getAll(User loggedUder, Locale locale) {
		List<UserRole> roles = this.userRoleDAO.getAll();
		UserRole roleAdmin = null;
		for (UserRole userRole : roles) {
			userRole.setRoleTranslated(msgSrc.getMessage(userRole.getRole(),null, locale));
			
			if(userRole.getRole().trim().equals("ROLE_ADMIN"))
				roleAdmin = userRole; 
		}
		
		if(isManager(loggedUder))
			roles.remove(roleAdmin);
		
		return roles;
	}

	private boolean isManager(User loggedUder) {
		if(loggedUder.getUserRole().getRole().trim().equals("ROLE_MANAGER"))
			return true;
		return false;
	}

	public UserRole getById(long id) {
		return (UserRole) this.userRoleDAO.getById(id, UserRole.class);
	}

}
