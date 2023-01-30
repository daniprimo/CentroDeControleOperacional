package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.coletivoDtos.RelatorioColetivo;
import com.example.demo.service.RelatorioService;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

	private RelatorioService relatorioService;
	
	@Lazy
	@Autowired
	public RelatorioController(RelatorioService relatorioService) {
		this.relatorioService = relatorioService;
	}
	
	@GetMapping
	public ResponseEntity<List<RelatorioColetivo>> relatorioSimplesColetivo () {
		return ResponseEntity.ok(relatorioService.listarRelatorio());
	}
	
	@GetMapping("/filial")
	public ResponseEntity<List<RelatorioColetivo>> relatorioColetivoFilial () {
		return ResponseEntity.ok(relatorioService.relatorioDosColetivosDaGaragemFilial());
	}
	
	@GetMapping("/matriz")
	public ResponseEntity<List<RelatorioColetivo>> relatorioColetivoMatriz () {
		return ResponseEntity.ok(relatorioService.relatorioDosColetivosDaGaragemMatriz());
	}
	
	@GetMapping("/manutencao")
	public ResponseEntity<List<RelatorioColetivo>> relatorioColetivoManutencao () {
		return ResponseEntity.ok(relatorioService.relatorioDosColetivosManutencao());
	}
	
	@GetMapping("/parado")
	public ResponseEntity<List<RelatorioColetivo>> relatorioColetivosParado () {
		return ResponseEntity.ok(relatorioService.relatorioDosColetivosParado());
	}
	
	@GetMapping("/operando")
	public ResponseEntity<List<RelatorioColetivo>> relatorioColetivosOperado () {
		return ResponseEntity.ok(relatorioService.relatorioDosColetivosOperando());
	}
	
	
	
	
}
