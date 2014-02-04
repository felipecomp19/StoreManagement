package com.textTI.storeManagement.exception;

public class ClientException extends Exception {

	private static final long serialVersionUID = 3062868169261411811L;
	
	private int code;
	
	public ClientException(int code)
	{
		super(Integer.toString(code));
		this.code = code;
	}
	
	public ClientException(int code, String message)
	{
		super(message);
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
