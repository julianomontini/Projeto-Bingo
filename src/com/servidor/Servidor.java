package com.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.common.ConexaoClienteServidor;
import com.common.Receber;
import com.database.Conexao;
import com.database.DAOS.JogadasDAO;
import com.database.DAOS.UsuariosDAO;
import com.database.DBOS.JogadaBO;
import com.jogo.Bingo;
import com.jogo.objetosConexao.Mensagem;
import com.jogo.objetosConexao.NumeroSorteado;
import com.jogo.objetosConexao.TentativaBingo;

/**
 * Servidor que fica recebendo novas conexoes e que controla o fluxo do jogo
 * @author Juliano
 *
 */
public class Servidor extends Thread implements Receber{

	private int porta;
	private boolean jogoEmAndamento = false;
	private boolean jogadorConectado = false;
	private List<ConexaoClienteServidor> conexoesAbertas = new ArrayList<ConexaoClienteServidor>();
	private List<Integer> numerosSorteados = new ArrayList<Integer>();
	private Timer timerSorteios;
	
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
				ConexaoClienteServidor c = new ConexaoClienteServidor(conexao, this);
				
				if(jogoEmAndamento)
					c.getEscreverObjetos().writeObject(new Mensagem("Jogo indisponivel, volte mais tarde"));
				else{
					conexoesAbertas.add(c);
					c.getEscreverObjetos().writeObject(new Mensagem("Aguardando novos jogadores"));
					if(!jogadorConectado){
						jogadorConectado = true;
						Timer t = new Timer();
						t.schedule(new TimerTask() {
							
							@Override
							public void run() {
								Servidor.this.iniciaContagem();
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
		if(o instanceof TentativaBingo){
			tentativaBingo(o);
		}
	}
	
	private void tentativaBingo(Object o){
		System.out.println("Tentativa de bingo recebida");
		TentativaBingo tentativa = (TentativaBingo)o;
		
		System.out.print("Numeros do servidor: ");
		for(Integer i : this.numerosSorteados)
			System.out.print(i + " ");
		System.out.print("\nNumeros do cliente: ");
		for(Integer i : tentativa.getNumerosJogados())
			System.out.println(i + " ");
		System.out.println("Contem todos? " + this.numerosSorteados.containsAll(tentativa.getNumerosJogados()));
		System.out.println("Tamanho cliente: " + tentativa.getNumerosJogados().size());
		
		if(tentativa.getNumerosJogados().size() == 24 && this.numerosSorteados.containsAll(tentativa.getNumerosJogados())){
			try{
				timerSorteios.cancel();
			}catch(Exception e){
				e.printStackTrace();
			}
			this.writeToAll(new Mensagem(tentativa.getUsuario().getNome() + " ganhou"));
			try {
				JogadasDAO mgrJogadas = new JogadasDAO(Conexao.getConnection());
				mgrJogadas.create(new JogadaBO(null, tentativa.getUsuario().getId(), Calendar.getInstance().getTime()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					iniciaContagem();
				}
			}, 10000);
		}
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
		this.writeToAll(new Mensagem("O servidor iniciou a partida"));
		this.jogoEmAndamento = true;
		this.numerosSorteados.clear();
		
		List<Integer> numerosSorteados = new ArrayList<Integer>();
		
		for(int i = 1; i <= 75; i++){
			numerosSorteados.add(i);
		}
		
		Collections.shuffle(numerosSorteados);
		this.writeToAll(new Mensagem("O servidor comecara a sortear numeros"));
		timerSorteios = new Timer();
		timerSorteios.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if(!numerosSorteados.isEmpty()){
					Servidor.this.writeToAll(new NumeroSorteado(numerosSorteados.get(0)));
					Servidor.this.numerosSorteados.add(new Integer(numerosSorteados.get(0)));
					System.out.print("\nNumeros Já sorteados: ");
					for(Integer numero : Servidor.this.numerosSorteados)
						System.out.print(numero + " ");
					numerosSorteados.remove(0);
				}else{
					Servidor.this.writeToAll(new Mensagem("Fim do jogo - Nao ha ganhador"));
					Servidor.this.jogoEmAndamento = false;
					new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							iniciaContagem();
							
						}
					}, 5000);;
					this.cancel();
				}
				
			}
		}, 5000, 5000);
	}

	@Override
	public void receber(Object o) {
		mapeiaAcoes(o);
	}
	
	public void iniciaContagem(){
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			int contagem = 5;
			boolean sorteio = false;
			@Override
			public void run() {
				if(contagem >=0){
					if(!sorteio){
						sorteio = true;
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
					}
					Servidor.this.writeToAll(new Mensagem("O jogo comecara em " + contagem + " segundos "));
					contagem--;
				}else{
					Servidor.this.iniciaPartida();
					this.cancel();
				}
				
			}
		}, 0, 1000);
	}
}
