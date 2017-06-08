package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe para se conectar com o banco de dados
 * @author Juliano
 *
 */
public class Conexao {
	
	private Conexao(){}

	private static Connection conexao = null;
	
	/**
	 * Retorna uma nova conexao com o banco de dados, caso uma 
	 * conexao ja tenha sido aberta,retorna ela mesma, sendo assim
	 * so existe uma conexao ativa por vez ( singleton pattern )
	 * @return Conexao com o banco de dados.
	 * 
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		
		if(conexao != null)
			return conexao;
		
		final String usuario = "juliano";
		final String senha = "juliano";
		final String ip = "localhost";
		final String porta = "3306";
		final String database = "mysql";
		final String schema = "trabalhofinal";
		
		final Properties props = new Properties();
		
		props.put("user", usuario);
		props.put("password", senha);
		
		conexao = DriverManager.getConnection("jdbc:"+database+"://" +ip+":" + porta + "/"+schema, props);
		return conexao;
		
	}
	
}
