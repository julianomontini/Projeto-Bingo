package com.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.database.DBOS.UsuarioBO;

/**
 * 
 * @author Juliano
 *	Classe para estabelecer a conexao entre o cliente e o servidor
 */
public class ConexaoClienteServidor{
	
	private UsuarioBO jogador;
	private Socket conexao;
	private ObjectOutputStream escreverObjetos;
	private Listener listener;
	
	/**
	 * 
	 * @param conexao Socket da conexao
	 * @param receber Objeto que executará ações
	 * @throws Exception Caso falhe em crirar o ObjectOutputStream
	 */
	public ConexaoClienteServidor(Socket conexao, Receber receber) throws Exception{
		this.listener = new Listener(conexao.getInputStream(), receber);
		this.escreverObjetos = new ObjectOutputStream(conexao.getOutputStream());	
	}

	/**
	 * 
	 * @return O ObjectOutputStream
	 */
	public ObjectOutputStream getEscreverObjetos() {
		return escreverObjetos;
	}
	
	/**
	 * 
	 * @param Novo objeto que executará açoes
	 */
	public void setReceber(Receber r){
		this.listener.setReceber(r);
	}

	/**
	 * 
	 * @return Jogador que abriu essa conexao
	 */
	public UsuarioBO getJogador() {
		return jogador;
	}

	/**
	 * 
	 * @param jogador Novo jogador da conexao
	 */
	public void setJogador(UsuarioBO jogador) {
		this.jogador = jogador;
	}
	
}
