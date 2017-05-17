package com.cliente;

import java.io.IOException;
import java.net.Socket;

import com.common.ConexaoClienteServidor;


public class StartCliente {

	public static void main(String[] args) {
		
		try {
			ConexaoClienteServidor c1 = new ConexaoClienteServidor(new Socket("localhost", 4200)){

				@Override
				public void receber(Object o) {
					if(o instanceof Integer[]){
						Integer[] recebido = (Integer[])o;
						for(Integer i : recebido)
							System.out.println(i);
					}
				}
			};
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
