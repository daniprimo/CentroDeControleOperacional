package com.example.demo.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entities.Coletivo;
import com.example.demo.exceptions.ColetivoComDocumentoCitadaJaExiste;
import com.example.demo.exceptions.ColetivoComPlacaCitadaJaExiste;
import com.example.demo.exceptions.ColetivoComPrefixoCitadoJaExiste;
import com.example.demo.exceptions.ColetivoNaoFoiSalvoException;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.repository.ColetivoRepository;

@RunWith(SpringRunner.class)
public class ColetivoServiceTest {
	
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
	private ColetivoService coletivoService;
	
	@Mock
	private ColetivoRepository coletivoRepository;
	
	private Coletivo coletivo;
	
	private Optional<Coletivo> optionalColetivo;
	
	@BeforeEach
	void setup () {
		MockitoAnnotations.openMocks(this);
		startColetivo();
	}
	
	@Test
	@DisplayName("Sucesso ao criar o coletivo")
	void deveCriarComSucessoUmOnjeto () {
		when(coletivoRepository.save(any())).thenReturn(coletivo);
		
		Coletivo response = coletivoService.adicionar(coletivo);
		
		assertNotNull(response);
		assertEquals(Coletivo.class, response.getClass());
		assertEquals(_ID, response.getId());
		assertEquals(PLACA, response.getPlaca());
		assertEquals(PREFIXO, response.getPrefixo());
		assertEquals(DOCUMENTO, response.getDoc());
		assertEquals(MODELO, response.getModelo());
		
	}
	
	@Test
	@DisplayName("Falhar ao criar o coletivo")
	void deveFalharAoCriarUsuarioInexistente () {
		when(coletivoRepository.save(any())).thenThrow(new ColetivoNaoFoiSalvoException(COLETIVO_NÃO_FOI_SALVO));
		try {
			coletivoService.adicionar(coletivo);
		}catch (Exception e) {
			assertEquals(ColetivoNaoFoiSalvoException.class, e.getClass());
			assertEquals(COLETIVO_NÃO_FOI_SALVO, e.getMessage());
			
		}
	}
	
	@Test
	@DisplayName("Deve Ao Buscar O Coletivo Pelo Id Retorna Uma Instancia Do Objeto")
	public void deveAoBuscarOColetivoPeloIdRetornaUmaInstanciaDoObjeto() {
		when(coletivoRepository.findById(anyLong())).thenReturn(optionalColetivo);
		Coletivo response = coletivoService.pesquisarColetivoPorId(_ID);
		
		assertNotNull(response);
		assertEquals(Coletivo.class, response.getClass());
		assertEquals(_ID, response.getId());
		assertEquals(PLACA, response.getPlaca());
		assertEquals(PREFIXO, response.getPrefixo());
		assertEquals(DOCUMENTO, response.getDoc());
		assertEquals(MODELO, response.getModelo());
		
	}
	
	@Test
	@DisplayName("Quando não tiver o objeto deve estoura uma Exceptions")
	void deveRetornaUmaExceptionQuandoObjetoForNull () {
		when(coletivoRepository.findById(anyLong())).thenThrow(new EntityNotFoundException(COLETIVO_NÃO_ENCONTRADO));
		try {
			coletivoService.pesquisarColetivoPorId(_ID);
		}catch (Exception e) {
			assertEquals(EntityNotFoundException.class, e.getClass());
			assertEquals(COLETIVO_NÃO_ENCONTRADO, e.getMessage());
			
			
			
		}
	}
	
	@Test
	@DisplayName("Falhar quando tentar cadastrar placa que ja existe")
	void deveRetornaUmaExceptionQuandoJaTiverPlacaRegistrada() {
		when(coletivoRepository.findByPlaca(anyString())).thenReturn(optionalColetivo);
		when(coletivoRepository.findByPlaca(anyString())).thenThrow(new ColetivoComPlacaCitadaJaExiste(PLACA_JA_EXISTENTE));
		try {
			optionalColetivo.get().setPlaca(PLACA);
			coletivoService.adicionar(coletivo);
		}catch (Exception e) {
			assertEquals(ColetivoComPlacaCitadaJaExiste.class, e.getClass());
			assertEquals(PLACA_JA_EXISTENTE, e.getMessage());	
			
		}
	}
	
	@Test
	@DisplayName("Falhar quando tentar cadastrar prefixo que ja existe")
	void deveRetornaUmaExceptionQuandoJaTiverPrefixoRegistrada() {
		when(coletivoRepository.findByPrefixo(anyString())).thenReturn(optionalColetivo);
		when(coletivoRepository.findByPrefixo(anyString())).thenThrow(new ColetivoComPrefixoCitadoJaExiste(PREFIXO_JA_EXISTENTE));
		try {
			optionalColetivo.get().setPrefixo(PREFIXO);
			Coletivo ss = coletivo;
			ss.setPrefixo("21555");
			ss.setPlaca(PLACA);
			coletivoService.adicionar(ss);
		}catch (Exception e) {
			assertEquals(ColetivoComPrefixoCitadoJaExiste.class, e.getClass());
			assertEquals(PREFIXO_JA_EXISTENTE, e.getMessage());	
			
		}
	}
	
	@Test
	@DisplayName("Falhar quando tentar cadastrar DOCUMENTO que ja existe")
	void deveRetornaUmaExceptionQuandoJaTiverDocumentoRegistrada() {
		when(coletivoRepository.findByDoc(anyString())).thenReturn(optionalColetivo);
		when(coletivoRepository.findByDoc(anyString())).thenThrow(new ColetivoComDocumentoCitadaJaExiste("Documento ja existente"));
		try {
			optionalColetivo.get().setDoc(DOCUMENTO);
			coletivoService.adicionar(coletivo);
		}catch (Exception e) {
			assertEquals(ColetivoComDocumentoCitadaJaExiste.class, e.getClass());
			assertEquals("Documento ja existente", e.getMessage());	
			
		}
	}
	
