package com.textTI.storeManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_address")
public class Address extends BaseModel {
	
	private static final long serialVersionUID = 7849037788850367722L;

	@Column(name = "street")
	private String street;
	
	@Column(name = "number")
	private int number;
	
	@Column(name = "complement")
	private String complement;
	
	@Column(name = "cep", length = 20)
	private String cep;
	
	@Column(name = "neighbourhood", length = 50)
	private String neighbourhood;
	
	@Column(name = "city", length = 50)
	private String city;
	
	@Column(name = "state", length = 50)
	private String state;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
