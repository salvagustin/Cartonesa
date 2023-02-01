package com.cartonesa.control.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.Tecnico;
import com.cartonesa.control.modelo.Tipotrabajo;

@Repository
public interface ITecnico extends JpaRepository<Tecnico, Integer> {

	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR TENICOS POR NOMBRE
	public List<Tecnico> findByNombre(String nombre);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR TECNICOS POR CODIGO DE TRABAJADOR
	public List<Tecnico> findByCodigotrab(String codigotrab);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR TECNICOS POR TIPO DE TRABAJO
	public List<Tecnico> findByTipotrabajo(Tipotrabajo tipotrabajo);
		
		
}
