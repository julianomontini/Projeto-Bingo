package com.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConexaoCliente implements AcoesClienteServidor{

	private final Socket conexao;
	private final InputStream inputStream;
	private final OutputStream outputStream;
	private final ObjectOutputStream objOutput;
	
	public ConexaoCliente(Socket conexao) throws Exception{
		this.conexao = conexao;
		this.inputStream = conexao.getInputStream();
		this.outputStream = conexao.getOutputStream();
		this.objOutput = new ObjectOutputStream(this.outputStream);
		ListenerCliente listener = new ListenerCliente(this, this.inputStream);
	}

	

	@Override
	public void acao(Object o) {
		// TODO Auto-generated method stub
		
	}
	
	private static class ListenerCliente extends Thread{
		final AcoesClienteServidor acoes;
		final InputStream is;
		
		public ListenerCliente(AcoesClienteServidor acoes, InputStream is){
			this.acoes = acoes;
			this.is = is;
			this.start();
		}

		@Override
		public void run() {
			
			try {
				ObjectInputStream ois = new ObjectInputStream(this.is);
				while(true){
					System.out.println(ois.readObject().toString());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
