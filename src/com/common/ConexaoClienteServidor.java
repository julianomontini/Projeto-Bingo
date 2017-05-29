package com.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.database.DBOS.UsuarioBO;

public class ConexaoClienteServidor{
	
	private UsuarioBO jogador;
	private Socket conexao;
	private ObjectOutputStream escreverObjetos;
	private Listener listener;
	
	public ConexaoClienteServidor(Socket conexao, Receber receber) throws Exception{
		this.listener = new Listener(conexao.getInputStream(), receber);
		this.escreverObjetos = new ObjectOutputStream(conexao.getOutputStream());	
	}

	public ObjectOutputStream getEscreverObjetos() {
		return escreverObjetos;
	}
	
	public void setReceber(Receber r){
		this.listener.setReceber(r);
	}

	public UsuarioBO getJogador() {
		return jogador;
	}

	public void setJogador(UsuarioBO jogador) {
		this.jogador = jogador;
	}
	
}
