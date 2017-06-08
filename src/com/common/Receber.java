package com.common;
/**
 * 
 * @author Juliano
 *	Interface utilizada em conjunto com o listener para indentificar que uma 
 *classe é capaz de receber um objeto e tomar uma acao com ele
 */
public interface Receber {
	/**
	 * Executa uma acao desejada com o objeto recebido na conexao
	 * @param o Objeto recebido na conexao
	 */
	public void receber(Object o);
}
