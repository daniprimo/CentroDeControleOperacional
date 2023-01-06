package com.example.demo.exceptions.excessoes;

public class DocumentoExistenteException extends Exception {

	
private static final long serialVersionUID = 1L;
	
	private String message;
	
	public DocumentoExistenteException() {
	}

	public DocumentoExistenteException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
