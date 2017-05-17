package com.jogo;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.common.ConexaoClienteServidor;
import com.database.Conexao;

import javafx.stage.Stage;

public class Jogo {

	private String nomeJogador;
	private Stage primaryStage;
	
	public Jogo(String nomeJogador, Stage primaryStage){
		
		this.nomeJogador = nomeJogador;
		this.primaryStage = primaryStage;
		iniciaConexao();
		
	}
	
	private void iniciaConexao(){
		
		try {
			ConexaoClienteServidor conexao = new ConexaoClienteServidor(new Socket("localhost",4200)) {
				
				@Override
				public void receber(Object o) {
					if(o instanceof Cartela)
						novoJogo((Cartela)o);
				}
			};
			
			conexao.getEscreverObjetos().writeObject(new JogadorPronto(false));
			conexao.getEscreverObjetos().writeObject(new JogadorPronto(true));
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void novoJogo(Cartela c){
		System.out.println("Cartela recebida");
	}
	
}
