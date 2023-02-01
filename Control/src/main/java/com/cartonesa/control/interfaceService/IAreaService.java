package com.cartonesa.control.interfaceService;

import java.util.List;
import java.util.Optional;

import com.cartonesa.control.modelo.Area;

public interface IAreaService {
	
	//METODOS CRUD DE LAS AREAS
	public List<Area>listar();		
	public Optional<Area>listarId(int id);	
	public int save(Area a);	
	public void delete(int id);	
	
	
	//BUSCAR AREAS POR NOMBRE DE AREA
	public List<Area> findByAreanombre(String areanombre);
		
}

