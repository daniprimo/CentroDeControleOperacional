package com.example.demo.service.validacoes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.example.demo.entities.Coletivo;
import com.example.demo.exceptions.excessoes.DocumentoExistenteException;
import com.example.demo.exceptions.excessoes.PlacaExistenteException;
import com.example.demo.exceptions.excessoes.PrefixoExistenteException;
import com.example.demo.repository.ColetivoRepository;

public class ValidacoesColetivo {
	
	@Lazy
	@Autowired
	private ColetivoRepository coletivoRepository;	
	
	public ValidacoesColetivo(ColetivoRepository coletivoRepository) {
		this.coletivoRepository = coletivoRepository;
	}

	private void validandoSeOPrefixoExiste(Coletivo coletivo) throws PrefixoExistenteException {
			Optional<Coletivo> encontrado  = coletivoRepository.findByPrefixo(coletivo.getPrefixo());
			
			if (!encontrado.isEmpty()) 
				 throw new PrefixoExistenteException();
	}
	
	private void validandoSeOPlacaExiste(Coletivo coletivo) throws PlacaExistenteException {
		Boolean encontrado  = coletivoRepository.findByPlaca(coletivo.getPlaca()).isEmpty();

		if (!encontrado) 
			throw new PlacaExistenteException();

	}
	
	private void validandoSeODocExiste(Coletivo coletivo) throws DocumentoExistenteException {
		Boolean encontrado  = coletivoRepository.findByDoc(coletivo.getDoc()).isEmpty();
		
		if (!encontrado) 
			throw new DocumentoExistenteException();

	}
	
	public void ValidacaoSeExisteInformaçõesRepetidas(Coletivo coletivo) throws Exception {
		if (coletivo.getPlaca() == "" | coletivo.getPrefixo() == "" | coletivo.getModelo() == "")
			throw new Exception();
		
		validandoSeOPrefixoExiste(coletivo);
		validandoSeOPlacaExiste(coletivo);
		validandoSeODocExiste(coletivo);
		
	}

}
