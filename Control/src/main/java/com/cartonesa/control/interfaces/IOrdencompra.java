package com.cartonesa.control.interfaces;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordencompra;
import com.cartonesa.control.modelo.Unidadmedida;

@Repository
public interface IOrdencompra extends JpaRepository<Ordencompra, Integer>{
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE COMPRA POR SOLICITUD DE COMPRA
	public List<Ordencompra> findByNumsolcompra(int numsolcompra);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE COMPRA POR COTIZACION
	public List<Ordencompra> findByCotizacion(String cotizacion);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE COMPRA POR CANTIDAD DE PRODUCTO
	public List<Ordencompra> findByCantprod(BigDecimal cantprod);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE COMPRA POR PRODUCTO
	public List<Ordencompra> findByProducto(String producto);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE COMPRA POR MAQUINA
	public List<Ordencompra> findByMaquina(Maquina maquina);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE COMPRA POR UNIDAD DE MEDIDA
	public List<Ordencompra> findByUnidadmedida(Unidadmedida unidadmedida);
	
}
