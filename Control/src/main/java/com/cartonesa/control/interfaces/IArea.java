package com.cartonesa.control.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.Area;

@Repository
public interface IArea extends JpaRepository<Area, Integer>{
	
	//LISTAR LOS REGISTROS EN FORMA DESCENDENTE.
	public List<Area> findByOrderByIdDesc( );
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR AREAS POR NOMBRE
	public List<Area> findByAreanombreContaining(String areanombre);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR OT POR AREA NOMBRE
	public List<Area> findByAreanombre(String areanombre);
			
 }
