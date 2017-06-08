package com.common;

public class VitoriasJogador {
	Integer vitorias;
	String jogador;
	public VitoriasJogador(Integer vitorias, String jogador) {
		this.vitorias = vitorias;
		this.jogador = jogador;
	}
	public Integer getVitorias() {
		return vitorias;
	}
	public void setVitorias(Integer vitorias) {
		this.vitorias = vitorias;
	}
	public String getJogador() {
		return jogador;
	}
	public void setJogador(String jogador) {
		this.jogador = jogador;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jogador == null) ? 0 : jogador.hashCode());
		result = prime * result + ((vitorias == null) ? 0 : vitorias.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VitoriasJogador other = (VitoriasJogador) obj;
		if (jogador == null) {
			if (other.jogador != null)
				return false;
		} else if (!jogador.equals(other.jogador))
			return false;
		if (vitorias == null) {
			if (other.vitorias != null)
				return false;
		} else if (!vitorias.equals(other.vitorias))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "VitoriasJogador [vitorias=" + vitorias + ", jogador=" + jogador + "]";
	}
	
	
}