	@Test
	@DisplayName("Deve retornar uma lista de coletios")
	void deveRetornaUmaListaDeTodosOsColetivos () {
		when(coletivoRepository.findAll()).thenReturn(List.of(coletivo));
		
		List<Coletivo> response = coletivoService.listaColetivos();
		
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Coletivo.class, response.get(0).getClass());
		assertEquals(_ID, response.get(0).getId());
		assertEquals(PLACA, response.get(0).getPlaca());
		assertEquals(PREFIXO, response.get(0).getPrefixo());
		assertEquals(DOCUMENTO, response.get(0).getDoc());
		assertEquals(MODELO, response.get(0).getModelo());
		
	}
	
	@Test
	@DisplayName("Sucesso ao atualizar coletivo pelo ID registrado")
	void deveAtualziarColetivoComSucessoPorId() {
		when(coletivoRepository.findById(anyLong())).thenReturn(optionalColetivo);
		when(coletivoRepository.save(any())).thenReturn(coletivo);
		
		Coletivo response = coletivoService.alterarColetivoPorId(coletivo,_ID);
		
		assertNotNull(response);
		assertEquals(Coletivo.class, response.getClass());
		assertEquals(_ID, response.getId());
		assertEquals(PLACA, response.getPlaca());
		assertEquals(PREFIXO, response.getPrefixo());
		assertEquals(DOCUMENTO, response.getDoc());
		assertEquals(MODELO, response.getModelo());
		
	}
	
	@Test
	@DisplayName("Sucesso ao atualizar coletivo pelo placa registrado")
	void deveAtualziarColetivoComSucessoPorPlaca() {
		when(coletivoRepository.findByPlaca(anyString())).thenReturn(optionalColetivo);
		when(coletivoRepository.save(any())).thenReturn(coletivo);
		
		Coletivo response = coletivoService.alterarColetivoPorPlaca(coletivo,PLACA);
		
		assertNotNull(response);
		assertEquals(Coletivo.class, response.getClass());
		assertEquals(_ID, response.getId());
		assertEquals(PLACA, response.getPlaca());
		assertEquals(PREFIXO, response.getPrefixo());
		assertEquals(DOCUMENTO, response.getDoc());
		assertEquals(MODELO, response.getModelo());
		
	}
	
	@Test
	@DisplayName("Sucesso ao atualizar coletivo pelo placa registrado")
	void deveAtualziarColetivoComSucessoPorPrefixo() {
		when(coletivoRepository.findByPrefixo(anyString())).thenReturn(optionalColetivo);
		when(coletivoRepository.save(any())).thenReturn(coletivo);
		
		Coletivo response = coletivoService.alterarColetivoPorPrefixo(coletivo,PREFIXO);
		
		assertNotNull(response);
		assertEquals(Coletivo.class, response.getClass());
		assertEquals(_ID, response.getId());
		assertEquals(PLACA, response.getPlaca());
		assertEquals(PREFIXO, response.getPrefixo());
		assertEquals(DOCUMENTO, response.getDoc());
		assertEquals(MODELO, response.getModelo());
		
	}
	
	@Test
	@DisplayName("Deve retorna OK ao deletar coletivo")
	void deveRetornaOkAoDeletar(){
		when(coletivoRepository.findById(7l)).thenReturn(optionalColetivo);
		doNothing().when(coletivoRepository).deleteById(anyLong());
		coletivoService.deletarColetivoPorId(_ID);
		verify(coletivoRepository, times(1)).delete(any());
	}
	
	@Test
	@DisplayName("Deve retorna uma exception ao deletar coletivo que não esta no banco")
	void deveRetornaUmaExceptionAoDeletar(){
		when(coletivoRepository.findById(7l)).thenThrow(new EntityNotFoundException(COLETIVO_NÃO_ENCONTRADO2));
		doNothing().when(coletivoRepository).deleteById(anyLong());
		try {
			coletivoService.deletarColetivoPorId(_ID);
		} catch (Exception e) {
			assertEquals(EntityNotFoundException.class, e.getClass());
			assertEquals(COLETIVO_NÃO_ENCONTRADO, e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Deve retorna OK ao deletar coletivo")
	void deveRetornaOkAoDeletarPrefixo(){
		when(coletivoRepository.findByPrefixo(PREFIXO)).thenReturn(optionalColetivo);
		doNothing().when(coletivoRepository).deleteById(anyLong());
		coletivoService.deletarColetivoPorPrefixo(PREFIXO);
		verify(coletivoRepository, times(1)).delete(any());
	}
	
	@Test
	@DisplayName("Deve retorna uma exception ao deletar coletivo que não esta no banco")
	void deveRetornaUmaExceptionAoDeletarPorPrefixo(){
		when(coletivoRepository.findByPrefixo("21520")).thenThrow(new EntityNotFoundException(COLETIVO_NÃO_ENCONTRADO2));
		doNothing().when(coletivoRepository).deleteById(anyLong());
		try {
			coletivoService.deletarColetivoPorPrefixo(PREFIXO);
		} catch (Exception e) {
			assertEquals(EntityNotFoundException.class, e.getClass());
			assertEquals(COLETIVO_NÃO_ENCONTRADO, e.getMessage());
		}
	}

	
	private void startColetivo () {
		coletivo = new Coletivo(_ID, PLACA, PREFIXO, MODELO, DOCUMENTO);
		optionalColetivo = Optional.of(new Coletivo(_ID, PLACA, PREFIXO, MODELO, DOCUMENTO));
	}

}
