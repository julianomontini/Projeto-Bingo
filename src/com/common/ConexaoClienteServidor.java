package com.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public abstract class ConexaoClienteServidor implements Receber{

	private Socket conexao;
	private ObjectOutputStream escreverObjetos;
	
	public ConexaoClienteServidor(Socket conexao) throws Exception{
		new Listener(conexao.getInputStream(), this);
		this.escreverObjetos = new ObjectOutputStream(conexao.getOutputStream());	
	}

	public ObjectOutputStream getEscreverObjetos() {
		return escreverObjetos;
	}
	
}
