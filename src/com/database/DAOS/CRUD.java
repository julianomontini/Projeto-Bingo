package com.database.DAOS;

import java.sql.SQLException;
import java.util.List;

import com.database.DBOS.GenericoBO;

public interface CRUD<T extends GenericoBO> {

	public void create(T objeto) throws SQLException;
	public void update(T objeto) throws SQLException;
	public void delete(T objeto) throws SQLException;
	public T select(Integer id) throws SQLException;
	public List<T> select() throws SQLException;
	public List<T> select(String[] clausulas, String[] valores) throws SQLException, Exception;
	
}
