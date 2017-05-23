package com.jogo.objetosConexao;

import java.io.Serializable;

public class Cartela implements Serializable{

	private Integer[] valores;

	public Cartela(Integer[] valores) {
		this.valores = valores;
	}

	public Integer[] getValores() {
		return valores;
	}

	public void setValores(Integer[] valores) {
		this.valores = valores;
	}
	
	
	
}
