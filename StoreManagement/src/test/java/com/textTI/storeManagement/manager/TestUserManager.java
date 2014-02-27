package com.textTI.storeManagement.manager;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.model.User;
import com.textTI.storeManagement.model.UserRole;

public class TestUserManager extends BaseManagerTestCase{
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private UserRoleManager userRoleManager;
	
	@Test
	public void testCreateUser()
	{
		//the database should have two roles ROLE_ADMIN e ROLE_USER
		UserRole role = this.userRoleManager.getAll(Locale.ENGLISH).get(0);
		
		User user = new User();
		user.setUserName("felipecomp19");
		user.setUserRole(role);
		user.setPassword("123456");
		
		this.userManager.insert(user);
		Assert.assertTrue(user.getId() > 0);
		
		//getById
		User _user = this.userManager.getById(user.getId());
		Assert.assertNotNull(_user);
		
		//Update
		UserRole newRole = this.userRoleManager.getAll(Locale.ENGLISH).get(1);
		_user.setUserRole(newRole);
		this.userManager.update(_user);
		
		user = this.userManager.getById(_user.getId());
		Assert.assertEquals(newRole.getId(), user.getUserRole().getId());
		
		
		this.userManager.delete(user);
	}
}
