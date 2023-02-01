package com.cartonesa.control.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAUSA")
public class Causa {
	
	//CAMPOS, SETTER Y GETTER DE LA TABLA CAUSAS Y FALLAS
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcausa;
	private String falla;
	private String solucion;
	
	
	
	public Causa() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Causa(int idcausa, String falla, String solucion) {
		super();
		this.idcausa = idcausa;
		this.falla = falla;
		this.solucion = solucion;
	}

	public int getIdcausa() {
		return idcausa;
	}

	public void setIdcausa(int idcausa) {
		this.idcausa = idcausa;
	}

	public String getFalla() {
		return falla;
	}

	public void setFalla(String falla) {
		this.falla = falla;
	}


	public String getSolucion() {
		return solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
	

}
