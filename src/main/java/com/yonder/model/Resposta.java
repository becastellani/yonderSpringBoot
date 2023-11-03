package com.yonder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Resposta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String texto;
	
	@Column 
	private boolean correta;
	
	@ManyToOne
	@JoinColumn(name = "id_pergunta")
	private Perguntas pergunta;
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isCorreta() {
		return correta;
	}

	public void setCorreta(boolean correta) {
		this.correta = correta;
	}

	public Perguntas getPergunta() {
		return pergunta;
	}

	public void setPergunta(Perguntas pergunta) {
		this.pergunta = pergunta;
	}
	
	
	
}
