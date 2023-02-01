package com.cartonesa.control.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.Causa;

@Repository
public interface ICausa extends JpaRepository<Causa, Integer>{
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR CAUSAS POR FALLAS
	public List<Causa> findByFalla(String falla);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR CAUSAS POR SOLUCION
	public List<Causa> findBySolucion(String solucion);
	
}
