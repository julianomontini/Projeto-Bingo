package com.common;

import java.io.InputStream;
import java.io.ObjectInputStream;

public class Listener extends Thread{
	final Receber acoes;
	final InputStream is;
	
	public Listener(InputStream is, Receber acoes){
		this.acoes = acoes;
		this.is = is;
		this.start();
	}

	@Override
	public void run() {
		System.out.println("Aguardando inputs");
		try {
			ObjectInputStream ois = new ObjectInputStream(this.is);
			while(true){
				acoes.receber(ois.readObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
