package com.textTI.storeManagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Simple JavaBean domain object with an id property. 
 * Used as a base class for objects needing this property.
 *
 * @author Felipe Teixeira
 * 
 */
@MappedSuperclass
public class BaseModel implements Serializable{

	private static final long serialVersionUID = -2163020351856207635L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "isEnabled")
	private boolean enabled;
	
	public BaseModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getIdAsString()
	{
		if(this.getId() == null)
			return "";
		
		return this.getId().toString();
	}
}
