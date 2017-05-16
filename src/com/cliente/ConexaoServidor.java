package com.cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ConexaoServidor{

	private String endereço;
	private int porta;
	private Socket conexao;
	
	public ConexaoServidor(String endereco, int porta) throws Exception{
		this.endereço = endereço;
		this.porta = porta;
		this.conexao = new Socket(endereco, porta);
		System.out.println("Conectado ao servidor " + conexao.getInetAddress() + " porta " + porta);
	}
	
	private static class ListenerServidor extends Thread{

		private InputStream is;
		private ObjectInputStream ois;
		
		public ListenerServidor(InputStream is){
			try {
				this.is= is;
				this.ois = new ObjectInputStream(is);
				this.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(true){
				try {
					System.out.println(ois.readObject().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
}
