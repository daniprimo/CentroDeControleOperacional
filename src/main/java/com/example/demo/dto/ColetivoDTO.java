package com.example.demo.dto;

public class ColetivoDTO {

	private String placa;
	private String prefixo;
	private String modelo;
	private String cor;
	private String doc;
	private String status;

	public ColetivoDTO(String placa, String prefixo, String modelo, String cor, String doc, String status) {
		super();
		this.placa = placa;
		this.prefixo = prefixo;
		this.modelo = modelo;
		this.cor = cor;
		this.doc = doc;
		this.status = status;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
