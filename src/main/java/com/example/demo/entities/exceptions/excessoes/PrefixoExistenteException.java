package com.example.demo.entities.exceptions.excessoes;

public class PrefixoExistenteException extends Exception {
	
	
private static final long serialVersionUID = 1L;
	
	private String message;
	
	public PrefixoExistenteException() {
	}

	public PrefixoExistenteException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
