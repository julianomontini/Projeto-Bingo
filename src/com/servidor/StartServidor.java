package com.servidor;

public class StartServidor {

	public static void main(String[] args) {
		
		Servidor s1 = new Servidor(4200);
		s1.start();
	}

}