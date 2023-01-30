package com.example.demo.repositoryJDBC;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.dto.InfoGaragemDTO;
import com.example.demo.dto.relatorio.ColetivoDTO;
import com.example.demo.entities.Coletivo;

@Repository
public interface RepositoryJDBC {


	public InfoGaragemDTO quantosColetivosNaGaragem();

	public Long adicionarColetivo(ColetivoDTO dto);

	public List<Coletivo> listarColetivos();

	public Coletivo buscarColetivoPeloPrefixo(String prefixo);

	public Coletivo buscarColetivoPeloId(Long id);

	public Coletivo buscarColetivoPelaPlaca(String placa);

	public void excluirColetivoPorPlaca(String placa);

	public void excluirColetivoPorPrefixo(String prefixo);

	public void criarTabela() throws SQLException;

	public void editarColetivo(Long id, ColetivoDTO coletivo);

}
