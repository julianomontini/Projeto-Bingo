package com.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.common.ConexaoClienteServidor;
import com.jogo.Bingo;
import com.jogo.JogadorPronto;

public class Servidor extends Thread{

	int porta;
	List<ConexaoClienteServidor> conexoesAbertas = new ArrayList<ConexaoClienteServidor>();
	Stack<JogadorPronto> jogadoresSemOk = new Stack<JogadorPronto>();
	
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
						
						if(o instanceof JogadorPronto)
							jogadorPronto((JogadorPronto)o);
					}
					
				};
				conexoesAbertas.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void jogadorPronto(JogadorPronto j){
		if(j.getPronto()){
			jogadoresSemOk.pop();
			if(jogadoresSemOk.isEmpty())
				iniciaPartida();
		}else{
			jogadoresSemOk.push(j);
		}
	}
	
	
	private void writeToAll(Object o){
		
	}
	
	private void iniciaPartida(){
		for(ConexaoClienteServidor c : conexoesAbertas){
			try {
				c.getEscreverObjetos().writeObject(Bingo.geraCartela());
				c.getEscreverObjetos().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
