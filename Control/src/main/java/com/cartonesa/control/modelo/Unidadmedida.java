package com.cartonesa.control.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UNIDADMEDIDA")
public class Unidadmedida {

	//CAMPOS, SETTER Y GETTER DE LA TABLA UNIDADES DE MEDIDA
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idunidad;
	private String unidad;
	
	
	public Unidadmedida() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Unidadmedida(int idunidad, String unidad) {
		super();
		this.idunidad = idunidad;
		this.unidad = unidad;
	}
	
	public int getIdunidad() {
		return idunidad;
	}
	
	public void setIdunidad(int idunidad) {
		this.idunidad = idunidad;
	}
	
	public String getUnidad() {
		return unidad;
	}
	
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	
	
}
