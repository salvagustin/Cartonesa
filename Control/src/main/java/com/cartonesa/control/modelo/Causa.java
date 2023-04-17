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
	private String descripcioncausa;
	
	
	public Causa() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Causa(int idcausa, String descripcioncausa) {
		super();
		this.idcausa = idcausa;
		this.descripcioncausa=descripcioncausa;
	}

	public int getIdcausa() {
		return idcausa;
	}

	public void setIdcausa(int idcausa) {
		this.idcausa = idcausa;
	}



	public String getDescripcioncausa() {
		return descripcioncausa;
	}


	public void setDescripcioncausa(String descripcioncausa) {
		this.descripcioncausa = descripcioncausa;
	}


	//CONSTRUCTOR PARA PRUEBA JUNIT
	public Causa(String descripcioncausa) {
		super();
		this.descripcioncausa = descripcioncausa;
	}
	
	

}
