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
	
	
	//BUSCAR OT POR NOMBRE DE AREA
	public List<Area> findByAreanombre(String areanombre);

	//BUSCAR AREAS POR NOMBRE DE AREA O COINCIDENCIAS
	public List<Area> findByAreanombreContaining(String areanombre);
	
	
}

