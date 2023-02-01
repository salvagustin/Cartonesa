package com.cartonesa.control.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cartonesa.control.interfaceService.IOrdencompraService;
import com.cartonesa.control.interfaces.IOrdencompra;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordencompra;
import com.cartonesa.control.modelo.Unidadmedida;

@Service
public class OrdencompraService implements IOrdencompraService{

	//VARIABLE DE LA INTERFAZ DE ORDEN DE COMPRA
	@Autowired
	private IOrdencompra data;

	
	
	//SERVICIO PARA EDITAR UN REGISTRO
	@Override
	public Optional<Ordencompra> listarId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}

	//SERVICIO PARA GUARDAR UN NUEVO REGISTRO DE ORDEN DE COMPRA
	@Override
	public int save(Ordencompra c) {
		int res=0;
		Ordencompra ordencompra=data.save(c);
		if(!ordencompra.equals(null)) {
			res=1;
		}
		return res;
	}

	//SERVICIO PARA ELIMINAR UNA ORDEN DE COMPRA SELECCIONADA
	@Override
	public void delete(int id) {
		data.deleteById(id);
	}

	//SERVICIO QUE LISTA TODAS LAS ORDENES DE COMPRA
	@Override
	public List<Ordencompra> listar() {
		return (List<Ordencompra>)data.findAll();
	}

	//SERVICIO QUE BUSCAR REGISTROS POR NUMERO DE SOLICITUD DE COMPRA
	@Override
	public List<Ordencompra> findByNumsolcompra(int numsolcompra) {
		// TODO Auto-generated method stub
		return data.findByNumsolcompra(numsolcompra);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR COTIZACION
	@Override
	public List<Ordencompra> findByCotizacion(String cotizacion) {
		// TODO Auto-generated method stub
		return data.findByCotizacion(cotizacion);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR CANTIDAD DE PRODUCTO
	@Override
	public List<Ordencompra> findByCantprod(BigDecimal cantprod) {
		// TODO Auto-generated method stub
		return data.findByCantprod(cantprod);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR PRODUCTO
	@Override
	public List<Ordencompra> findByProducto(String producto) {
		// TODO Auto-generated method stub
		return data.findByProducto(producto);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR MAQUINA
	@Override
	public List<Ordencompra> findByMaquina(Maquina maquina) {
		// TODO Auto-generated method stub
		return data.findByMaquina(maquina);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR UNIDAD DE MEDIDA
	@Override
	public List<Ordencompra> findByUnidadmedida(Unidadmedida unidadmedida) {
		// TODO Auto-generated method stub
		return data.findByUnidadmedida(unidadmedida);
	}		
}
