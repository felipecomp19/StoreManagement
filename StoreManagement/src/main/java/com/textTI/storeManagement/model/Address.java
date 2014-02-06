package com.textTI.storeManagement.model;

@Entity
@Table(name = "tb_address")
public class Address {
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "number")
	private int number;
	
	@Column(name = "complement")
	private String complement;
	
	@Column(name = "cep", length = 20)
	private String cep;

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
}
