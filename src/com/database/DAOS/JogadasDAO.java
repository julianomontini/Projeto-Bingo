package com.database.DAOS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.database.DBOS.JogadaBO;

public class JogadasDAO implements CRUD<JogadaBO>{
	
	private Connection conn;
	
	public JogadasDAO(Connection conn){
		this.conn = conn;
	}

	@Override
	public void create(JogadaBO jogada) throws SQLException {
		String query = "INSERT INTO JOGADAS(ID_USUARIO, HORARIO) VALUES("+ jogada.getIdUsuario()+",'"+jogada.horarioSQL()+"')";
		conn.createStatement().executeUpdate(query);
	}

	@Override
	public void update(JogadaBO jogada) throws SQLException {
		String query = "UPDATE JOGADAS SET ID_USUARIO = '" + jogada.getIdUsuario()+ "' , HORARIO = '"+jogada.horarioSQL()+ "' WHERE ID = " + jogada.getId();
		conn.createStatement().executeUpdate(query);
	}

	@Override
	public void delete(JogadaBO jogada) throws SQLException {
		String query = "DELETE FROM JOGADAS WHERE ID = " + jogada.getId();
		conn.createStatement().executeUpdate(query);
	}

	@Override
	public JogadaBO select(Integer id) throws SQLException {
		JogadaBO retorno = null;
		String query = "SELECT ID, ID_USUARIO, HORARIO FROM JOGADAS WHERE ID = " + id;
		
		ResultSet resultado = conn.createStatement().executeQuery(query);
		
		if(resultado.first())
			retorno = new JogadaBO(resultado.getInt(1),resultado.getInt(2), resultado.getDate(3));
		
		return retorno;
	}

	@Override
	public List<JogadaBO> select() throws SQLException {
		List<JogadaBO> retorno = new ArrayList<JogadaBO>();
		String query = "SELECT ID, ID_USUARIO, HORARIO FROM JOGADAS";
		
		ResultSet resultado = conn.createStatement().executeQuery(query);
		
		if(resultado.first())
			retorno.add(new JogadaBO(resultado.getInt(1),resultado.getInt(2), resultado.getDate(3)));
		
		return retorno;
	}

	@Override
	public List<JogadaBO> select(String[] clausula, String[] valor) throws SQLException {
		List<JogadaBO> retorno = new ArrayList<JogadaBO>();
		String query = "SELECT ID, ID_USUARIO, HORARIO FROM JOGADAS";
		
		
		if(clausula.length > 0){
			query += " WHERE ";
			
			for(int i = 0; i < clausula.length; i++){
				if(i > 0)
					query += " AND ";
				
				query +=  clausula[i] + " = " + "'" + valor[i] + "'";
			}
			
		}
		
		
		ResultSet resultado = conn.createStatement().executeQuery(query);
		
		if(resultado.first())
			retorno.add(new JogadaBO(resultado.getInt(1),resultado.getInt(2), resultado.getDate(3)));
		
		return retorno;
	}

}
