package com.textTI.storeManagement.manager;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.UserDAO;
import com.textTI.storeManagement.dao.UserRoleDAO;
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
		if(user.getPassword() == null || user.getPassword() == "")
		{
			User _user = this.getById(user.getId());
			user.setPassword(_user.getPassword());
		}else{
			this.encodePassword(user);
		}
		
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
	
}
