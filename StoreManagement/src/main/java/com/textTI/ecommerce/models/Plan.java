package com.textTI.ecommerce.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.textTI.storeManagement.model.BaseModel;

@Entity
@Table(name="tb_ec_plan")
public class Plan extends BaseModel{

	private static final long serialVersionUID = -1619733460938586750L;
	
	private String name;
	
	private String description;
	
	private BigDecimal value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
