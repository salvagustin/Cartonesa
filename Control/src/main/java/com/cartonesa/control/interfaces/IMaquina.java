package com.cartonesa.control.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.Area;
import com.cartonesa.control.modelo.Maquina;
  
@Repository
public interface IMaquina extends JpaRepository<Maquina, Integer>{
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR MAQUINAS POR NOMBRE
	public List<Maquina> findByMaquinanombre(String maquinanombre);
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR AREA
	public List<Maquina> findByArea(Area area);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR MAQUINAS POR AREA SELECIONADA
	List<Maquina>findByArea_Id(int idArea);
	
}