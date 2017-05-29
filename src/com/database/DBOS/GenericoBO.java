package com.database.DBOS;

import java.io.Serializable;

public class GenericoBO implements Serializable{
	private Integer id;
	
	public GenericoBO(Integer id){
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString(){
		String retorno = "Id: ";
		
		retorno += (this.id != null ? id.toString() : "null");
		
		return retorno;
	}
	
	@Override
	public int hashCode(){
		int retorno = 777;	
		return (this.id != null ? retorno*31 + this.id.hashCode() : retorno);
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null)
			return false;
		if(o == this)
			return true;
		if(!(o.getClass() == this.getClass()))
			return false;
		
		GenericoBO comparado = (GenericoBO)o;
		
		if(!comparado.id.equals(this.id))
			return false;
		
		return true;
	}
	
}
