package com.example.demo.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.entities.Coletivo;

public class ColetivoTest {

	public Coletivo coletivo;
	
	@BeforeEach
	public void iniciandoColetivo () {
		System.out.println("Chambraa");
		coletivo = new Coletivo();
	}
	
	private void popularClasse() {
		coletivo.setId(1l);
		coletivo.setPlaca("BKW-2462");
		coletivo.setPrefixo("21.501");
		coletivo.setModelo("PKS");
		coletivo.setDoc("052245");
		
	}
	
	@Test
	public void testDasFuncionalidadesDoId () {
		popularClasse();
		
		assertTrue(coletivo.getId() == 1l);
		assertFalse(coletivo.getId() == 21l);
		
	}
	

	@Test
	public void testDasFuncionalidadesDaPlaca () {
		popularClasse();

		assertTrue(coletivo.getPlaca() == "BKW-2462");
		assertFalse(coletivo.getPlaca() == "BKW-sss");
	}
	
	@Test
	public void testDasFuncionalidadesDoPrefixo () {
		popularClasse();

		assertTrue(coletivo.getPrefixo() == "21.501");
		assertFalse(coletivo.getPrefixo() == "sss");	
	}
	
	@Test
	public void testDasFuncionalidadesDoModelo () {
		popularClasse();

		assertTrue(coletivo.getModelo() == "PKS");
		assertFalse(coletivo.getModelo() == "aaa");

	}

	@Test
	public void testDasFuncionalidadesDoDocumento () {
		popularClasse();

		assertTrue(coletivo.getDoc() == "052245");
		assertFalse(coletivo.getDoc() == "052dd245");

	}




}
