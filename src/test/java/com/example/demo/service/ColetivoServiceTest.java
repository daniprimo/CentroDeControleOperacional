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

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.Coletivo;
import com.example.demo.exceptions.ColetivoComPlacaCitadaJaExiste;
import com.example.demo.exceptions.ColetivoComPrefixoCitadoJaExiste;
import com.example.demo.repository.ColetivoRepository;

@SpringBootTest
class ColetivoServiceTest {

	private static final String _DOCUMENTO = "001";

	private static final String _MODELO = "Mercedez";

	private static final String __PREFIXO = "21.500";

	private static final String _PLACA = "BKW-2462";

	private static final long __ID = 1l;

	@InjectMocks
	private ColetivoService coletivoService;

	@Mock
	private ColetivoRepository coletivoRepository;

	private Coletivo coletivo;

	private Optional<Coletivo> optional;

	private List<Coletivo> list = new ArrayList<Coletivo>();

	@BeforeEach
	void setUp() throws Exception {
			MockitoAnnotations.openMocks(this);
			startColetivo();
	}

	@Test
	@DisplayName("Listar todos os coletivos")
	void testListaColetivos() {
		when(coletivoRepository.findAll()).thenReturn(list);
		List<Coletivo> response = coletivoService.listaColetivos();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Coletivo.class, list.get(0).getClass());
		assertEquals(__ID, list.get(0).getId());
		assertEquals(__PREFIXO, list.get(0).getPrefixo());
		assertEquals(_PLACA, list.get(0).getPlaca());
		assertEquals(_DOCUMENTO, list.get(0).getDoc());

	}

	@Test
	@DisplayName("Deve adicionar um coletivo ao banco de dados")
	void testAdicionar() {
		Coletivo novo = new Coletivo(__ID, "BDD-0015", "21.805", _MODELO, "005");
		when(coletivoRepository.save(any())).thenReturn(novo);

		Coletivo response = coletivoService.adicionar(novo);

		assertNotNull(response);
		assertEquals(Coletivo.class, response.getClass());
		assertEquals(__ID, response.getId());
		assertEquals("BDD-0015", response.getPlaca());
		assertEquals("21.805", response.getPrefixo());
		assertEquals(_MODELO, response.getModelo());
		assertEquals("005", response.getDoc());
	}

	@Test
	@DisplayName("Deve retorna um erro do  prefixo existente ")
	void testEstouraErroAoAdicionarPrefixoExistente() {
		when(coletivoRepository.save(any())).thenReturn(coletivo);
		try {
			coletivoService.adicionar(coletivo);
		} catch (Exception e) {
			assertEquals(ColetivoComPrefixoCitadoJaExiste.class, e.getClass());
			assertEquals("Prefixo 21.500 já existente!", e.getMessage());
		}
	}

	@Test
	@DisplayName("Deve retorna expetion com placa existente")
	void testEstouraErroAoAdicionarPlacaExistente() {
		Coletivo novo = new Coletivo(__ID, _PLACA, "21.809", _MODELO, "005");
		when(coletivoRepository.save(any())).thenReturn(novo);
		try {
			coletivoService.adicionar(novo);
		} catch (Exception e) {
			assertEquals(ColetivoComPlacaCitadaJaExiste.class, e.getClass());
			assertEquals("Placa " + _PLACA + " já existente!", e.getMessage());
		}
	}

	@Test
	@DisplayName("Deve retorna exception por estar com documento igual")
	void testEstouraErroAoAdicionarDocExistente() {
		Coletivo novo = new Coletivo(__ID, "DAS-0547", "21.874", _MODELO, _DOCUMENTO);
		when(coletivoRepository.save(any())).thenReturn(novo);
		
		try {
			coletivoService.adicionar(novo);
		} catch (Exception e) {
			assertEquals(ColetivoComPlacaCitadaJaExiste.class, e.getClass());
			assertEquals("Documento " + _DOCUMENTO + " já existente!", e.getMessage());
		}
	}

	@Test
	void testPesquisarColetivoPorId() {

		Mockito.when(coletivoRepository.findById(anyLong())).thenReturn(optional);

		Coletivo response = coletivoService.pesquisarColetivoPorId(__ID);

		assertNotNull(response);
		assertEquals(Coletivo.class, response.getClass());
		assertEquals(__ID, response.getId());
		assertEquals(__PREFIXO, response.getPrefixo());
		assertEquals(_PLACA, response.getPlaca());
		assertEquals(_DOCUMENTO, response.getDoc());
		assertEquals(_MODELO, response.getModelo());

	}

	@Test
	void testPesquisarColetivoPorPrefixo() {
		Mockito.when(coletivoRepository.findByPrefixo(anyString())).thenReturn(optional);

		Coletivo response = coletivoService.pesquisarColetivoPorPrefixo(__PREFIXO);

		assertNotNull(response);
		assertEquals(Coletivo.class, response.getClass());
		assertEquals(__ID, response.getId());
		assertEquals(__PREFIXO, response.getPrefixo());
		assertEquals(_PLACA, response.getPlaca());
		assertEquals(_DOCUMENTO, response.getDoc());
		assertEquals(_MODELO, response.getModelo());

	}

	@Test
	void testPesquisarColetivoPorPlaca() {
		Mockito.when(coletivoRepository.findByPlaca(anyString())).thenReturn(optional);

		Coletivo response = coletivoService.pesquisarColetivoPorPlaca(_PLACA);

		assertNotNull(response);
		assertEquals(Coletivo.class, response.getClass());
		assertEquals(__ID, response.getId());
		assertEquals(__PREFIXO, response.getPrefixo());
		assertEquals(_PLACA, response.getPlaca());
		assertEquals(_DOCUMENTO, response.getDoc());
		assertEquals(_MODELO, response.getModelo());

	}

	@Test
	void daveEstouraExpetionsBuscarColetivoERetornaloPeloId() {
		when(coletivoRepository.findById(anyLong())).thenThrow(new EntityNotFoundException("Coletivo não encontrado"));
		try {
			coletivoService.pesquisarColetivoPorId(6l);
		} catch (Exception e) {
			assertEquals(EntityNotFoundException.class, e.getClass());
			assertEquals("Coletivo não encontrado", e.getMessage());
		}
	}

	@Test
	void testAlterarColetivoPorId() {
		when(coletivoRepository.findById(anyLong())).thenReturn(optional);
		when(coletivoRepository.save(any())).thenReturn(coletivo);
		
		Coletivo response = coletivoService.alterarColetivoPorId(coletivo, coletivo.getId());

		assertNotNull(response);
		assertEquals(Coletivo.class, response.getClass());
		assertEquals(__ID, response.getId());
		assertEquals(_PLACA, response.getPlaca());
		assertEquals(__PREFIXO, response.getPrefixo());
		assertEquals(_MODELO, response.getModelo());
		assertEquals(_DOCUMENTO, response.getDoc());

	}
	
	@Test
	void testAlterarColetivoPorPrefixo() {
		when(coletivoRepository.findByPrefixo(anyString())).thenReturn(optional);
		when(coletivoRepository.save(any())).thenReturn(coletivo);
		
		Coletivo response = coletivoService.alterarColetivoPorPrefixo(coletivo, coletivo.getPrefixo());
		
		assertNotNull(response);
		assertEquals(Coletivo.class, response.getClass());
		assertEquals(__ID, response.getId());
		assertEquals(_PLACA, response.getPlaca());
		assertEquals(__PREFIXO, response.getPrefixo());
		assertEquals(_MODELO, response.getModelo());
		assertEquals(_DOCUMENTO, response.getDoc());
		
	}

	@Test
	void testAlterarColetivoPorPlaca() {
		when(coletivoRepository.findByPlaca(anyString())).thenReturn(optional);
		when(coletivoRepository.save(any())).thenReturn(coletivo);
		
		Coletivo response = coletivoService.alterarColetivoPorPlaca(coletivo, coletivo.getPlaca());
		
		assertNotNull(response);
		assertEquals(Coletivo.class, response.getClass());
		assertEquals(__ID, response.getId());
		assertEquals(_PLACA, response.getPlaca());
		assertEquals(__PREFIXO, response.getPrefixo());
		assertEquals(_MODELO, response.getModelo());
		assertEquals(_DOCUMENTO, response.getDoc());
		

	}

	@Test
	void testDeletarColetivoPorId() {
		when(coletivoRepository.findById(anyLong())).thenReturn(optional);
		doNothing().when(coletivoRepository).deleteById(anyLong());
		coletivoService.deletarColetivoPorId(__ID);
		verify(coletivoRepository, times(1)).delete(any());

	}

	@Test
	void testDeletarColetivoPorPrefixo() {
		when(coletivoRepository.findByPrefixo(anyString())).thenReturn(optional);
		doNothing().when(coletivoRepository).deleteById(anyLong());
		coletivoService.deletarColetivoPorPrefixo(__PREFIXO);
		verify(coletivoRepository, times(1)).delete(any());

	}

	private void startColetivo() {
		coletivo = new Coletivo(__ID, _PLACA, __PREFIXO, _MODELO, _DOCUMENTO);
		optional = Optional.of(new Coletivo(__ID, _PLACA, __PREFIXO, _MODELO, _DOCUMENTO));
		list.add(coletivo);
	}

}
