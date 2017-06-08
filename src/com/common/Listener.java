package com.common;

import java.io.InputStream;
import java.io.ObjectInputStream;

public class Listener extends Thread{
	private Receber acoes;
	private Receber temp;
	private InputStream is;
	
	/**
	 * Thread que fica esperando por novos objetos na conexao,
	 * quando recebe um novo objeto, escreve para a classe que implementa
	 * a interface receber
	 * @param InputStream
	 * @param acoes Classe que implementa a interface Receber
	 */
	public Listener(InputStream is, Receber acoes){
		this.acoes = acoes;
		this.is = is;
		this.start();
	}

	@Override
	public void run() {
		boolean parar = false;
		try {
			ObjectInputStream ois = new ObjectInputStream(this.is);
			while(!parar){
				acoes.receber(ois.readObject());
				efetuaTroca();
			}
		} catch (Exception e) {
			parar = true;
			System.out.println("Conexao perdida");
			e.printStackTrace();
		}
	}
	
	/**
	 * Troca a classe que executara as acoes
	 * @param r Nova classe que executara as acoes
	 */
	public void setReceber(Receber r){
		this.temp = r;
	}
	
	private void efetuaTroca(){
		if(this.temp != null){
			this.acoes = this.temp;
			this.temp = null;
		}
	}
}
