package com.cartonesa.control.interfaceService;

import java.util.List;
import java.util.Optional;
import com.cartonesa.control.modelo.Tipotrabajo;

public interface ITipotrabajoService {

	//METODOS CRUD DE TIPO DE TRABAJO
	public List<Tipotrabajo>listar();
	public Optional<Tipotrabajo>listarId(int idtiptrab);
	public int save(Tipotrabajo t);
	public void delete(int idtiptrab);
	
	//METODOS PARA BUSCAR REGISTROS POR ID
	public List<Tipotrabajo> findByIdiptrab(int idtiptrab);
	
	//METODOS PARA BUSCAR REGISTROS POR TIPO DE TRABAJO
	public List<Tipotrabajo> findByTipotrab(String tipotrab);
}
