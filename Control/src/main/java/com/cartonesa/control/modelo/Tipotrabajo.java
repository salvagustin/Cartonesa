package com.cartonesa.control.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPOTRABAJO")
public class Tipotrabajo {
	
	//CAMPOS, SETTER Y GETTER DE LA TABLA TIPO DE TRABAJO
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idtiptrab;
	private String tipotrab;
	
	
	public Tipotrabajo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Tipotrabajo(int idtiptrab, String tipotrab) {
		super();
		this.idtiptrab = idtiptrab;
		this.tipotrab = tipotrab;
	}


	public int getIdtiptrab() {
		return idtiptrab;
	}


	public void setIdtiptrab(int idtiptrab) {
		this.idtiptrab = idtiptrab;
	}


	public String getTipotrab() {
		return tipotrab;
	}


	public void setTipotrab(String tipotrab) {
		this.tipotrab = tipotrab;
	}
	

	//CONSTRUCTOR PARA PRUEBA JUNIT
	public Tipotrabajo(String tipotrab) {
		super();
		this.tipotrab = tipotrab;
	}
	
}
