package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.coletivoDtos.RelatorioColetivo;
import com.example.demo.repository.RelatorioRepository;

@Service
public class RelatorioService {

	private RelatorioRepository relatorioRepository;
	
	@Autowired
	public RelatorioService(RelatorioRepository relatorioRepository) {
		this.relatorioRepository = relatorioRepository;
	}
	
	public List<RelatorioColetivo> listarRelatorio () {
		return relatorioRepository.findRelatorioColetivoRefGaragem();
	}
	
	public List<RelatorioColetivo> relatorioDosColetivosDaGaragemFilial () {
		return relatorioRepository.findRelatorioColetivoGaragemFilial();
	}
	
	public List<RelatorioColetivo> relatorioDosColetivosDaGaragemMatriz () {
		return relatorioRepository.findRelatorioColetivoGaragemMatriz();
	}
	
	public List<RelatorioColetivo> relatorioDosColetivosOperando () {
		return relatorioRepository.findRelatorioColetivoOperando();
	}
	
	public List<RelatorioColetivo> relatorioDosColetivosManutencao () {
		return relatorioRepository.findRelatorioColetivoManutencao();
	}
	
	public List<RelatorioColetivo> relatorioDosColetivosParado () {
		return relatorioRepository.findRelatorioColetivoParado();
	}
	
	
	
	
}
