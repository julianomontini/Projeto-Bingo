package com.jogo;

import java.util.ArrayList;
import java.util.Collections;

import com.jogo.objetosConexao.Cartela;

public class Bingo {

	public static Cartela geraCartela(){
		
		ArrayList<Integer> numerosSorteados = new ArrayList<Integer>();
		Integer[] retorno = new Integer[24];
		
		while(numerosSorteados.size() <= 24){
			int numero = ((int)(Math.random()*60))+1;
			if(!numerosSorteados.contains(numero)){
				numerosSorteados.add(numero);
			}
		}
		
		Collections.sort(numerosSorteados);
		
		return new Cartela(numerosSorteados.toArray(retorno));
	}
	
}
