package com.textTI.storeManagement.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.textTI.storeManagement.manager.UserRoleManager;
import com.textTI.storeManagement.model.UserRole;

public class UserRoleConverter implements Converter<String, UserRole>{

	@Autowired
	private UserRoleManager roleManager;
	
	@Override
	public UserRole convert(String id) {

		return this.roleManager.getById(Long.parseLong(id));
	}

}
