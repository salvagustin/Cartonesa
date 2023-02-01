package com.cartonesa.control.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUBMAQUINA")
public class Submaquina {

	//CAMPOS, SETTER Y GETTER DE LA TABLA SUBMAQUINA
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombres;
	
	@ManyToOne
    @JoinColumn(name="MAQUINA_ID")
	private Maquina maquina;

	public Submaquina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Submaquina(int id, String nombres, Maquina maquina) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.maquina = maquina;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	
	@Override
	public String toString() {
		return "Submaquina [id=" + id + ", nombres=" + nombres + ", maquina=" + maquina + "]";
	}
	
}
