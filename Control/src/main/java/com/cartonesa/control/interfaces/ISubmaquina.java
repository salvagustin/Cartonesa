package com.cartonesa.control.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Submaquina;

@Repository
public interface ISubmaquina extends JpaRepository<Submaquina, Integer>{

	//LISTAR LOS REGISTROS EN FORMA DESCENDENTE.
	public List<Submaquina> findByOrderByIdDesc( );
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR SUBMAQUINAS POR NOMBRE
	public List<Submaquina> findByNombresContaining(String nombres);

	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR SUBMAQUINA POR MAQUINA
	public List<Submaquina> findByMaquina(Maquina maquina);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR SUBMAQUINA POR IDMAQUINA SELECCIONADA
	List<Submaquina> findByMaquina_Id(int idMaquina);

	//REPOSITORIO QUE CONSULTA A LA BASE SI EXISTE UN IDMAQUINA EN LAS SUBMAQUINA PARA ELIMINAR MAQUINA
	@Query(value = " SELECT * FROM Submaquina WHERE maquina_id=  ?1 ", nativeQuery = true)
	public List<Submaquina>buscarporidmaq(int id);
}
