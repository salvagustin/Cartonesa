package com.cartonesa.control.interfaceService;

import java.util.List;
import java.util.Optional;

import com.cartonesa.control.modelo.Unidadmedida;

public interface IUnidadmedidaService {

	//METODOS CRUD DE UNIDAD DE MEDIDA
	public List<Unidadmedida>listar();
	public Optional<Unidadmedida>listarId(int id);
	public int save(Unidadmedida d);
	public void delete(int id);
	
	//METODOS PARA BUSCAR REGISTROS POR ID
	public List<Unidadmedida>findByIdunidad(int idunidad);
	
	//METODOS PARA BUSCAR REGISTROS POR UNIDAD DE MEDIDA
	public List<Unidadmedida>findByUnidad(String unidad);
}
