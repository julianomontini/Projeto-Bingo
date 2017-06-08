package com.database.DBOS;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class JogadaBO extends GenericoBO implements Serializable{

	private Integer idUsuario;
	private Date horario;
	
	/**
	 * Representa uma linha da tabela Jogada
	 * @param id
	 * @param idUsuario
	 * @param horario
	 */
	public JogadaBO(Integer id, Integer idUsuario, Date horario){
		super(id);
		this.idUsuario = idUsuario;
		this.horario = horario;
	}

	/**
	 * getter idUsuario
	 * @return
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}
	/**
	 * setter idUsuario
	 * @param idUsuario
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * getter Horario
	 * @return
	 */
	public Date getHorario() {
		return horario;
	}
	/**
	 * setter Horario
	 * @param horario
	 */
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	/**
	 * 
	 * @return string de horario que pode ser escrita no banco
	 */
	public String horarioSQL(){
		Calendar c = Calendar.getInstance();
		c.setTime(this.getHorario());
		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DAY_OF_MONTH);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		JogadaBO other = (JogadaBO) obj;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JogadaBO [idUsuario=" + idUsuario + ", horario=" + horario + "]";
	}
	
	
	
}
