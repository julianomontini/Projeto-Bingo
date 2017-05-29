package com.jogo.objetosConexao;

import java.io.Serializable;
import java.util.List;

import com.database.DBOS.UsuarioBO;

public class TentativaBingo implements Serializable{
	private final UsuarioBO usuario;
	private final List<Integer> numerosJogados;
	public TentativaBingo(UsuarioBO usuario, List<Integer> numerosJogados) {
		this.usuario = usuario;
		this.numerosJogados = numerosJogados;
	}
	public UsuarioBO getUsuario() {
		return usuario;
	}
	public List<Integer> getNumerosJogados() {
		return numerosJogados;
	}
	
	
}
