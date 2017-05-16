package com.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread{

	int porta;
	
	public Servidor(int porta){
		this.porta = porta;
	}
	
	@Override
	public void run() {
		
		try {
			ServerSocket servidor = new ServerSocket(porta);
			
			while(true){
				
				Socket conexao = servidor.accept();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
