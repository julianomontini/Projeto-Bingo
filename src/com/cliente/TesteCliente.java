package com.cliente;

import java.io.IOException;
import java.net.Socket;

import com.common.ConexaoClienteServidor;

public class TesteCliente {

	public static void main(String[] args) {
		
		try {
			ConexaoClienteServidor c1 = new ConexaoClienteServidor(new Socket("localhost", 4200)){

				@Override
				public void receber(Object o) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void escrever() {
					while(true){
						try {
							this.getEscreverObjetos().writeObject("Cliente escrevendo");
							this.getEscreverObjetos().flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}
				
			};
			
			c1.escrever();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
