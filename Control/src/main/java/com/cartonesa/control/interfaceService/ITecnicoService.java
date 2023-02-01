package com.cartonesa.control.interfaceService;

import java.util.List;
import java.util.Optional;
import com.cartonesa.control.modelo.Tecnico;
import com.cartonesa.control.modelo.Tipotrabajo;

public interface ITecnicoService {
	
	//METODOS CRUD DE LOS TECNICO
	public List<Tecnico>listar();
	public Optional<Tecnico>listarId(int id);
	public int save(Tecnico t);
	public void delete(int id);
	
	//METODO PARA BUSCAR REGISTROS POR NOMBRE
	public List<Tecnico>findByNombre(String nombre);
	
	//METODO PARA BUSCAR REGISTROS POR CODIGO DE TRABAJADOR
	public List<Tecnico>findByCodigotrab(String codigotrab);
	
	//METODO PARA BUSCAR REGISTROS POR TIPO DE TRABAJO
	public List<Tecnico>findByTipotrabajo(Tipotrabajo tipotrabajo);
}
