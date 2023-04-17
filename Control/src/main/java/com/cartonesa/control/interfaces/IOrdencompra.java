package com.cartonesa.control.interfaces;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordencompra;
import com.cartonesa.control.modelo.Unidadmedida;

@Repository
public interface IOrdencompra extends JpaRepository<Ordencompra, Integer>{
	
	//LISTAR LOS REGISTROS EN FORMA DESCENDENTE.
	public List<Ordencompra> findByOrderByIdordencompDesc( );
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR OC POR NUM SOLICITUD DE COMPRA
	public List<Ordencompra> findByNumsolcompra(int numsolcompra);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE COMPRA POR COTIZACION
	public List<Ordencompra> findByCotizacionContaining(String cotizacion);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE COMPRA POR CANTIDAD DE PRODUCTO
	public List<Ordencompra> findByCantprod(BigDecimal cantprod);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE COMPRA POR PRODUCTO
	public List<Ordencompra> findByProductoContaining(String producto);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE COMPRA POR MAQUINA
	public List<Ordencompra> findByMaquina(Maquina maquina);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE COMPRA POR UNIDAD DE MEDIDA
	public List<Ordencompra> findByUnidadmedida(Unidadmedida unidadmedida);
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE COMPRA POR ESTADO OC
	public List<Ordencompra> findByEstadoocContaining(String estadooc);
	
	//REPOSITORIO QUE CUENTA LOS REGISTROS
	long countByEstadooc(String estado);
		
	//REPOSITORIO QUE CONSULTA A LA BASE SI EXISTE UN IDUNIDAD EN LAS ORDENES DE COMPRA PARA ELIMINAR UNA UNIDAD DE MDEDIDA
	@Query(value = " SELECT * FROM Ordencompra WHERE idunidad=  ?1 ", nativeQuery = true)
	public List<Ordencompra>buscarporidunidad(int id);
	
	//REPOSITORIO QUE CONSULTA A LA BASE SI EXISTE UN IDMAQUINA EN LAS ORDENES DE COMPRA PARA ELIMINAR UNA MAQUINA
	@Query(value = " SELECT * FROM Ordencompra WHERE idmaq=  ?1 ", nativeQuery = true)
	public List<Ordencompra>buscarporidmaq(int id);
}
