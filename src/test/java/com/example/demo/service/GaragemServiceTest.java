package com.example.demo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

import com.example.demo.entities.Coletivo;
import com.example.demo.entities.Endereco;
import com.example.demo.entities.Funcionario;
import com.example.demo.entities.Garagem;
import com.example.demo.repository.GaragemRepository;

@SpringBootTest
class GaragemServiceTest {
	
	private static final String CEP_RUA = "06385820";

	private static final String NOME_RUA = "Rua Ipixuna";

	private static final String NOME_GARAGEM = "Garagem Matriz";

	@Mock
	private GaragemRepository garagemRepository;
	
	@InjectMocks
	private GaragemService garagemService;

	private Optional<Garagem> optional;
	
	private Garagem garagem;
	
	private List<Garagem> list = new ArrayList<Garagem>();
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startCompleto();
		startGaragem();
	}

	@Test
	void testListaDasGaragens() {
		when(garagemRepository.findAll()).thenReturn(list);
		List<Garagem> response = garagemService.listaDasGaragens();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Garagem.class, list.get(0).getClass());
		assertEquals(1l, response.get(0).getId());
		assertEquals(NOME_GARAGEM, response.get(0).getNome());
		assertEquals(NOME_RUA, response.get(0).getEndereco().getLogradouro());
		assertEquals("141", response.get(0).getEndereco().getNumero());
		assertEquals(CEP_RUA, response.get(0).getEndereco().getCep());
		assertEquals("Carapicuiba", response.get(0).getEndereco().getCidade());
		assertEquals("Bairro", response.get(0).getEndereco().getBairro());
		assertEquals(1l, response.get(0).getColetivos().get(0).getId());
		assertEquals("PREFIXO", response.get(0).getColetivos().get(0).getPrefixo());
		assertEquals("PLACA", response.get(0).getColetivos().get(0).getPlaca());
		assertEquals("DOC", response.get(0).getColetivos().get(0).getDoc());
		assertEquals("COR", response.get(0).getColetivos().get(0).getCor());
		assertEquals("STATUS", response.get(0).getColetivos().get(0).getStatus());
		assertEquals("MODELO", response.get(0).getColetivos().get(0).getModelo());
		

	}

	@Test
	void testBuscarGaragemPorId() {
		when(garagemRepository.findById(anyLong())).thenReturn(optional);
		Garagem response = garagemService.buscarGaragemPorId(1l);

		assertNotNull(response);
		assertEquals(Garagem.class, response.getClass());
		assertEquals(1l, response.getId());
		assertEquals(NOME_GARAGEM, response.getNome());
		assertEquals(NOME_RUA, response.getEndereco().getLogradouro());
		assertEquals("141", response.getEndereco().getNumero());
		assertEquals(CEP_RUA, response.getEndereco().getCep());
		assertEquals("Carapicuiba", response.getEndereco().getCidade());
		assertEquals("Bairro", response.getEndereco().getBairro());
		assertEquals(1l, response.getColetivos().get(0).getId());
		assertEquals("PREFIXO", response.getColetivos().get(0).getPrefixo());
		assertEquals("PLACA", response.getColetivos().get(0).getPlaca());
		assertEquals("DOC", response.getColetivos().get(0).getDoc());
		assertEquals("COR", response.getColetivos().get(0).getCor());
		assertEquals("STATUS", response.getColetivos().get(0).getStatus());
		assertEquals("MODELO", response.getColetivos().get(0).getModelo());

	}

	@Test
	void testAdicionarGaragens() {
		when(garagemRepository.save(any())).thenReturn(garagem);
		Garagem response = garagemService.adicionarGaragens(garagem);

		assertNotNull(response);
		assertEquals(Garagem.class, response.getClass());
		assertEquals(1l, response.getId());
		assertEquals(NOME_GARAGEM, response.getNome());
		assertEquals(NOME_RUA, response.getEndereco().getLogradouro());
		assertEquals("141", response.getEndereco().getNumero());
		assertEquals(CEP_RUA, response.getEndereco().getCep());
		assertEquals("Carapicuiba", response.getEndereco().getCidade());
		assertEquals("Bairro", response.getEndereco().getBairro());
		assertEquals(1l, response.getColetivos().get(0).getId());
		assertEquals("PREFIXO", response.getColetivos().get(0).getPrefixo());
		assertEquals("PLACA", response.getColetivos().get(0).getPlaca());
		assertEquals("DOC", response.getColetivos().get(0).getDoc());
		assertEquals("COR", response.getColetivos().get(0).getCor());
		assertEquals("STATUS", response.getColetivos().get(0).getStatus());
		assertEquals("MODELO", response.getColetivos().get(0).getModelo());

	}

	@Test
	void testAtualizarGaragem() {
		when(garagemRepository.findById(anyLong())).thenReturn(optional);
		garagem.setId(3l);
		when(garagemRepository.save(any())).thenReturn(garagem);
		Garagem response = garagemService.atualizarGaragem(garagem,1l);

		assertNotNull(response);
		assertEquals(Garagem.class, response.getClass());
		assertEquals(3l, response.getId());
		assertEquals(NOME_GARAGEM, response.getNome());
		assertEquals(NOME_RUA, response.getEndereco().getLogradouro());
		assertEquals("141", response.getEndereco().getNumero());
		assertEquals(CEP_RUA, response.getEndereco().getCep());
		assertEquals("Carapicuiba", response.getEndereco().getCidade());
		assertEquals("Bairro", response.getEndereco().getBairro());
		assertEquals(1l, response.getColetivos().get(0).getId());
		assertEquals("PREFIXO", response.getColetivos().get(0).getPrefixo());
		assertEquals("PLACA", response.getColetivos().get(0).getPlaca());
		assertEquals("DOC", response.getColetivos().get(0).getDoc());
		assertEquals("COR", response.getColetivos().get(0).getCor());
		assertEquals("STATUS", response.getColetivos().get(0).getStatus());
		assertEquals("MODELO", response.getColetivos().get(0).getModelo());
	
	}

	@Test
	void testDeletarPorId() {
		when(garagemRepository.findById(anyLong())).thenReturn(optional);
		doNothing().when(garagemRepository).deleteById(anyLong());
		garagemService.deletarPorId(1l);
		verify(garagemRepository, times(1)).delete(any());
	}
	
	
	private void startGaragem() {
		optional = optional.of(garagem);
		list.add(garagem);
	}
	
	private void startCompleto () {
		Endereco endereco = new Endereco(NOME_RUA, "141", CEP_RUA, "Bairro","Carapicuiba"); 
		Coletivo coletivo = new Coletivo(1l, "PLACA", "PREFIXO", "MODELO", "COR", "DOC", "STATUS", garagem);
		Funcionario funcionario = new Funcionario(1l, "NOME", "CPF", "TELEFONE", garagem);
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(funcionario);
		List<Coletivo> lista = new ArrayList<Coletivo>();
		lista.add(coletivo);
		garagem = new Garagem(1l, NOME_GARAGEM, endereco, lista, funcionarios);
	}

}
