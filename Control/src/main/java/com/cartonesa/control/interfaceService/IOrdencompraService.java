package com.cartonesa.control.interfaceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordencompra;
import com.cartonesa.control.modelo.Unidadmedida;

public interface IOrdencompraService {
	
	//METODOS CRUD DE LAS ORDEN DE COMPRA
	public List<Ordencompra>listar();
	public Optional<Ordencompra>listarId(int id);
	public int save(Ordencompra c);
	public void delete(int id);
	
	//METODOS PARA BUSCAR REGISTROS POR SOLICITUD DE COMPRA
	public List<Ordencompra> findByNumsolcompra(int numsolcompra);
	
	//METODOS PARA BUSCAR REGISTROS POR COTIZACION
	public List<Ordencompra> findByCotizacion(String cotizacion);
	
	//METODOS PARA BUSCAR REGISTROS POR CANTIDAD DE PRODUCTO
	public List<Ordencompra> findByCantprod(BigDecimal cantprod);
	
	//METODOS PARA BUSCAR REGISTROS POR PRODUCTO
	public List<Ordencompra> findByProducto(String producto);
	
	//METODOS PARA BUSCAR REGISTROS POR MAQUINA
	public List<Ordencompra> findByMaquina(Maquina maquina);
	
	//METODOS PARA BUSCAR REGISTROS POR UNIDAD DE MEDIDA
	public List<Ordencompra> findByUnidadmedida(Unidadmedida unidadmedida);
	
	//METODOS PARA BUSCAR REGISTROS POR ESTADO OC
	public List<Ordencompra> findByEstadooc(String estadooc);
	
	//METODOS PARA CONTAR TODOS LOS REGISTROS
	public long getNumberOfOrdencompra();
		
	//METODOS PARA CONTAR REGISTROS POR ESTADO DE ORDEN DE TRABAJO
	public long countByEstadooc(String estado);
		
	//METODOS PARA BUSCAR REGISTROS POR IDUNIDADMEDIDA Y ELIMINAR UNIDAD MEDIDA
	public List<Ordencompra> buscarporidunidad(int id);
		
	//METODOS PARA BUSCAR REGISTROS POR IDMAQUINA Y ELIMINAR MAQUINAS
	public List<Ordencompra> buscarporidmaq(int id);
}
