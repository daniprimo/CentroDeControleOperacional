package com.example.demo.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class defaultTest {
	
	DefaultError defaultClas;
	
	@BeforeEach
	public void setupClass () {
		defaultClas = new DefaultError(404, "Ta de palhaçada");
	}
	
	@Test
	public void testandFuncionaidadesDoCode () {
		assertTrue(defaultClas.getCode() == 404);
		assertFalse(defaultClas.getCode() == 414);
	}
	
	@Test
	public void testandoFuncionalidadesDoMessage () {
		assertTrue(defaultClas.getMenssage() == "Ta de palhaçada");
		assertFalse(defaultClas.getMenssage() == "Saaai lokooo");
	}
	
	@Test
	public void testandFuncionaidadesSetDoCode () {
		defaultClas.setCode(8);
		
		assertTrue(defaultClas.getCode() == 8);
		assertFalse(defaultClas.getCode() == 414);
	}

	@Test
	public void testandoFuncionalidadesSetDaMessage () {
		defaultClas.setMenssage("Avaliando a menssagem");
		assertTrue(defaultClas.getMenssage() == "Avaliando a menssagem");
		assertFalse(defaultClas.getMenssage() == "Saaai lokooo");
	}
	
}
