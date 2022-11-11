package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Coletivo;
import com.example.demo.repository.ColetivoRepository;

@RestController
@RequestMapping("/coletivo")
public class ColetivoController {
	
	@Autowired
	private ColetivoRepository coletivoRepository;
	
	@GetMapping
	public List<Coletivo> listarColetivos() {
		return coletivoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Coletivo adiionar(@RequestBody Coletivo coletivo) {
		return coletivoRepository.save(coletivo);
	}

}
