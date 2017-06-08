package com.servidor;

/**
 * Inicializa o servidor na porta 4200
 * @author Juliano
 *
 */
public class StartServidor {

	public static void main(String[] args) {
		
		Servidor s1 = new Servidor(4200);
		s1.start();
	}

}
