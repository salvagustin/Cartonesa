package com.cartonesa.control.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.Unidadmedida;

@Repository
public interface IUnidadmedida extends JpaRepository<Unidadmedida, Integer>{

	//LISTAR LOS REGISTROS EN FORMA DESCENDENTE.
	public List<Unidadmedida> findByOrderByIdunidadDesc( );
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR UNIDAD DE MEDIDA POR ID
	public List<Unidadmedida>findByIdunidad(int idunidad);
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR UNIDAD DE MEDIDA POR NOMBRE
	public List<Unidadmedida>findByUnidadContaining(String unidad);
}
