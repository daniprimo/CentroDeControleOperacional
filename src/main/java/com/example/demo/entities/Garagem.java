package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tb_garagem")
public class Garagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@Embedded
	private Endereco endereco;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "garagem")
	private List<Coletivo> coletivos = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "garagem")
	private List<Funcionario> funcionarios = new ArrayList<>();

	public Garagem(String nome, String logradouro, String cep) {
		this.nome = nome;
		this.endereco = new Endereco(logradouro, cep);
	}


	
	public void adicionarColetivo (Coletivo coletivo) {
		this.coletivos.add(coletivo);
	}

	public void adicionarFuncionario (Coletivo coletivo) {
		this.coletivos.add(coletivo);
	}
	

}
