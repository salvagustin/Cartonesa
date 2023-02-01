package com.cartonesa.control.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TECNICO")
public class Tecnico {

	//CAMPOS, SETTER Y GETTER DE LA TABLA TECNICOS
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idtecn;
	private String nombre;
	private String codigotrab;
	
	
	@ManyToOne
    @JoinColumn(name="IDTIPTRAB")
    public Tipotrabajo tipotrabajo;

	public Tecnico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tecnico(int idtecn, String nombre, String codigotrab, Tipotrabajo tipotrabajo) {
		super();
		this.idtecn = idtecn;
		this.nombre = nombre;
		this.codigotrab = codigotrab;
		this.tipotrabajo = tipotrabajo;
	}

	public int getIdtecn() {
		return idtecn;
	}

	public void setIdtecn(int idtecn) {
		this.idtecn = idtecn;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigotrab() {
		return codigotrab;
	}

	public void setCodigotrab(String codigotrab) {
		this.codigotrab = codigotrab;
	}

	public Tipotrabajo getTipotrabajo() {
		return tipotrabajo;
	}

	public void setTipotrabajo(Tipotrabajo tipotrabajo) {
		this.tipotrabajo = tipotrabajo;
	}
	
	
	
	
}


