package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

	private static Connection conexao = null;
	
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
