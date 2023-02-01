package com.cartonesa.control.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Submaquina;

@Repository
public interface ISubmaquina extends JpaRepository<Submaquina, Integer>{

	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR SUBMAQUINAS POR NOMBRE
	public List<Submaquina> findByNombres(String nombres);

	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR SUBMAQUINA POR MAQUINA
	public List<Submaquina> findByMaquina(Maquina maquina);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR SUBMAQUINA POR IDMAQUINA SELECCIONADO
	List<Submaquina> findByMaquina_Id(int idMaquina);

}
