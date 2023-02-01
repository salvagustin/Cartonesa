package com.cartonesa.control.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AREA")
public class Area{
	
	//CAMPOS, SETTER Y GETTER DE LA TABLA AREA
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String areanombre;
	
	
	public Area() {
		// TODO Auto-generated constructor stub
	}

	public Area(int id, String areanombre) {
		this.id = id;
		this.areanombre = areanombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreanombre() {
		return areanombre;
	}

	public void setAreanombre(String areanombre) {
		this.areanombre = areanombre;
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", areanombre=" + areanombre + "]";
	}
	
}
