package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Coletivo;
import com.example.demo.exceptions.ColetivoComDocumentoCitadaJaExiste;
import com.example.demo.exceptions.ColetivoComPlacaCitadaJaExiste;
import com.example.demo.exceptions.ColetivoComPrefixoCitadoJaExiste;
import com.example.demo.exceptions.ColetivoNaoFoiSalvoException;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.exceptions.excessoes.DocumentoExistenteException;
import com.example.demo.exceptions.excessoes.PlacaExistenteException;
import com.example.demo.exceptions.excessoes.PrefixoExistenteException;
import com.example.demo.repository.ColetivoRepository;
import com.example.demo.service.validacoes.ValidacoesColetivo;

@Service
public class ColetivoService extends ValidacoesColetivo {
	
	@Autowired
	private ColetivoRepository coletivoRepository;

	
	
	public ColetivoService(ColetivoRepository coletivoRepository, ColetivoRepository coletivoRepository2) {
		super(coletivoRepository);
		coletivoRepository = coletivoRepository2;
	}


	public List<Coletivo> listaColetivos () {
			return coletivoRepository.findAll();
	}
	
	
	public Coletivo adicionar (Coletivo coletivo) {
		try {
			ValidacaoSeExisteInformaçõesRepetidas(coletivo);
			return coletivoRepository.save(coletivo);			
		}catch (PrefixoExistenteException e) {
			throw new ColetivoComPrefixoCitadoJaExiste("Prefixo já existente");
		}catch (PlacaExistenteException e) {
			throw new ColetivoComPlacaCitadaJaExiste("Placa já existente");
		}catch (DocumentoExistenteException e) {
			throw new ColetivoComDocumentoCitadaJaExiste("Documento já existente");
		}
		catch (Exception e) {		
			throw new ColetivoNaoFoiSalvoException("Coletivo não foi salvo");
		}	
	}	

	
	public Coletivo pesquisarColetivoPorId (Long id) {
		Optional<Coletivo> optional = coletivoRepository.findById(id);
		return optional.orElseThrow(()  -> new EntityNotFoundException("Coletivo não encontrado"));
	}
	
	public Coletivo pesquisarColetivoPorPrefixo (String prefixo) {
		Optional<Coletivo> optional = coletivoRepository.findByPrefixo(prefixo);
		return optional.orElseThrow(()  -> new EntityNotFoundException("Coletivo não encontrado"));
	} 

	public Coletivo pesquisarColetivoPorPlaca (String placa) {
		Optional<Coletivo> optional = coletivoRepository.findByPlaca(placa);
		return optional.orElseThrow(()  -> new EntityNotFoundException("Coletivo não encontrado"));
	}
	
	public Coletivo alterarColetivoPorId (Coletivo coletivo, Long id) {
		Coletivo coletivoOriginal = pesquisarColetivoPorId(id);
		coletivo.setId(coletivoOriginal.getId());
		return coletivoRepository.save(coletivo);
	}
	
	public Coletivo alterarColetivoPorPrefixo (Coletivo coletivo, String prefixo) {
		Coletivo coletivoOriginal = pesquisarColetivoPorPrefixo(prefixo);
		coletivo.setId(coletivoOriginal.getId());
		return coletivoRepository.save(coletivo);
	}
	
	public Coletivo alterarColetivoPorPlaca (Coletivo coletivo, String placa) {
		Coletivo coletivoOriginal = pesquisarColetivoPorPlaca(placa);
		coletivo.setId(coletivoOriginal.getId());
		return coletivoRepository.save(coletivo);
	}
	
	public void deletarColetivoPorId (Long id) {
		Coletivo coletivoAtual = pesquisarColetivoPorId(id);
		coletivoRepository.delete(coletivoAtual);
	}
	
	public void deletarColetivoPorPrefixo (String  prefixo) {
		Coletivo coletivoAtual = pesquisarColetivoPorPrefixo(prefixo);
		coletivoRepository.delete(coletivoAtual);
	}
	
}
