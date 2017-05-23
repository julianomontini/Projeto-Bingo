package com.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import com.common.ConexaoClienteServidor;
import com.jogo.Bingo;
import com.jogo.objetosConexao.JogoDisponivel;
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
				conexoesAbertas.add(c);
				
				if(jogoEmAndamento)
					c.getEscreverObjetos().writeObject(new JogoDisponivel(false));
				else{
					if(!jogadorConectado){
						Timer t = new Timer();
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
		
		for(int i = 1; i < 101; i++){
			numerosSorteados.add(i);
		}
		
		Collections.shuffle(numerosSorteados);
		
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if(!numerosSorteados.isEmpty()){
					Servidor.this.writeToAll(new NumeroSorteado(numerosSorteados.get(0)));
					numerosSorteados.remove(0);
				}
				
			}
		}, 5000, 5000);
	}
}
