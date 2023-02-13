package com.example.demo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.InfoGaragemDTO;
import com.example.demo.dto.relatorio.ColetivoDTO;
import com.example.demo.entities.Coletivo;
import com.example.demo.repositoryJDBC.RepositoryJDBC;

@SpringBootTest
class ColetivoServiceDAOTest {
	private static final String _DOCUMENTO = "001";

	private static final String _MODELO = "Mercedez";

	private static final String __PREFIXO = "21.500";

	private static final String _PLACA = "BKW-2462";

	private static final long __ID = 1l;

	@InjectMocks
	private ColetivoServiceDAO coletivoServiceDAO;

	@Mock
	private RepositoryJDBC repository;

	private Coletivo coletivo;

	private Optional<Coletivo> optional;

	private List<Coletivo> list = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startColetivo();
	}

	@Test
	void TEST_LISTAR_COLETIVOS () {
		when(repository.listarColetivos()).thenReturn(list);
		List<Coletivo> coletivos = coletivoServiceDAO.listarColetivos();
		
		assertNotNull(coletivos);
		assertEquals(1, coletivos.size());
		assertEquals(Coletivo.class, coletivos.get(0).getClass());
		assertEquals(__ID, coletivos.get(0).getId());
		assertEquals(__PREFIXO, coletivos.get(0).getPrefixo());
		assertEquals(_PLACA, coletivos.get(0).getPlaca());
		assertEquals(_DOCUMENTO, coletivos.get(0).getDoc());
	}

	@Test
	void TEST_ADICIONAR_COLETIVO() {
		ColetivoDTO dto = new ColetivoDTO(_PLACA, __PREFIXO, _MODELO, _MODELO, _DOCUMENTO, _DOCUMENTO);
		when(repository.adicionarColetivo(dto)).thenReturn(__ID);
		Long adicionarColetivo = coletivoServiceDAO.adicionarColetivo(dto);
		
		assertNotNull(adicionarColetivo);
		assertEquals(__ID, adicionarColetivo);
	}
	
	@Test
	void TEST_BUSCAR_COLEIIVO_POR_ID () {
		when(repository.buscarColetivoPeloId(anyLong())).thenReturn(coletivo);
		Coletivo coletivos = coletivoServiceDAO.buscarColetivoPeloId(__ID);
		
		assertNotNull(coletivos);
		assertNotNull(coletivos);
		assertEquals(Coletivo.class, coletivos.getClass());
		assertEquals(__ID, coletivos.getId());
		assertEquals(__PREFIXO, coletivos.getPrefixo());
		assertEquals(_PLACA, coletivos.getPlaca());
		assertEquals(_DOCUMENTO, coletivos.getDoc());
	}

	@Test
	void TEST_BUSCAR_COLEIIVO_POR_PREFIXO () {
		when(repository.buscarColetivoPeloPrefixo(anyString())).thenReturn(coletivo);
		Coletivo coletivos = coletivoServiceDAO.buscarColetivoPeloPrefixo(__PREFIXO);
		
		assertNotNull(coletivos);
		assertNotNull(coletivos);
		assertEquals(Coletivo.class, coletivos.getClass());
		assertEquals(__ID, coletivos.getId());
		assertEquals(__PREFIXO, coletivos.getPrefixo());
		assertEquals(_PLACA, coletivos.getPlaca());
		assertEquals(_DOCUMENTO, coletivos.getDoc());
	}
	
	@Test
	void TEST_BUSCAR_COLEIIVO_POR_PLACA () {
		when(repository.buscarColetivoPelaPlaca(anyString())).thenReturn(coletivo);
		Coletivo coletivos = coletivoServiceDAO.buscarColetivoPelaPlaca(_PLACA);
		
		assertNotNull(coletivos);
		assertNotNull(coletivos);
		assertEquals(Coletivo.class, coletivos.getClass());
		assertEquals(__ID, coletivos.getId());
		assertEquals(__PREFIXO, coletivos.getPrefixo());
		assertEquals(_PLACA, coletivos.getPlaca());
		assertEquals(_DOCUMENTO, coletivos.getDoc());
	}
	
	@Test
	void TEST_EXCLUIR_COLETIVO_POR_PREFIXO () {
		doNothing().when(repository).excluirColetivoPorPrefixo(anyString());
		coletivoServiceDAO.excluirColetivoPorPrefixo(__PREFIXO);
		verify(repository, times(1)).excluirColetivoPorPrefixo(anyString());
	}
	
	@Test
	void TEST_EDITAR_COLETIVO_INDENTIFICADOR_ID () {
		ColetivoDTO dto = new ColetivoDTO(_PLACA, __PREFIXO, _MODELO, _MODELO, _DOCUMENTO, _DOCUMENTO);
		doNothing().when(repository).editarColetivo(anyLong(), any());
		coletivoServiceDAO.editarColetivo(__ID,dto);
		verify(repository, times(1)).editarColetivo(__ID, dto);	
	
	}
	
	@Test
	void TEST_QUANTIDADE_DE_COLETIVO_POR_GARAGEM () {
		InfoGaragemDTO info = new InfoGaragemDTO(1, 5);
		when(repository.quantosColetivosNaGaragem()).thenReturn(info);
		InfoGaragemDTO naGaragem = coletivoServiceDAO.quantosColetivosNaGaragem();
		
		assertNotNull(naGaragem);
		assertEquals(5, naGaragem.getGaragemFilial());
		assertEquals(1, naGaragem.getGaragemMatriz());
	}

	private void startColetivo() {
		coletivo = new Coletivo(__ID, _PLACA, __PREFIXO, _MODELO, _DOCUMENTO);
		optional = Optional.of(new Coletivo(__ID, _PLACA, __PREFIXO, _MODELO, _DOCUMENTO));
		list.add(coletivo);
	}

}
