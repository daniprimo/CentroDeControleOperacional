package com.example.demo.dto.relatorio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColetivoDTO {

	private Long id;
	private String placa;
	private String prefixo;
	private String modelo;
	private String cor;
	private String doc;
	private String status;
	private Long garagem;

	public ColetivoDTO(String placa, String prefixo, String modelo, String cor, String doc, String status) {
		super();
		this.placa = placa;
		this.prefixo = prefixo;
		this.modelo = modelo;
		this.cor = cor;
		this.doc = doc;
		this.status = status;
	}
	
	public ColetivoDTO(Long id, String placa, String prefixo, String modelo, String cor, String doc, String status) {
		this.id = id;
		this.placa = placa;
		this.prefixo = prefixo;
		this.modelo = modelo;
		this.cor = cor;
		this.doc = doc;
		this.status = status;
	}

}
