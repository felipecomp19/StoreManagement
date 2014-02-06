package com.textTI.storeManagement.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.textTI.storeManagement.manager.ClientTypeManager;
import com.textTI.storeManagement.model.ClientType;

public class ClientTypeConverter implements Converter<String, ClientType>{

	@Autowired
	private ClientTypeManager cliTypeManager;
	
	@Override
	public ClientType convert(String id) {

		return this.cliTypeManager.getById(Long.parseLong(id));
	}

}
