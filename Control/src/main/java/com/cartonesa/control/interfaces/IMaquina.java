package com.cartonesa.control.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.Area;
import com.cartonesa.control.modelo.Maquina;
  
@Repository
public interface IMaquina extends JpaRepository<Maquina, Integer>{
		
	//LISTAR LOS REGISTROS EN FORMA DESCENDENTE.
	public List<Maquina> findByOrderByIdDesc( );
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR MAQUINAS POR NOMBRE
	public List<Maquina> findByMaquinanombre(String maquinanombre);
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR MAQUINAS POR AREA
	public List<Maquina> findByArea(Area area);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR MAQUINAS POR IDAREA 
	List<Maquina>findByArea_Id(int idArea);
	
	//REPOSITORIO QUE CONSULTA A LA BASE SI EXISTE UN IDTIPOTRABAJO EN LAS ORDENES DE TRABAJO PARA ELIMINAR AREA
	@Query(value = " SELECT * FROM Maquina WHERE area_id=  ?1 ", nativeQuery = true)
	public List<Maquina>buscarporidarea(int id);
}