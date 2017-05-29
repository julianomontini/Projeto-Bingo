package com.database.DBOS;

import java.io.Serializable;

public class UsuarioBO extends GenericoBO implements Serializable{

	private String nome;
	private String email;
	private String senha;
	
	public UsuarioBO(Integer id, String nome, String email, String senha){
		super(id);
		
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
