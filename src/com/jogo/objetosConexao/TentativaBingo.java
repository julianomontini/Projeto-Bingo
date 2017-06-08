package com.jogo.objetosConexao;

import java.io.Serializable;
import java.util.List;

import com.database.DBOS.UsuarioBO;

/**
 * Representa uma tentativa de bingo
 * @author Juliano
 *
 */
public class TentativaBingo implements Serializable{
	private final UsuarioBO usuario;
	private final List<Integer> numerosJogados;
	public TentativaBingo(UsuarioBO usuario, List<Integer> numerosJogados) {
		this.usuario = usuario;
		this.numerosJogados = numerosJogados;
	}
	/**
	 * usuario da tentativa
	 * @return
	 */
	public UsuarioBO getUsuario() {
		return usuario;
	}
	/**
	 * numeros da tentativa
	 * @return
	 */
	public List<Integer> getNumerosJogados() {
		return numerosJogados;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numerosJogados == null) ? 0 : numerosJogados.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		TentativaBingo other = (TentativaBingo) obj;
		if (numerosJogados == null) {
			if (other.numerosJogados != null)
				return false;
		} else if (!numerosJogados.equals(other.numerosJogados))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TentativaBingo [usuario=" + usuario + ", numerosJogados=" + numerosJogados + "]";
	}
	
	
}
