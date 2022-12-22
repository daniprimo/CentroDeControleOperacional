package com.example.demo.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_coletivo")
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
	
	public Coletivo(Long id, String placa, String prefixo, String modelo, String cor, String doc, String status) {
		this.id = id;
		this.placa = placa;
		this.prefixo = prefixo;
		this.modelo = modelo;
		this.cor = cor;
		this.doc = doc;
		this.status = status;
	}

	public Coletivo(Long id, String placa, String prefixo, String modelo, String doc) {
		this.id = id;
		this.placa = placa;
		this.prefixo = prefixo;
		this.modelo = modelo;
		this.doc = doc;
	}

	public Coletivo() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(doc, id, modelo, placa, prefixo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coletivo other = (Coletivo) obj;
		return Objects.equals(doc, other.doc) && Objects.equals(id, other.id) && Objects.equals(modelo, other.modelo)
				&& Objects.equals(placa, other.placa) && Objects.equals(prefixo, other.prefixo);
	}
	
	
	

	

}
