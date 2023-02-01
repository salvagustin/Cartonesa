package com.cartonesa.control.interfaceService;

import java.util.List;
import java.util.Optional;
import com.cartonesa.control.modelo.Causa;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordentrabajo;
import com.cartonesa.control.modelo.Tecnico;

public interface IOrdentrabajoService {	
	
	//METODOS CRUD DE LAS ORDEN DE TRABAJO
	public List<Ordentrabajo>listar();
	public Optional<Ordentrabajo>listarId(int id);
	public int save(Ordentrabajo o);
	public void delete(int id);
	
	//public int update(Ordentrabajo o);
	
	//METODOS PARA BUSCAR REGISTROS POR SOLUCION
	public List<Ordentrabajo>findByMaquina(Maquina maquina);
	
	//METODOS PARA BUSCAR REGISTROS POR SOLUCION
	public List<Ordentrabajo>findByTecnico(Tecnico tecnico);
	
	//METODOS PARA BUSCAR REGISTROS POR SOLUCION
	public List<Ordentrabajo>findByCausa(Causa causa);
	
	//METODOS PARA BUSCAR REGISTROS POR SOLUCION
	public List<Ordentrabajo>findByEstadoot(String estadoot);
	
	//METODOS PARA BUSCAR REGISTROS POR CORRELATIVO
	public List<Ordentrabajo>findByIdordentrab(int id);
	
	//METODOS PARA BUSCAR REGISTROS POR SOLUCION
	public List<Ordentrabajo>findByTipoorden(String tipoorden);
		
	//METODOS PARA CONTAR TODOS LOS REGISTROS
	public long getNumberOfOrdentrabajo();
	
	//METODOS PARA CONTAR REGISTROS POR ESTADO DE ORDEN DE TRABAJO
	public long countByEstadoot(String estado);
	
}