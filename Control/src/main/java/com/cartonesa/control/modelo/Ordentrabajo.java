package com.cartonesa.control.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ORDENTRABAJO")
public class Ordentrabajo {

	//CAMPOS, SETTER Y GETTER DE LA TABLA ORDEN DE TRABAJO
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idordentrab;
	
	private String descripcion;
	
	private String estadoot;

	private String estadooc;
		
	private BigDecimal horastrab;
		
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecharealizada;
		
	private String tiempos;
				
	private String tipoorden;
		
	@ManyToOne
    @JoinColumn(name="IDAREA")
    public Area area;
		
	@ManyToOne
    @JoinColumn(name="IDTIPTRAB")
    public Tipotrabajo tipotrabajo;	
	
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

	
	public Ordentrabajo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Ordentrabajo(int idordentrab, String descripcion, String estadoot, String estadooc,
			BigDecimal horastrab, Date fecharealizada, String tiempos, String tipoorden, Area area,
			Tipotrabajo tipotrabajo, Maquina maquina, Submaquina submaquina, Tecnico tecnico, Causa causa) {
		super();
		this.idordentrab = idordentrab;
		
		this.descripcion = descripcion;
		this.estadoot = estadoot;
		this.estadooc = estadooc;
		this.horastrab = horastrab;
		this.fecharealizada = fecharealizada;
		this.tiempos = tiempos;
		this.tipoorden = tipoorden;
		this.area = area;
		this.tipotrabajo = tipotrabajo;
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


	public String getEstadooc() {
		return estadooc;
	}


	public void setEstadooc(String estadooc) {
		this.estadooc = estadooc;
	}


	public BigDecimal getHorastrab() {
		return horastrab;
	}


	public void setHorastrab(BigDecimal horastrab) {
		this.horastrab = horastrab;
	}


	public Date getFecharealizada() {
		return fecharealizada;
	}


	public void setFecharealizada(Date fecharealizada) {
		this.fecharealizada = fecharealizada;
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


	public Area getArea() {
		return area;
	}


	public void setArea(Area area) {
		this.area = area;
	}


	public Tipotrabajo getTipotrabajo() {
		return tipotrabajo;
	}


	public void setTipotrabajo(Tipotrabajo tipotrabajo) {
		this.tipotrabajo = tipotrabajo;
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
		
	
	

}
