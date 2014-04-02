package com.textTI.storeManagement.model.utils;

public class Month {

	private int code;
	
	private String desc;

	public Month(int code, String desc) {
		this.code =  code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
