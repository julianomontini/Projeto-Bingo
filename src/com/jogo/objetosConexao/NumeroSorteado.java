package com.jogo.objetosConexao;

import java.io.Serializable;

public class NumeroSorteado implements Serializable {
	Integer numero;

	public NumeroSorteado(Integer numero) {
		this.numero = numero;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}
