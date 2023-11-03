package com.yonder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Perguntas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String cabecalho;
	
	@Column
	private String tipo_prova;
	
	@Column
	private int nivel;
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	public String getTipo_prova() {
		return tipo_prova;
	}

	public void setTipo_prova(String tipo_prova) {
		this.tipo_prova = tipo_prova;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	
}
