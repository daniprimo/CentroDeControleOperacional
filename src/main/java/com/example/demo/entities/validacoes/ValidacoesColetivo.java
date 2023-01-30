package com.example.demo.entities.validacoes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Coletivo;
import com.example.demo.exceptions.excessoes.DocumentoExistenteException;
import com.example.demo.exceptions.excessoes.PlacaExistenteException;
import com.example.demo.exceptions.excessoes.PrefixoExistenteException;
import com.example.demo.repository.ColetivoRepository;

@Component
public class ValidacoesColetivo {

	@Lazy
	@Autowired
	private static ColetivoRepository coletivoRepository;

	public ValidacoesColetivo(ColetivoRepository coletivoRepository) {
		ValidacoesColetivo.coletivoRepository = coletivoRepository;
	}

	public static void validandoSeOPrefixoExiste(String prefixo) throws PrefixoExistenteException {
		Optional<Coletivo> encontrado = coletivoRepository.findByPrefixo(prefixo);
		if (!encontrado.isEmpty())
			throw new PrefixoExistenteException();
	}

	public static void validandoSeOPlacaExiste(String placa) throws PlacaExistenteException {
		Boolean encontrado = coletivoRepository.findByPlaca(placa).isEmpty();
		if (!encontrado)
			throw new PlacaExistenteException();

	}

	public static void validandoSeODocExiste(String doc) throws DocumentoExistenteException {
		Boolean encontrado = coletivoRepository.findByDoc(doc).isEmpty();
		System.out.println(encontrado);
		if (!encontrado)
			throw new DocumentoExistenteException();

	}

}
