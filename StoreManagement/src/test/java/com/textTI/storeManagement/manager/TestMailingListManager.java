package com.textTI.storeManagement.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.textTI.storeManagement.model.MailingList;
import com.textTI.storeManagement.model.Store;

public class TestMailingListManager extends BaseManagerTestCase {
	
	@Autowired
	private MailingListManager mailingListManager; 
	
	@Test
	public void testMailingListManagerCRUD()
	{
		logger.info("starting CRUD MailingList...");
		
		MailingList mailingList = createMailingList("Morana");
		
		insertMailingList(mailingList);

		getByID(mailingList);
		
		edit(mailingList);
		
		delete(mailingList);
		
	}

	private MailingList createMailingList(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void insertMailingList(MailingList mailingList) {
		// TODO Auto-generated method stub
		
	}
	
	private void getByID(MailingList mailingList) {
		// TODO Auto-generated method stub
		
	}
	
	private void edit(MailingList mailingList) {
		// TODO Auto-generated method stub
		
	}
	
	private void delete(MailingList mailingList) {
		// TODO Auto-generated method stub
		
	}
}
