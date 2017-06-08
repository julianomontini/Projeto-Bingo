package com.jogo.objetosConexao;

import java.io.Serializable;

/**
 * Classe para mensagens
 * @author Juliano
 *
 */
public class Mensagem implements Serializable{
	private String texto;

	
	public Mensagem(String texto) {
		this.texto = texto;
	}

	/**
	 * getter mensagem
	 * @return
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * setter mensagem
	 * @param texto
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
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
		Mensagem other = (Mensagem) obj;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mensagem [texto=" + texto + "]";
	}
	
	
}
