package com.database.DBOS;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class JogadaBO extends GenericoBO implements Serializable{

	private Integer idUsuario;
	private Date horario;
	
	public JogadaBO(Integer id, Integer idUsuario, Date horario){
		super(id);
		this.idUsuario = idUsuario;
		this.horario = horario;
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	public String horarioSQL(){
		Calendar c = Calendar.getInstance();
		c.setTime(this.getHorario());
		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DAY_OF_MONTH);
	}
	
}
