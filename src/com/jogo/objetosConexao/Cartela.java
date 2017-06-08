package com.jogo.objetosConexao;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 
 * @author Juliano
 * Cartela do bingo
 */
public class Cartela implements Serializable{

	private Integer[] valores;

	public Cartela(Integer[] valores) {
		this.valores = valores;
	}

	/**
	 * setter valores
	 * @return
	 */
	public Integer[] getValores() {
		return valores;
	}

	/**
	 * getter valores
	 * @param valores
	 */
	public void setValores(Integer[] valores) {
		this.valores = valores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(valores);
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
		Cartela other = (Cartela) obj;
		if (!Arrays.equals(valores, other.valores))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cartela [valores=" + Arrays.toString(valores) + "]";
	}
	
	
	
}
