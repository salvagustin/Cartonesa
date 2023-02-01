package com.cartonesa.control.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cartonesa.control.interfaceService.IOrdentrabajoService;
import com.cartonesa.control.interfaces.IOrdentrabajo;
import com.cartonesa.control.modelo.Causa;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordentrabajo;
import com.cartonesa.control.modelo.Tecnico;

@Service
public class OrdentrabajoService implements IOrdentrabajoService{

	//VARIABLE DE LA INTERFAZ DE AREA
	@Autowired
	private IOrdentrabajo data;

	
	
	//SERVICIO QUE LISTA TODAS LAS ORDENES DE TRABAJO
	@Override
	public List<Ordentrabajo>  listar() {
		return (List<Ordentrabajo>)data.findAll();
	}

	//SERVICIO QUE LISTA LAS ORDENES DE TRABAJO POR ID
	@Override	
	public Optional<Ordentrabajo> listarId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}

	//SERVICIO PARA GUARDAR UN NUEVO REGISTRO DE ORDEN DE TRABAJO
	@Override
	public int save(Ordentrabajo o) {
		int res=0;
			Ordentrabajo ordentrabajo=data.save(o);
		if(!ordentrabajo.equals(null)) {
			res=1;
		}
		return res;
	}

	//SERVICIO PARA ELIMINAR UNA ORDEN SELECCIONADA
	@Override
	public void delete(int id) {
		data.deleteById(id);		
	}

	//SERVICIO QUE BUSCAR REGISTROS POR MAQUINA
	@Override
	public List<Ordentrabajo> findByMaquina(Maquina maquina) {
		// TODO Auto-generated method stub
		return data.findByMaquina(maquina);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR TECNICO
	@Override
	public List<Ordentrabajo> findByTecnico(Tecnico tecnico) {
		// TODO Auto-generated method stub
		return data.findByTecnico(tecnico);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR CAUSA
	@Override
	public List<Ordentrabajo> findByCausa(Causa causa) {
		// TODO Auto-generated method stub
		return data.findByCausa(causa);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR ESTADO OT
	@Override
	public List<Ordentrabajo> findByEstadoot(String estadoot) {
		// TODO Auto-generated method stub
		return data.findByEstadoot(estadoot);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR CORRELATIVO
	@Override
	public List<Ordentrabajo> findByIdordentrab(int id) {
		// TODO Auto-generated method stub
		return data.findByIdordentrab(id);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR TIPO ORDEN
	@Override
	public List<Ordentrabajo> findByTipoorden(String tipoorden) {
		// TODO Auto-generated method stub
		return data.findByTipoorden(tipoorden);
	}

	//SERVICIO QUE CUENTA REGISTROS POR ESTADO DE ORDEN DE TRABAJO
	@Override
	public long countByEstadoot(String estado) {
		// TODO Auto-generated method stub
		return data.countByEstadoot(estado);		
	}

	//SERVICIO QUE CUENTA TODOS LOS REGISTROS DE ORDEN DE TRABAJO
	@Override
	public long getNumberOfOrdentrabajo() {
		// TODO Auto-generated method stub
		return data.count();
	}
	
	

}
