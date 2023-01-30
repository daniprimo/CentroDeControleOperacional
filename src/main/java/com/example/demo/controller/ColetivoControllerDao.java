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

import com.example.demo.dto.InfoGaragemDTO;
import com.example.demo.dto.relatorio.ColetivoDTO;
import com.example.demo.entities.Coletivo;
import com.example.demo.service.ColetivoServiceDAO;

@RestController
@RequestMapping("/coletivoV2")
public class ColetivoControllerDao {

	@Autowired
	private ColetivoServiceDAO service;
	
	
	public ColetivoControllerDao(ColetivoServiceDAO dao) {
		this.service = dao;
	}
	
	
@GetMapping
public ResponseEntity<List<Coletivo>> listarColetivos () throws SQLException {
	return ResponseEntity.ok(service.listarColetivos());
}

	@GetMapping("/infos")
	public ResponseEntity<InfoGaragemDTO> mostrarInfos() {
		return ResponseEntity.ok(service.quantosColetivosNaGaragem());
	}

@PostMapping
@ResponseStatus(code = HttpStatus.CREATED)
public ResponseEntity<Coletivo> adicionarColetivo(@RequestBody ColetivoDTO dto) {
			Long id = service.adicionarColetivo(dto);
			
	return ResponseEntity.ok(service.buscarColetivoPeloId(id));			
}

@GetMapping("placa={placa}")
public ResponseEntity<Coletivo> consultarColetivoPelaPlaca(@PathVariable String placa) {
	return ResponseEntity.ok(service.buscarColetivoPelaPlaca(placa));
}

@GetMapping("prefixo={prefixo}")
public ResponseEntity<Coletivo> consultarColetivoPeloPrefixo(@PathVariable String prefixo) {
	return ResponseEntity.ok(service.buscarColetivoPeloPrefixo(prefixo));
}

@GetMapping("id={id}")
public ResponseEntity<Coletivo> consultarColetivoPeloPrefixo(@PathVariable Long id) {
	return ResponseEntity.ok(service.buscarColetivoPeloId(id));
}

@PutMapping("{id}")
public ResponseEntity<Coletivo> alterarColetivo(@RequestBody ColetivoDTO coletivo,@PathVariable Long id) {
	service.editarColetivo(id, coletivo);
	return ResponseEntity.ok(service.buscarColetivoPeloId(id));
}

@DeleteMapping("deletar={prefixo}")
public ResponseEntity<Coletivo> deletarColetivo(@PathVariable String prefixo){
	service.excluirColetivoPorPrefixo(prefixo);
	return ResponseEntity.ok().build();
}

}
