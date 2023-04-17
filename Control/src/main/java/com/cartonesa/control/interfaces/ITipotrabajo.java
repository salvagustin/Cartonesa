package com.cartonesa.control.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.Tipotrabajo;

@Repository
public interface ITipotrabajo extends JpaRepository<Tipotrabajo, Integer>{
	
	//LISTAR LOS REGISTROS EN FORMA DESCENDENTE.
	public List<Tipotrabajo> findByOrderByIdtiptrabDesc( );
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR TIPO DE TRABAJO POR NOMBRE
	public List<Tipotrabajo> findByTipotrabContaining(String tipotrab);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR TIPO DE TRABAJO POR ID
	public List<Tipotrabajo> findByIdtiptrab(int idtiptrab);
}
