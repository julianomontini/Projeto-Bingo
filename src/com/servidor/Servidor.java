package com.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.common.ConexaoClienteServidor;

public class Servidor extends Thread{

	int porta;
	List<ConexaoClienteServidor> conexoesAbertas = new ArrayList<ConexaoClienteServidor>();
	
	public Servidor(int porta){
		this.porta = porta;
	}
	
	@Override
	public void run() {
		
		try {
			ServerSocket servidor = new ServerSocket(porta);
			System.out.println("Servidor escutando na porta " + porta);
			while(true){
				Socket conexao = servidor.accept();
				System.out.println("Conexão recebida do cliente " + conexao.getInetAddress());
				ConexaoClienteServidor c = new ConexaoClienteServidor(conexao){

					@Override
					public void receber(Object o) {
						System.out.println(o.toString());
					}

					@Override
					public void escrever() {
						// TODO Auto-generated method stub
						
					}
					
				};
				
				conexoesAbertas.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
