package com.cartonesa.control.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartonesa.control.interfaceService.IUnidadmedidaService;
import com.cartonesa.control.interfaces.IUnidadmedida;
import com.cartonesa.control.modelo.Unidadmedida;

@Service
public class UnidadmedidaService implements IUnidadmedidaService{

	//VARIABLE DE LA INTERFAZ DE UNIDAD DE MEDIDA
	@Autowired
	private IUnidadmedida data;

	
	
	//SERVICIO QUE LISTA TODAS LAS UNIDADES DE MEDIDA
	@Override
	public List<Unidadmedida>  listar() {
		return (List<Unidadmedida>)data.findByOrderByIdunidadDesc();
	}

	//SERVICIO PARA EDITAR UN REGISTRO
	@Override
	public Optional<Unidadmedida> listarId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}

	//SERVICIO PARA GUARDAR UN NUEVO REGISTRO DE CAUSA
	@Override
	public int save(Unidadmedida d) {
		int res=0;
		Unidadmedida unidad=data.save(d);
		if(!unidad.equals(null)) {
			res=1;
		}
		return res;
	}

	//SERVICIO PARA ELIMINAR UNA UNIDAD SELECCIONADA
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		data.deleteById(id);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR IDUNIDAD
	@Override
	public List<Unidadmedida> findByIdunidad(int idunidad) {
		// TODO Auto-generated method stub
		return data.findByIdunidad(idunidad);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR UNIDAD DE MEDIDA
	@Override
	public List<Unidadmedida> findByUnidad(String unidad) {
		// TODO Auto-generated method stub
		return data.findByUnidadContaining(unidad);
	}
}
