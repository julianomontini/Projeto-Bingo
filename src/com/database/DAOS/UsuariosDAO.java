package com.database.DAOS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.database.DBOS.UsuarioBO;

public class UsuariosDAO implements CRUD<UsuarioBO>{

	private final Connection conn;
	
	public UsuariosDAO(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public void create(UsuarioBO usr) throws SQLException{
		String query = "INSERT INTO USUARIO(NOME, EMAIL, SENHA) VALUES('"+ usr.getNome()+"','"+usr.getEmail()+"','"+usr.getSenha()+"')";
		conn.createStatement().executeUpdate(query);
	}
	@Override
	public void update(UsuarioBO usr) throws SQLException{
		String query = "UPDATE USUARIO SET NOME = '" + usr.getNome()+ "' , EMAIL = '"+usr.getEmail()+ "' , SENHA = '" + usr.getSenha() + "' WHERE ID = " + usr.getId();
		conn.createStatement().executeUpdate(query);
	}
	
	@Override
	public void delete(UsuarioBO usr) throws SQLException{
		String query = "DELETE FROM USUARIO WHERE ID = " + usr.getId();
		conn.createStatement().executeUpdate(query);
	}

	@Override
	public UsuarioBO select(Integer id) throws SQLException {
		UsuarioBO retorno = null;
		String query = "SELECT ID, NOME, EMAIL, SENHA FROM USUARIO WHERE ID = " + id;
		
		ResultSet resultado = conn.createStatement().executeQuery(query);
		
		if(resultado.first())
			retorno = new UsuarioBO(resultado.getInt(1),resultado.getString(2), resultado.getString(3), resultado.getString(4));
		
		return retorno;
	}

	@Override
	public List<UsuarioBO> select() throws SQLException {
		String query = "SELECT ID, NOME, EMAIL, SENHA FROM USUARIO";
		List<UsuarioBO> usuarios = new ArrayList<UsuarioBO>();
		
		ResultSet resultado = conn.createStatement().executeQuery(query);
		
		while(resultado.next())
			usuarios.add(new UsuarioBO(resultado.getInt(1), resultado.getString(2), resultado.getString(3), resultado.getString(4)));
		
		return usuarios;
	}
	
	@Override
	public List<UsuarioBO> select(String[] clausula, String[] valor) throws SQLException, Exception {
		
		if(clausula.length != valor.length)
			throw new Exception("Cada clausula precisa ter um valor");
		
		String query = "SELECT ID, NOME, EMAIL, SENHA FROM USUARIO";
		
		if(clausula.length > 0){
			query += " WHERE ";
			
			for(int i = 0; i < clausula.length; i++){
				if(i > 0)
					query += " AND ";
				
				query +=  clausula[i] + " = " + "'" + valor[i] + "'";
			}
			
		}
		
		List<UsuarioBO> usuarios = new ArrayList<UsuarioBO>();
		
		ResultSet resultado = conn.createStatement().executeQuery(query);
		
		while(resultado.next())
			usuarios.add(new UsuarioBO(resultado.getInt(1), resultado.getString(2), resultado.getString(3), resultado.getString(4)));
		
		return usuarios;
	}
	
}
