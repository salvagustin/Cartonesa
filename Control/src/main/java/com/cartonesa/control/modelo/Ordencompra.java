package com.cartonesa.control.modelo;

import java.util.Date;

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

@Entity
@Table(name = "ORDENCOMPRA")
public class Ordencompra {

	//CAMPOS, SETTER Y GETTER DE LA TABLA ORDEN DE COMPRA
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idordencomp;
	private int numsolcompra;
	private String cotizacion;
	private String estadooc;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechareq;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaenvoc;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaingreso;
	
	private BigDecimal cantprod;
	private String producto;
	
	@ManyToOne
    @JoinColumn(name="IDMAQ")
    public Maquina maquina;
	
	@ManyToOne
    @JoinColumn(name="IDUNIDAD")
    public Unidadmedida unidadmedida;

	
	public Ordencompra() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ordencompra(int idordencomp, int numsolcompra, String cotizacion, Date fechareq, Date fechaenvoc,
			Date fechaingreso, BigDecimal cantprod, String producto, Maquina maquina,
			Unidadmedida unidadmedida,String estadooc) {
		super();
		this.idordencomp = idordencomp;
		this.numsolcompra = numsolcompra;
		this.cotizacion = cotizacion;
		this.fechareq = fechareq;
		this.fechaenvoc = fechaenvoc;
		this.fechaingreso = fechaingreso;
		this.cantprod = cantprod;
		this.producto = producto;
		this.maquina = maquina;
		this.unidadmedida = unidadmedida;
		this.estadooc = estadooc;
	}

	public int getIdordencomp() {
		return idordencomp;
	}

	public void setIdordencomp(int idordencomp) {
		this.idordencomp = idordencomp;
	}

	public int getNumsolcompra() {
		return numsolcompra;
	}

	public void setNumsolcompra(int numsolcompra) {
		this.numsolcompra = numsolcompra;
	}

	public String getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	public Date getFechareq() {
		return fechareq;
	}

	public void setFechareq(Date fechareq) {
		this.fechareq = fechareq;
	}

	public Date getFechaenvoc() {
		return fechaenvoc;
	}

	public void setFechaenvoc(Date fechaenvoc) {
		this.fechaenvoc = fechaenvoc;
	}

	public Date getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public BigDecimal getCantprod() {
		return cantprod;
	}

	public void setCantprod(BigDecimal cantprod) {
		this.cantprod = cantprod;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Unidadmedida getUnidadmedida() {
		return unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public String getEstadooc() {
		return estadooc;
	}

	public void setEstadooc(String estadooc) {
		this.estadooc = estadooc;
	}

	public Ordencompra(int numsolcompra, String cotizacion, String estadooc, Date fechareq, Date fechaenvoc,
			Date fechaingreso, BigDecimal cantprod, String producto, Maquina maquina, Unidadmedida unidadmedida) {
		super();
		this.numsolcompra = numsolcompra;
		this.cotizacion = cotizacion;
		this.estadooc = estadooc;
		this.fechareq = fechareq;
		this.fechaenvoc = fechaenvoc;
		this.fechaingreso = fechaingreso;
		this.cantprod = cantprod;
		this.producto = producto;
		this.maquina = maquina;
		this.unidadmedida = unidadmedida;
	}
	
	
	
}
