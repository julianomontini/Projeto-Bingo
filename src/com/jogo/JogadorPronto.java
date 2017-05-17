package com.jogo;

import java.io.Serializable;

public class JogadorPronto implements Serializable{

	private Boolean pronto;

	public JogadorPronto(Boolean pronto) {
		this.pronto = pronto;
	}

	public Boolean getPronto() {
		return pronto;
	}

	public void setPronto(Boolean pronto) {
		this.pronto = pronto;
	}
	
}
