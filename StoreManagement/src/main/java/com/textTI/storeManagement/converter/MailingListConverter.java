package com.textTI.storeManagement.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.textTI.storeManagement.manager.MailingListManager;
import com.textTI.storeManagement.model.MailingList;

public class MailingListConverter implements Converter<String, MailingList> {

	@Autowired
	private MailingListManager mailingListManager;
	
	@Override
	public MailingList convert(String id) {
		
		return this.mailingListManager.getById(Long.parseLong(id));
	}

}
