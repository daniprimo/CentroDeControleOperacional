package com.example.demo.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

import com.example.demo.configuracao.ColetivoRepositoyJDBC;
import com.example.demo.dto.ColetivoDTO;
import com.example.demo.dto.InfoGaragemDTO;
import com.example.demo.entities.Coletivo;
import com.example.demo.service.ColetivoServiceDAO;

@RestController
@RequestMapping("/coletivoV2")
public class ColetivoControllerDao {
	
	Connection connection = new ColetivoRepositoyJDBC().conectandoComBancoDeDados();
	ColetivoServiceDAO dao = new ColetivoServiceDAO(connection);

	
@GetMapping
public ResponseEntity<List<Coletivo>> listarColetivos () throws SQLException {
	dao.quantosColetivosNaGaragem();
	return ResponseEntity.ok(dao.listarColetivos());
}

@GetMapping("/infos")
public ResponseEntity<InfoGaragemDTO> mostrarInfos () throws SQLException {
	
	return ResponseEntity.ok(dao.quantosColetivosNaGaragem());
}

@PostMapping
@ResponseStatus(code = HttpStatus.CREATED)
public ResponseEntity<Coletivo> adicionarColetivo(@RequestBody ColetivoDTO dto) throws  SQLException {
			Long id = dao.adicionarColetivo(dto);
	return ResponseEntity.ok(dao.buscarColetivoPeloId(id));			
}

@GetMapping("placa={placa}")
public ResponseEntity<Coletivo> consultarColetivoPelaPlaca(@PathVariable String placa) throws SQLException{
	return ResponseEntity.ok(dao.buscarColetivoPelaPlaca(placa));
}

@GetMapping("prefixo={prefixo}")
public ResponseEntity<Coletivo> consultarColetivoPeloPrefixo(@PathVariable String prefixo) throws SQLException{
	return ResponseEntity.ok(dao.buscarColetivoPeloPrefixo(prefixo));
}

@GetMapping("id={id}")
public ResponseEntity<Coletivo> consultarColetivoPeloPrefixo(@PathVariable Long id) throws SQLException{
	return ResponseEntity.ok(dao.buscarColetivoPeloId(id));
}

@PutMapping("{id}")
public ResponseEntity<Coletivo> alterarColetivo(@RequestBody ColetivoDTO coletivo,@PathVariable Long id) throws SQLException{
	dao.editarColetivo(id, coletivo);
	return ResponseEntity.ok(dao.buscarColetivoPeloId(id));
}

@DeleteMapping("deletar={prefixo}")
public ResponseEntity<Coletivo> deletarColetivo(@PathVariable String prefixo) throws SQLException{
	dao.excluirColetivoPorPrefixo(prefixo);
	return ResponseEntity.ok().build();
}
	
}
