package com.textTI.ecommerce.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.textTI.storeManagement.model.BaseModel;

@Entity
@Table(name="tb_ec_payment")
public class Payment extends BaseModel {
	
	private static final long serialVersionUID = 758334331510731958L;

	private Date date;
	
	private BigDecimal value;
	
	//private Plan plan;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account", nullable = false)
	private Account account;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
