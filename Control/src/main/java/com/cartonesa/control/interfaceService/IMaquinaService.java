package com.cartonesa.control.interfaceService;

import java.util.List;
import java.util.Optional;
import com.cartonesa.control.modelo.Area;
import com.cartonesa.control.modelo.Maquina;

public interface IMaquinaService {
	
	//METODOS CRUD DE LAS MAQUINAS
	public List<Maquina>listar();
	public Optional<Maquina>listarId(int id);
	public int save(Maquina m);
	public void delete(int id);
	
	
	//METODO PARA BUSCAR OT POR NOMBRE DE MAQUINA
	public List<Maquina>findByMaquinanombre(String maquinanombre);
	
	//METODO PARA BUSCAR AREA
	public List<Maquina>findByArea(Area area);
		
	//METODO PARA BUSCAR MAQUINAS POR IDAREA SELECCIONADA
	List<Maquina>buscarPorIdArea(int idArea);
	
	//METODOS PARA BUSCAR REGISTROS POR IDORDENCOMPRA PARA ELIMINAR OC
	public List<Maquina> buscarporidarea(int id);
}





