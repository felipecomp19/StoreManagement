package com.textTI.storeManagement.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.dao.UserDAO;
import com.textTI.storeManagement.model.User;

public class UserManager {

	@Autowired
	private UserDAO userDAO;
	
	public void insert(User user) {
		user.setActive(true);
		//TODO cypher!!!
		this.userDAO.insert(user);
	}

	public void delete(User user) {
		user.setActive(false);
		this.userDAO.update(user);
	}

}
