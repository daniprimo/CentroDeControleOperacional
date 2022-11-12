package com.example.demo.dto;

public class DefaultError {

	private int code;
	private String menssage;
	
	public DefaultError(int code, String menssage) {
		this.code = code;
		this.menssage = menssage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMenssage() {
		return menssage;
	}

	public void setMenssage(String menssage) {
		this.menssage = menssage;
	}
	
	
	
	
	
}
