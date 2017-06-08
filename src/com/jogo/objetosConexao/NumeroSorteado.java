package com.jogo.objetosConexao;

import java.io.Serializable;

/**
 * Representa numero sorteado 
 * @author Juliano
 *
 */
public class NumeroSorteado implements Serializable {
	Integer numero;

	public NumeroSorteado(Integer numero) {
		this.numero = numero;
	}

	/**
	 * getter numero
	 * @return
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * setter numero
	 * @param numero
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		NumeroSorteado other = (NumeroSorteado) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NumeroSorteado [numero=" + numero + "]";
	}
	
	
}
