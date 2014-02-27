package com.textTI.storeManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_imagen")
public class Imagen extends BaseModel {

	private static final long serialVersionUID = -5977756152109868543L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "fileName")
	private String fileName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
