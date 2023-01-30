package com.example.demo.entities;

import javax.persistence.Embeddable;

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
@Embeddable
public class Endereco {
	
	private String logradouro;
	private String numero;
	private String cep;
	private String bairro;
	private String cidade;

	
	public Endereco(String logradouro, String cep) {
			this.logradouro = logradouro;
			this.cep = cep;
	}
	
}
