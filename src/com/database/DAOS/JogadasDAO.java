package com.database.DAOS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.common.VitoriasJogador;
import com.database.DBOS.JogadaBO;

/**
 * Efetua operacoes no banco relacionadas a classe JogadasBO
 * @author Juliano
 *
 */
public class JogadasDAO implements CRUD<JogadaBO>{
	
	private Connection conn;
	
	public JogadasDAO(Connection conn){
		this.conn = conn;
	}

	@Override
	public void create(JogadaBO jogada) throws SQLException {
		String query = "INSERT INTO JOGADA(ID_USUARIO, HORARIO) VALUES("+ jogada.getIdUsuario()+",'"+jogada.horarioSQL()+"')";
		conn.createStatement().executeUpdate(query);
	}

	@Override
	public void update(JogadaBO jogada) throws SQLException {
		String query = "UPDATE JOGADA SET ID_USUARIO = '" + jogada.getIdUsuario()+ "' , HORARIO = '"+jogada.horarioSQL()+ "' WHERE ID = " + jogada.getId();
		conn.createStatement().executeUpdate(query);
	}

	@Override
	public void delete(JogadaBO jogada) throws SQLException {
		String query = "DELETE FROM JOGADA WHERE ID = " + jogada.getId();
		conn.createStatement().executeUpdate(query);
	}

	@Override
	public JogadaBO select(Integer id) throws SQLException {
		JogadaBO retorno = null;
		String query = "SELECT ID, ID_USUARIO, HORARIO FROM JOGADA WHERE ID = " + id;
		
		ResultSet resultado = conn.createStatement().executeQuery(query);
		
		if(resultado.first())
			retorno = new JogadaBO(resultado.getInt(1),resultado.getInt(2), resultado.getDate(3));
		
		return retorno;
	}

	@Override
	public List<JogadaBO> select() throws SQLException {
		List<JogadaBO> retorno = new ArrayList<JogadaBO>();
		String query = "SELECT ID, ID_USUARIO, HORARIO FROM JOGADA";
		
		ResultSet resultado = conn.createStatement().executeQuery(query);
		
		if(resultado.first())
			retorno.add(new JogadaBO(resultado.getInt(1),resultado.getInt(2), resultado.getDate(3)));
		
		return retorno;
	}

	@Override
	public List<JogadaBO> select(String[] clausula, String[] valor) throws SQLException {
		List<JogadaBO> retorno = new ArrayList<JogadaBO>();
		String query = "SELECT ID, ID_USUARIO, HORARIO FROM JOGADA";
		
		
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
	
	public List<VitoriasJogador> getVitoriasJogador() throws SQLException{
		List<VitoriasJogador> vitorias = new ArrayList<VitoriasJogador>();
		
		ResultSet resultado = conn.createStatement().executeQuery("SELECT * FROM VITORIAS_USUARIO");
		
		while(resultado.next())
			vitorias.add(new VitoriasJogador(resultado.getInt(1), resultado.getString(2)));
		
		Collections.sort(vitorias, new Comparator<VitoriasJogador>(){
			@Override
			public int compare(VitoriasJogador v1, VitoriasJogador v2) {
				if(v1.getVitorias() > v2.getVitorias())
					return -1;
				if(v1.getVitorias() < v2.getVitorias())
					return 1;
				
				return 0;
			}});
		
		return vitorias;
	}

}
