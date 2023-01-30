package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Coletivo;
import com.example.demo.exceptions.ColetivoComDocumentoCitadaJaExiste;
import com.example.demo.exceptions.ColetivoComPlacaCitadaJaExiste;
import com.example.demo.exceptions.ColetivoComPrefixoCitadoJaExiste;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.exceptions.excessoes.DocumentoExistenteException;
import com.example.demo.exceptions.excessoes.PlacaExistenteException;
import com.example.demo.exceptions.excessoes.PrefixoExistenteException;
import com.example.demo.repository.ColetivoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ColetivoService {

	@Autowired
	private ColetivoRepository coletivoRepository;


	public ColetivoService(ColetivoRepository coletivoRepository, ColetivoRepository coletivoRepository2) {
		coletivoRepository = coletivoRepository2;
	}

	public List<Coletivo> listaColetivos() {			
			return coletivoRepository.findAll();
	}

	public Coletivo adicionar(Coletivo coletivo) {
			try {
				coletivo.validacao();
				coletivoRepository.save(coletivo);
				log.error("Coletivo de prefixo "+ coletivo.getPrefixo() + " adcionado !!"+ coletivo);
			} catch (PrefixoExistenteException e) {
				log.error("Prefixo "+ coletivo.getPrefixo() + " ja existente !!");
				throw new ColetivoComPrefixoCitadoJaExiste("Prefixo " + coletivo.getPrefixo() + " já existente!");
			} catch (PlacaExistenteException e) {
				log.error("Placa "+ coletivo.getPlaca() + " ja existente !!");
				throw new ColetivoComPlacaCitadaJaExiste("Placa " + coletivo.getPlaca() + " já existente!");
			} catch (DocumentoExistenteException e) {
				log.error("Documento "+ coletivo.getDoc() + " ja existente !!");
				throw new ColetivoComDocumentoCitadaJaExiste("Documento " + coletivo.getDoc() + " já existente!");
			}
			return coletivo;

	}

	public Coletivo pesquisarColetivoPorId(Long id) {
		Optional<Coletivo> coletivo = coletivoRepository.findById(id);
		return coletivo.orElseThrow(() -> new EntityNotFoundException("Coletivo não encontrado"));
	}

	public Coletivo pesquisarColetivoPorPrefixo(String prefixo) {
		return coletivoRepository.findByPrefixo(prefixo)
				.orElseThrow(() -> new EntityNotFoundException("Coletivo não encontrado"));
	}

	public Coletivo pesquisarColetivoPorPlaca(String placa) {
		return coletivoRepository.findByPlaca(placa)
				.orElseThrow(() -> new EntityNotFoundException("Coletivo não encontrado"));
	}

	public Coletivo alterarColetivoPorId(Coletivo coletivo, Long id) {
		Coletivo coletivoAtual = pesquisarColetivoPorId(id);
		coletivo.setId(coletivoAtual.getId());

		return coletivoRepository.save(coletivo);
	}

	public Coletivo alterarColetivoPorPrefixo(Coletivo coletivoAtualizado, String prefixo) {
		Coletivo coletivoAtual = pesquisarColetivoPorPrefixo(prefixo);
		coletivoAtualizado.setId(coletivoAtual.getId());
		return coletivoRepository.save(coletivoAtualizado);
	}

	public Coletivo alterarColetivoPorPlaca(Coletivo coletivoAtualizado, String placa) {
		Coletivo coletivoAtual = pesquisarColetivoPorPlaca(placa);
		coletivoAtualizado.setId(coletivoAtual.getId());
		return coletivoRepository.save(coletivoAtualizado);
	}

	public void deletarColetivoPorId(Long id) {
		Coletivo coletivoAtual = pesquisarColetivoPorId(id);
		coletivoRepository.delete(coletivoAtual);
	}

	public void deletarColetivoPorPrefixo(String prefixo) {
		Coletivo coletivoAtual = pesquisarColetivoPorPrefixo(prefixo);
		coletivoRepository.delete(coletivoAtual);

	}

}
