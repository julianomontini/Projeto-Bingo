package com.jogo;

import java.net.Socket;

import com.common.ConexaoClienteServidor;
import com.common.Receber;
import com.jogo.objetosConexao.Cartela;
import com.jogo.objetosConexao.Mensagem;
import com.jogo.objetosConexao.NumeroSorteado;
import com.telas.TelaPrincipal;

import javafx.application.Platform;
import javafx.stage.Stage;

public class Jogo {

	private String nomeJogador;
	private Stage primaryStage;
	private ConexaoClienteServidor conexao;
	private TelaPrincipal telaPrincipal = null;
	private Receber chamante;
	
	public Jogo(String nomeJogador, Stage primaryStage, Receber chamante){
		
		this.nomeJogador = nomeJogador;
		this.primaryStage = primaryStage;
		this.chamante = chamante;
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
		if(o instanceof NumeroSorteado)
			this.telaPrincipal.escreverMensagem("Numero Sorteado: " + ((NumeroSorteado)o).getNumero());
		if(o instanceof Mensagem){
			Mensagem m = (Mensagem)o;
			if(m.getTexto().equals("Jogo indisponivel, volte mais tarde")){
				this.chamante.receber(m);
			}else{
				this.telaPrincipal.escreverMensagem("Mensagem: " + ((Mensagem)o).getTexto());
			}
		}
	}
	
	private void iniciaNovoJogo(Cartela c){
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Jogo.this.telaPrincipal = new TelaPrincipal(Jogo.this.primaryStage, c, Jogo.this.conexao);
				
			}
		});
	}
	
}
