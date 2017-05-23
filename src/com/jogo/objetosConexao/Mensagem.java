package com.jogo.objetosConexao;

import java.io.Serializable;

public class Mensagem implements Serializable{
	private String texto;

	public Mensagem(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
