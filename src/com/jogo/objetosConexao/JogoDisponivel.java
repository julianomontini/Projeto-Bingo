package com.jogo.objetosConexao;

import java.io.Serializable;

public class JogoDisponivel implements Serializable{
	private Boolean disponivel;

	public JogoDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}
}
