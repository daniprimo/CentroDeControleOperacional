package com.example.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.dto.ColetivoDTO;
import com.example.demo.entities.Coletivo;

public interface ColetivoDAO {

	public void criarTabela() throws SQLException;

	public List<Coletivo> listarColetivos() throws SQLException;

	public Long adicionarColetivo(ColetivoDTO dto) throws SQLException;

	public Coletivo buscarColetivoPeloPrefixo(String prefixo) throws SQLException;

	public Coletivo buscarColetivoPeloId (Long id) throws SQLException;

	public Coletivo buscarColetivoPelaPlaca(String placa) throws SQLException;

	public void editarColetivo(Long id, ColetivoDTO coletivo) throws SQLException;

	public void excluirColetivoPorPrefixo(String prefixo) throws SQLException;

	public void excluirColetivoPorPlaca(String placa) throws SQLException;


}
