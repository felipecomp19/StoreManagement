package com.textTI.storeManagement.form;

import java.util.List;

import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.MailingList;

public class MailingListForm {
	
	private List<Client> allClients;
	
	private MailingList mailingList;

	public List<Client> getAllClients() {
		return allClients;
	}

	public void setAllClients(List<Client> allClients) {
		this.allClients = allClients;
	}

	public MailingList getMailingList() {
		return mailingList;
	}

	public void setMailingList(MailingList mailingList) {
		this.mailingList = mailingList;
	}
}
