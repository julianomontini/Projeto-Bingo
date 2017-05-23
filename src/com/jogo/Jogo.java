package com.jogo;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.common.ConexaoClienteServidor;
import com.database.Conexao;
import com.jogo.objetosConexao.Cartela;
import com.jogo.objetosConexao.JogoDisponivel;
import com.jogo.objetosConexao.NumeroSorteado;
import com.telas.TelaPrincipal;

import javafx.application.Platform;
import javafx.stage.Stage;

public class Jogo {

	private String nomeJogador;
	private Stage primaryStage;
	private ConexaoClienteServidor conexao;
	
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
					mapaAcoes(o);
				}
			};
			this.conexao = conexao;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void mapaAcoes(Object o){
		if(o instanceof Cartela)
			iniciaNovoJogo((Cartela)o);
		if(o instanceof JogoDisponivel)
			exibeMensagemEsperar();
		if(o instanceof NumeroSorteado)
			System.out.println("Numero sorteado: " + ((NumeroSorteado) o).getNumero());
	}
	
	private void iniciaNovoJogo(Cartela c){
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new TelaPrincipal(Jogo.this.primaryStage, c, Jogo.this.conexao);
				
			}
		});
	}
	
	private void exibeMensagemEsperar(){
		
	}
	
}
