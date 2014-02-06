package com.textTI.storeManagement.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.MailingListDAO;
import com.textTI.storeManagement.model.MailingList;

@Component
public class MailingListManager {

	@Autowired
	private MailingListDAO mlDao;

	public void insert(MailingList mailingList) {
		this.mlDao.insert(mailingList);
	}

	public MailingList getById(Long id) {
		return (MailingList) this.mlDao.getById(id, MailingList.class);
	}

	public void delete(MailingList mailingList) {
		this.mlDao.delete(mailingList);

	}

	public void update(MailingList mailingList) {
		this.mlDao.update(mailingList);
	}

}
