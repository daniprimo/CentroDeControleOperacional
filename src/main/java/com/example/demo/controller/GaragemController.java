package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Garagem;
import com.example.demo.service.GaragemService;

@RestController
@RequestMapping("/garagem")
public class GaragemController {

	private GaragemService garagemService;
	
	@Autowired
	public GaragemController(GaragemService garagemService) {
		this.garagemService = garagemService;
	}
	
	@GetMapping
	public List<Garagem> buscarTodasAsGaragens () {
		return garagemService.listaDasGaragens();
	}
	
	@GetMapping("{id}")
	public Garagem buscarTodasAsGaragens (@PathVariable Long id) {
		return garagemService.buscarGaragemPorId(id);
	}
	
	@PostMapping
	public Garagem adicionarGaragens (@RequestBody Garagem garagem) {
		return garagemService.adicionarGaragens(garagem);
	}
	
	@PutMapping("{id}")
	public Garagem atualziarGaragens (@RequestBody Garagem garagem, @PathVariable Long id) {
		return garagemService.atualizarGaragem(garagem, id);
	}
	
	@DeleteMapping("{id}")
	public void deletarPorId(@PathVariable Long id) {
		garagemService.deletarPorId(id);
	}
	
	
}
