package com.example.demo.dto;

public class InfoGaragemDTO {

	private int garagemMatriz;
	private int garagemFilial;

	public InfoGaragemDTO(int garagemMatriz, int garagemFilial) {
		this.garagemMatriz = garagemMatriz;
		this.garagemFilial = garagemFilial;
	}

	public InfoGaragemDTO() {
	}

	public int getGaragemMatriz() {
		return garagemMatriz;
	}

	public void setGaragemMatriz(int garagemMatriz) {
		this.garagemMatriz = garagemMatriz;
	}

	public int getGaragemFilial() {
		return garagemFilial;
	}

	public void setGaragemFilial(int garagemFilial) {
		this.garagemFilial = garagemFilial;
	}

}
