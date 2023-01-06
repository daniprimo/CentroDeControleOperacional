package com.example.demo.exceptions;

import com.example.demo.exceptions.excessoes.ColetivoException;

public class EntityNotFoundException extends ColetivoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
