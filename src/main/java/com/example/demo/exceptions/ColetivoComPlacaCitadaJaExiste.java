package com.example.demo.exceptions;

import com.example.demo.exceptions.excessoes.ColetivoException;

public class ColetivoComPlacaCitadaJaExiste extends ColetivoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColetivoComPlacaCitadaJaExiste(String message) {
		super(message);
	}

}
