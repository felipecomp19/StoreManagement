package com.textTI.storeManagement.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.textTI.storeManagement.manager.StoreManager;
import com.textTI.storeManagement.model.Store;

public class StoreConverter implements Converter<String, Store>{

	@Autowired
	private StoreManager storeManager;
	
	@Override
	public Store convert(String id) {
		return this.storeManager.getById(Long.parseLong(id));
	}

}
