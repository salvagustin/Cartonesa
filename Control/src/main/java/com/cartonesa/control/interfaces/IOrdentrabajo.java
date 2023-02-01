package com.cartonesa.control.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.Causa;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordentrabajo;
import com.cartonesa.control.modelo.Tecnico;

@Repository
public interface IOrdentrabajo extends JpaRepository<Ordentrabajo, Integer>{

	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR MAQUINA
	public List<Ordentrabajo>findByMaquina(Maquina maquina);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR TECNICO
	public List<Ordentrabajo>findByTecnico(Tecnico tecnico);
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR CAUSA
	public List<Ordentrabajo>findByCausa(Causa causa);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR ESTADO OT
	public List<Ordentrabajo>findByEstadoot(String estadoot);
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR CORRELATIVO
	public List<Ordentrabajo>findByIdordentrab(int id);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR TIPO ORDEN
	public List<Ordentrabajo>findByTipoorden(String tipoorden);

	//REPOSITORIO QUE CUENTA LOS REGISTROS
	long countByEstadoot(String estado);
	
}