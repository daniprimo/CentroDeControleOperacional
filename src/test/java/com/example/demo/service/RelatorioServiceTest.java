package com.example.demo.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.coletivoDtos.RelatorioColetivo;
import com.example.demo.repository.RelatorioRepository;

@SpringBootTest
class RelatorioServiceTest {
	
	@Mock
	private RelatorioRepository relatorioRepository;
	
	@InjectMocks
	private RelatorioService relatorioService;
	
	private RelatorioColetivo relatorioColetivo;
	
	private List<RelatorioColetivo> list = new ArrayList<RelatorioColetivo>();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);	
		list.add(relatorioColetivo);
	}

	@Test
	void testListarRelatorio() {
		when(relatorioRepository.findRelatorioColetivoRefGaragem()).thenReturn(list);
		List<RelatorioColetivo> relatorios = relatorioService.listarRelatorio();
		
		assertNotNull(relatorios);
	}

	@Test
	void testRelatorioDosColetivosDaGaragemFilial() {
		when(relatorioRepository.findRelatorioColetivoGaragemFilial()).thenReturn(list);
		List<RelatorioColetivo> list2 = relatorioService.relatorioDosColetivosDaGaragemFilial();
		
		assertNotNull(list2);
	}

	@Test
	void testRelatorioDosColetivosDaGaragemMatriz() {
		when(relatorioRepository.findRelatorioColetivoGaragemMatriz()).thenReturn(list);
		List<RelatorioColetivo> relatorios = relatorioService.relatorioDosColetivosDaGaragemMatriz();
		
		assertNotNull(relatorios);

	}

	@Test
	void testRelatorioDosColetivosOperando() {
		when(relatorioRepository.findRelatorioColetivoOperando()).thenReturn(list);
		List<RelatorioColetivo> relatorios = relatorioService.relatorioDosColetivosOperando();
		
		assertNotNull(relatorios);

	}

	@Test
	void testRelatorioDosColetivosManutencao() {
		when(relatorioRepository.findRelatorioColetivoManutencao()).thenReturn(list);
		List<RelatorioColetivo> relatorios = relatorioService.relatorioDosColetivosManutencao();
		
		assertNotNull(relatorios);

	}

	@Test
	void testRelatorioDosColetivosParado() {
		when(relatorioRepository.findRelatorioColetivoParado()).thenReturn(list);
		List<RelatorioColetivo> relatorios = relatorioService.relatorioDosColetivosParado();
		
		assertNotNull(relatorios);

	}

}
