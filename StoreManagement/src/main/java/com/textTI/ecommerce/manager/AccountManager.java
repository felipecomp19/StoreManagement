package com.textTI.ecommerce.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.textTI.ecommerce.models.Account;
import com.textTI.ecommerce.repository.AccountRepository;

@Component
public class AccountManager {

	@Autowired
	private AccountRepository accountRepository;
	
	public void update(Account account) {
		Account _ac = (Account) this.accountRepository.getById(account.getId(), Account.class);
		
		account.setPlan(_ac.getPlan());
		account.setPayments(_ac.getPayments());
		account.setEnabled(_ac.isEnabled());
		
		this.accountRepository.update(account);
	}

	public void insert(Account account) {
		System.out.println("Não foi implementado");
	}
}
