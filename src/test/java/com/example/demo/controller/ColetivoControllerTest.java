package com.example.demo.controller;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entities.Coletivo;
import com.example.demo.service.ColetivoService;

@RunWith(SpringRunner.class)
public class ColetivoControllerTest {
	
	private static final String COLETIVO_NÃO_ENCONTRADO2 = "Coletivo não encontrado";

	private static final String PREFIXO_JA_EXISTENTE = "Prefixo Ja existente";

	private static final String PLACA_JA_EXISTENTE = "Placa ja existente";

	private static final String COLETIVO_NÃO_FOI_SALVO = "Coletivo não foi salvo";

	private static final String COLETIVO_NÃO_ENCONTRADO = COLETIVO_NÃO_ENCONTRADO2;

	private static final String DOCUMENTO = "500125";

	private static final String MODELO = "pks";

	private static final String PREFIXO = "21.501";

	private static final String PLACA = "DGT-5245";

	private static final long _ID = 7l;
	
	@InjectMocks
	private ColetivoController coletivoController;
	
	@Mock
	private ColetivoService coletivoService;
	
	private Coletivo coletivo;
	
	private Optional<Coletivo> optionalColetivo;
	
	@BeforeEach
	void setUp () {
		MockitoAnnotations.openMocks(this);
		startColetivo();
	}
	
	
	
	
	private void startColetivo () {
		coletivo = new Coletivo(_ID, PLACA, PREFIXO, MODELO, DOCUMENTO);
		optionalColetivo = Optional.of(new Coletivo(_ID, PLACA, PREFIXO, MODELO, DOCUMENTO));
	}

	
	
	
}
