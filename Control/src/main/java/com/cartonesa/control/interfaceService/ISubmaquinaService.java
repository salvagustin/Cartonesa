package com.cartonesa.control.interfaceService;

import java.util.List;
import java.util.Optional;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Submaquina;

public interface ISubmaquinaService {
	
	//METODOS CRUD DE LAS SUBMAQUINA
	public List<Submaquina>listar();
	public Optional<Submaquina>listarId(int idsubmaq);
	public int save(Submaquina s);
	public void delete(int idsubmaq);
	
	//METODOS PARA BUSCAR REGISTROS POR NOMBRE SUBMAQUINA
	public List<Submaquina> findByNombres(String nombres);
	
	//METODOS PARA BUSCAR REGISTROS POR MAQUINA
	public List<Submaquina> findByMaquina(Maquina maquina);
	
	//METODOS PARA BUSCAR REGISTROS POR IDMAQUINA SELECIONADA
	List<Submaquina>buscarPorIdMaquina(int idMaquina);
	
	//REPOSITORIO QUE CONSULTA A LA BASE SI EXISTE UN IDMAQUINA EN LAS SUBMAQUINA PARA ELIMINAR MAQUINA
	public List<Submaquina>buscarporidmaq(int id);
}