package com.cartonesa.control.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "MAQUINA")
public class Maquina {

	//CAMPOS, SETTER Y GETTER DE LA TABLA MAQUINA
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String maquinanombre;
	
	@ManyToOne
    @JoinColumn(name="AREA_ID")
    public Area area;

	public Maquina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Maquina(int id, String maquinanombre, Area area) {
		super();
		this.id = id;
		this.maquinanombre = maquinanombre;
		this.area = area;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id= id;
	}

	public String getMaquinanombre() {
		return maquinanombre;
	}

	public void setMaquinanombre(String maquinanombre) {
		this.maquinanombre = maquinanombre;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Maquina [id=" + id + ", maquinanombre=" + maquinanombre + ", area=" + area + "]";
	}
	
	
}
