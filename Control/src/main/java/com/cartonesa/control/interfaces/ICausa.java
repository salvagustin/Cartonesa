package com.cartonesa.control.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.Causa;

@Repository
public interface ICausa extends JpaRepository<Causa, Integer>{
	
	//LISTAR LOS REGISTROS EN FORMA DESCENDENTE.
	public List<Causa> findByOrderByIdcausaDesc( );
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR CAUSAS POR DESCRICPION DE LA CAUSA
	public List<Causa> findByDescripcioncausaContaining(String descripcioncausa);
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR OT POR DESCRIPCIONCAUSA
	public List<Causa> findByDescripcioncausa(String descripcioncausa);
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR CAUSAS POR ID
	public List<Causa> findByIdcausa(int id);
}
