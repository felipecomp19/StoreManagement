package com.textTI.ecommerce.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.textTI.storeManagement.model.BaseModel;
import com.textTI.storeManagement.model.User;

@Entity
@Table(name="tb_ec_account")
public class Account extends BaseModel {

	private static final long serialVersionUID = -5765655312498138356L;

	//name that will be used as the logo of the system
	private String name;
	
	@OneToMany(mappedBy="account")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<User> users;
	
	@ManyToOne
	@JoinColumn(name = "plan")
	private Plan plan;
	
	@OneToMany(mappedBy="account")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Payment> payments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
}
