package com.database.DAOS;

import java.sql.SQLException;
import java.util.List;

import com.database.DBOS.GenericoBO;

/**
 * Define operacoes basicas de um banco
 * @author Juliano
 *
 * @param <T> Classes que extendam de um BO
 */
public interface CRUD<T extends GenericoBO> {

	/**
	 * Cria novo objeto no banco
	 * @param objeto objeto a ser criado
	 * @throws SQLException
	 */
	public void create(T objeto) throws SQLException;
	/**
	 * Atualiza objeto no banco
	 * @param objeto objeto a ser atualizado
	 * @throws SQLException
	 */
	public void update(T objeto) throws SQLException;
	/**
	 * Apaga objeto do banco
	 * @param objeto objeto a ser apagado
	 * @throws SQLException
	 */
	public void delete(T objeto) throws SQLException;
	/**
	 * Seleciona uma unica entrada do banco de acordo com o id
	 * @param id identificacao do objeto
	 * @return instancia deu um objeto, ou null caso nao exista
	 * @throws SQLException
	 */
	public T select(Integer id) throws SQLException;
	/**
	 * Busca todas as entradas no banco daquele objeto
	 * @return todas as entradas da tabela
	 * @throws SQLException
	 */
	public List<T> select() throws SQLException;
	/**
	 * Metodo para entrar varios where e valores, construindo assim queries diferentes
	 * @param clausulas condicoes where
	 * @param valores valores do where 
	 * @return resultados da query
	 * @throws SQLException
	 * @throws Exception quantidade de clausulas e valores sejam invalidos
	 */
	public List<T> select(String[] clausulas, String[] valores) throws SQLException, Exception;
	
}
