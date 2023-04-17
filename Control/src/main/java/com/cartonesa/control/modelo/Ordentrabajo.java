package com.cartonesa.control.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import java.math.BigDecimal;
import java.sql.Timestamp;



@Entity
@Table(name = "ORDENTRABAJO")
@DynamicUpdate
@SequenceGenerator(
	    name="OTSecuenc",
	    sequenceName = "Secuenc_ot",
	    initialValue = 20000000, 
	    allocationSize = 1
	)

public class Ordentrabajo {

	//CAMPOS, SETTER Y GETTER DE LA TABLA ORDEN DE TRABAJO
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "OTSecuenc")
	private int idordentrab;
	
	private String descripcion;
	
	private String falla;
	
	private String estadoot;
		
	private BigDecimal horastrab;
	
	@Column(updatable = false, nullable = false)
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Timestamp fecharegistro;
		
	private String tiempos;
				
	private String tipoorden;
		
	@ManyToOne
    @JoinColumn(name="IDMAQ")
    public Maquina maquina;	
	
	@ManyToOne
    @JoinColumn(name="IDSUBMAQ")
    public Submaquina submaquina;
	
	@ManyToOne
    @JoinColumn(name="IDTECN")
    public Tecnico tecnico;
	
	@ManyToOne
    @JoinColumn(name="IDCAUSA")
    public Causa causa;
	
	@ManyToOne
    @JoinColumn(name="idordencomp")
    public Ordencompra ordencompra;

	
	public Ordentrabajo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Ordentrabajo(int idordentrab, String descripcion, String estadoot,
			BigDecimal horastrab, Timestamp fecharegistro, String tiempos, String tipoorden, 
			Maquina maquina, Submaquina submaquina, Tecnico tecnico, Causa causa,Ordencompra ordencompra, 
			String falla) {
		super();
		this.idordentrab = idordentrab;
		this.falla=falla;
		this.descripcion = descripcion;
		this.estadoot = estadoot;
		this.horastrab = horastrab;
		this.fecharegistro = fecharegistro;
		this.tiempos = tiempos;
		this.tipoorden = tipoorden;
		this.ordencompra=ordencompra;
		this.maquina = maquina;
		this.submaquina = submaquina;
		this.tecnico = tecnico;
		this.causa = causa;
	}


	public int getIdordentrab() {
		return idordentrab;
	}


	public void setIdordentrab(int idordentrab) {
		this.idordentrab = idordentrab;
	}


	
	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getEstadoot() {
		return estadoot;
	}


	public void setEstadoot(String estadoot) {
		this.estadoot = estadoot;
	}



	public BigDecimal getHorastrab() {
		return horastrab;
	}


	public void setHorastrab(BigDecimal horastrab) {
		this.horastrab = horastrab;
	}


	public Timestamp getFecharegistro() {
		return fecharegistro;
	}


	public void setFecharegistro(Timestamp fecharegistro) {
		this.fecharegistro = fecharegistro;
	}


	public String getTiempos() {
		return tiempos;
	}


	public void setTiempos(String tiempos) {
		this.tiempos = tiempos;
	}


	public String getTipoorden() {
		return tipoorden;
	}


	public void setTipoorden(String tipoorden) {
		this.tipoorden = tipoorden;
	}


	public Maquina getMaquina() {
		return maquina;
	}


	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}


	public Submaquina getSubmaquina() {
		return submaquina;
	}


	public void setSubmaquina(Submaquina submaquina) {
		this.submaquina = submaquina;
	}


	public Tecnico getTecnico() {
		return tecnico;
	}


	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}


	public Causa getCausa() {
		return causa;
	}


	public void setCausa(Causa causa) {
		this.causa = causa;
	}


	public String getFalla() {
		return falla;
	}


	public void setFalla(String falla) {
		this.falla = falla;
	}


	public Ordencompra getOrdencompra() {
		return ordencompra;
	}


	public void setOrdencompra(Ordencompra ordencompra) {
		this.ordencompra = ordencompra;
	}

	public Ordentrabajo(String descripcion, String falla, String estadoot,  BigDecimal horastrab,
			Timestamp fecharegistro, String tiempos, String tipoorden,  Maquina maquina,
			Submaquina submaquina, Tecnico tecnico, Causa causa, Ordencompra ordencompra) {
		super();
		this.descripcion = descripcion;
		this.falla = falla;
		this.estadoot = estadoot;
		this.horastrab = horastrab;
		this.fecharegistro = fecharegistro;
		this.tiempos = tiempos;
		this.tipoorden = tipoorden;
		this.maquina = maquina;
		this.submaquina = submaquina;
		this.tecnico = tecnico;
		this.causa = causa;
		this.ordencompra = ordencompra;
		
	}
		
	
	
	
	

}
