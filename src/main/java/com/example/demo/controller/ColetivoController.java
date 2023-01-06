package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Coletivo;
import com.example.demo.exceptions.excessoes.PrefixoExistenteException;
import com.example.demo.service.ColetivoService;

@RestController
@RequestMapping("/coletivo")
public class ColetivoController {
	
	@Autowired
	private ColetivoService coletivoService;
	
	public ColetivoController(ColetivoService coletivoService) {
		this.coletivoService = coletivoService;
	}

	@GetMapping
	public ResponseEntity<List<Coletivo>> listarColetivos() throws SQLException {
		 return ResponseEntity.ok(coletivoService.listaColetivos());
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Coletivo> adiionarColetivo(@RequestBody Coletivo coletivo) throws PrefixoExistenteException {
				Coletivo coletivoAlterado = coletivoService.adicionar(coletivo);
				return ResponseEntity.ok(coletivoAlterado);		
		
	}
	
	@GetMapping("id={id}")
	public ResponseEntity<Coletivo> consultarColetivoPeloId(@PathVariable Long id){
		Coletivo coletivo = coletivoService.pesquisarColetivoPorId(id);
		return ResponseEntity.ok(coletivo);
	}
	
	@GetMapping("placa={placa}")
	public ResponseEntity<Coletivo> consultarColetivoPelaPlaca(@PathVariable String placa){
		Coletivo coletivo = coletivoService.pesquisarColetivoPorPlaca(placa);
		return ResponseEntity.ok(coletivo);
	}
	
	@GetMapping("{prefixo}")
	public ResponseEntity<Coletivo> consultarColetivoPeloPrefixo(@PathVariable String prefixo){
		Coletivo coletivo = coletivoService.pesquisarColetivoPorPrefixo(prefixo);
		return ResponseEntity.ok(coletivo);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Coletivo> alterarColetivo(@RequestBody Coletivo coletivo,@PathVariable Long id){
	 Coletivo alterado = coletivoService.alterarColetivoPorId(coletivo, id);
	return ResponseEntity.ok(alterado);
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<Coletivo> deletarColetivo(@PathVariable Long id){
		coletivoService.deletarColetivoPorId(id);
		return ResponseEntity.ok().build();
	}
	
	

}
