package com.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.common.ConexaoClienteServidor;
import com.jogo.Bingo;
import com.jogo.objetosConexao.Mensagem;
import com.jogo.objetosConexao.NumeroSorteado;

public class Servidor extends Thread{

	int porta;
	boolean jogoEmAndamento = false;
	boolean jogadorConectado = false;
	List<ConexaoClienteServidor> conexoesAbertas = new ArrayList<ConexaoClienteServidor>();
	Timer timer = new Timer();
	
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
				conexao.hashCode();
				System.out.println("Conexão recebida do cliente " + conexao.getInetAddress());
				ConexaoClienteServidor c = new ConexaoClienteServidor(conexao){

					@Override
					public void receber(Object o) {
						mapeiaAcoes(o);
					}
					
				};
				
				if(jogoEmAndamento)
					c.getEscreverObjetos().writeObject(new Mensagem("Jogo indisponivel, volte mais tarde"));
				else{
					conexoesAbertas.add(c);
					if(!jogadorConectado){
						jogadorConectado = true;
						timer.schedule(new TimerTask() {
							
							@Override
							public void run() {
								Servidor.this.iniciaPartida();
							}
						}, 10000);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	private void mapeiaAcoes(Object o){
	}
	
	
	private void writeToAll(Object o){
		Iterator i = this.conexoesAbertas.iterator();
		
		while(i.hasNext()){
			try {
				ConexaoClienteServidor conexao = (ConexaoClienteServidor)i.next();
				conexao.getEscreverObjetos().writeObject(o);
			} catch (IOException e) {
				System.out.println("Cliente perdeu a conexao, desconectando");
				i.remove();
			}
		}
	}
	
	private void iniciaPartida(){
		System.out.println("Iniciando Jogo");
		this.jogoEmAndamento = true;
		Iterator conexoes = conexoesAbertas.iterator();
		while(conexoes.hasNext()){
			ConexaoClienteServidor c = (ConexaoClienteServidor)conexoes.next();
			try {
				c.getEscreverObjetos().writeObject(Bingo.geraCartela());
				c.getEscreverObjetos().flush();
			} catch (IOException e) {
				System.out.println("Cliente perdeu conexao, desconectando");
				conexoes.remove();
			}
		}
		
		List<Integer> numerosSorteados = new ArrayList<Integer>();
		
		for(int i = 1; i < 62; i++){
			numerosSorteados.add(i);
		}
		
		Collections.shuffle(numerosSorteados);
		
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if(!numerosSorteados.isEmpty()){
					Servidor.this.writeToAll(new NumeroSorteado(numerosSorteados.get(0)));
					numerosSorteados.remove(0);
				}else{
					Servidor.this.writeToAll(new Mensagem("Fim do jogo - Nao ha ganhador"));
					Servidor.this.jogoEmAndamento = false;
					new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							iniciaPartida();
							
						}
					}, 10000);;
					this.cancel();
				}
				
			}
		}, 1000, 1000);
	}
}
