package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Garagem;
import com.example.demo.repository.GaragemRepository;

@Service
public class GaragemService {

	private GaragemRepository garagemRepository;

	@Autowired
	public GaragemService(GaragemRepository garagemRepository) {
		this.garagemRepository = garagemRepository;
	}

	public List<Garagem> listaDasGaragens() {
		return garagemRepository.findAll();
	}

	public Garagem buscarGaragemPorId(Long id) {
		return garagemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public Garagem adicionarGaragens(Garagem garagem) {
		return garagemRepository.save(garagem);
	}

	public Garagem atualizarGaragem(Garagem garagem, Long id) {
		Garagem garagemAtual = buscarGaragemPorId(id);
		garagem.setId(garagemAtual.getId());
		return garagemRepository.save(garagem);
	}

	public void deletarPorId(Long id) {
		Garagem garagemAtual = buscarGaragemPorId(id);
		garagemRepository.delete(garagemAtual);
	}

}
