package com.example.quiz_1140818.response;

public class BasicRes {

	private int code;
	
	private String message;

	private boolean admin;
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BasicRes() {
		super();
		
	}

	public BasicRes(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BasicRes(int code, String message, boolean admin) {
		super();
		this.code = code;
		this.message = message;
		this.admin = admin;
	}
	
	
	
	
	
}
