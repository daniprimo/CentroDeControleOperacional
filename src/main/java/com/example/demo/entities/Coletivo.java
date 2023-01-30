package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.entities.validacoes.ValidacoesColetivo;
import com.example.demo.exceptions.excessoes.DocumentoExistenteException;
import com.example.demo.exceptions.excessoes.PlacaExistenteException;
import com.example.demo.exceptions.excessoes.PrefixoExistenteException;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_coletivo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coletivo {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = true, unique = true)
	private String placa;
	@Column(nullable = false, unique = true)
	private String prefixo;
	@Column(nullable = false)
	private String modelo;
	@Column(nullable = false)
	private String cor;
	@Column(nullable = false, unique = true)
	private String doc;
	@Column(nullable = false)
	private String status;
	
	
	@ManyToOne
	@JsonBackReference
	private Garagem garagem;

	public Coletivo(Long id, String placa, String prefixo, String modelo, String cor, String doc, String status) {
		super();
		this.id = id;
		this.placa = placa;
		this.prefixo = prefixo;
		this.modelo = modelo;
		this.cor = cor;
		this.doc = doc;
		this.status = status;
	}

	
	


	public Coletivo(long id, String placa, String prefixo, String modelo, String documento) {
		this.id = id;
		this.placa = placa;
		this.prefixo = prefixo;
		this.modelo = modelo;
		this.doc = documento;
	}


	public void validacao() throws PrefixoExistenteException, PlacaExistenteException, DocumentoExistenteException {
		ValidacoesColetivo.validandoSeOPrefixoExiste(prefixo);
		ValidacoesColetivo.validandoSeOPlacaExiste(placa);
		ValidacoesColetivo.validandoSeODocExiste(doc);

	}
	
	
	public void adicionarColetivoNaGaragem () {
		garagem.adicionarColetivo(this);
	}
	


}
