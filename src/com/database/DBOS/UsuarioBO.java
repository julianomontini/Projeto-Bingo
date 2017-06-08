package com.database.DBOS;

import java.io.Serializable;

public class UsuarioBO extends GenericoBO implements Serializable{

	private String nome;
	private String email;
	private String senha;
	
	/**
	 * Representa uma linha da tabela usuario
	 * @param id
	 * @param nome
	 * @param email
	 * @param senha
	 */
	public UsuarioBO(Integer id, String nome, String email, String senha){
		super(id);
		
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	/**
	 * getter nome
	 * @return
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * setter nome
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * getter email
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * setter email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * getter senha
	 * @return
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * setter senha
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
