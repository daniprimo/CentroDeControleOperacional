package com.example.demo.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

public class StandarErrorTest {
	
	public StandartError standar;
	
	@BeforeEach
	public void setupStard () {
		standar = new StandartError();
	}
	
	@Test
	public void verificarSeOSeteGetDoTimesTampEstafuncinandoCorretamente () {
		Instant iinstante = Instant.now();
		Instant feito = iinstante.plus(5, ChronoUnit.HOURS);
		standar.setTimestamp(feito);
		
		assertEquals(feito, standar.getTimestamp());
	}
	
	@Test
	public void verificarSeOSeteGetDoStatus() {
		Integer status = 404;
		standar.setStatus(status);
		
		assertEquals(status, standar.getStatus());
	}

	@Test
	public void verificarSeOSeteGetDoError() {
		String erro = "Errando de novo";
		standar.setError(erro);
		
		assertEquals(erro, standar.getError());
	}
	
	@Test
	public void verificarSeOSeteGetDoMessage() {
		String message = "Descrevendo o erro";
		standar.setMessage(message);
		
		assertEquals(message, standar.getMessage());
	}
	
	@Test
	public void verificarSeOSeteGetDoPath() {
		String path = "./coisa/coisas";
		standar.setPath(path);
		
		assertEquals(path, standar.getPath());
	}
	
	
}
