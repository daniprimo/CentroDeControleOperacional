package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.InfoGaragemDTO;
import com.example.demo.dto.relatorio.ColetivoDTO;
import com.example.demo.entities.Coletivo;
import com.example.demo.repositoryJDBC.RepositoryJDBC;

@Service
public class ColetivoServiceDAO  {

	@Autowired
	private RepositoryJDBC repository;

	public ColetivoServiceDAO(RepositoryJDBC repositoryJDBC) {
		this.repository = repositoryJDBC;
	}


	public InfoGaragemDTO quantosColetivosNaGaragem() {
		return repository.quantosColetivosNaGaragem();
	}


	public List<Coletivo> listarColetivos() {
		return repository.listarColetivos();
	}


	public Long adicionarColetivo(ColetivoDTO dto) {
		/*
		 * Nao consegui implantar o as excessões
		 * */
		return repository.adicionarColetivo(dto);
	}


	public Coletivo buscarColetivoPeloId(Long id) {
		return repository.buscarColetivoPeloId(id);
	}


	public void excluirColetivoPorPrefixo(String prefixo) {
		repository.excluirColetivoPorPrefixo(prefixo);
	}


	public void editarColetivo(Long id, ColetivoDTO coletivo) {
		repository.editarColetivo(id, coletivo);
	}


	public Coletivo buscarColetivoPelaPlaca(String placa) {
		return repository.buscarColetivoPelaPlaca(placa);
	}


	public Coletivo buscarColetivoPeloPrefixo(String prefixo) {
		return repository.buscarColetivoPeloPrefixo(prefixo);
	}



}
