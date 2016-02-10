package com.textTI.storeManagement.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.UserDAO;
import com.textTI.storeManagement.dao.UserRoleDAO;
import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.User;

@Component
public class UserManager {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserRoleDAO roleDAO;
	
	@Autowired
	private MessageSource msgSrc;
	
	public void insert(User user) {
		user.setActive(true);

		this.encodePassword(user);
		
		this.userDAO.insert(user);
	}

	public void delete(User user) {
		user.setActive(false);
		this.userDAO.update(user);
	}

	public User getById(Long id) {
		return (User) this.userDAO.getById(id, User.class);
	}

	public void update(User user) {

		User _user = this.getById(user.getId());
		if(user.getStores() == null)
			user.setStores(_user.getStores());
		if(user.getUserRole() == null)
			user.setUserRole(_user.getUserRole());
		if(user.getPassword() == null || user.getPassword() == "")
			user.setPassword(_user.getPassword());
		else
			this.encodePassword(user);
		
		this.userDAO.update(user);
	}

	private void encodePassword(User user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	}

	public List<User> getAll(Locale locale) {
		List<User> users = this.userDAO.getAll();
		
		for (User user : users) {
			user.getUserRole().setRoleTranslated(msgSrc.getMessage(user.getUserRole().getRole(),null, locale));
		}
		
		return users;
	}

	public User getByUserName(String userName) {
		return this.userDAO.getByUserName(userName);
	}

	public List<User> getAllByUser(User loggedUser, Locale locale) {
		//if theres is no store, return an empty list
		if(loggedUser.getStoresId() == null || loggedUser.getStoresId().size() == 0)
			return new ArrayList<User>();

		List<User> users = this.userDAO.getAllByUser(loggedUser.getStoresId());
		
		for (User user : users) {
			user.getUserRole().setRoleTranslated(msgSrc.getMessage(user.getUserRole().getRole(),null, locale));
		}		
		
		return users;
	}
}
