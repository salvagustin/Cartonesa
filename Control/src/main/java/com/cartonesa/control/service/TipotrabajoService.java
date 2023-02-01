package com.cartonesa.control.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cartonesa.control.interfaceService.ITipotrabajoService;
import com.cartonesa.control.interfaces.ITipotrabajo;
import com.cartonesa.control.modelo.Tipotrabajo;

@Service
public class TipotrabajoService implements ITipotrabajoService{

	//VARIABLE DE LA INTERFAZ DE TIPO DE TRABAJO
	@Autowired
	private ITipotrabajo data;

	
	
	//SERVICIO QUE LISTA TODOS LOS TIPOS DE TRABAJO
	@Override
	public List<Tipotrabajo>  listar() {
		return (List<Tipotrabajo>)data.findAll();
	}

	//SERVICIO PARA EDITAR UN REGISTRO
	@Override
	public Optional<Tipotrabajo> listarId(int idtiptrab) {
		return data.findById(idtiptrab);
	}

	//SERVICIO PARA GUARDAR UN NUEVO REGISTRO DE TIPO DE TRABAJO
	@Override
	public int save(Tipotrabajo t) {
		int res=0;
		Tipotrabajo tipotrabajo=data.save(t);
		if(!tipotrabajo.equals(null)) {
			res=1;
		}
		return res;
	}

	//SERVICIO PARA ELIMINAR UN TIPO DE TRABAJO SELECCIONADO
	@Override
	public void delete(int idtiptrab) {
		data.deleteById(idtiptrab);		
	}

	//SERVICIO QUE BUSCAR REGISTROS POR TIPO DE TRABAJO
	@Override
	public List<Tipotrabajo> findByTipotrab(String tipotrab) {
		// TODO Auto-generated method stub
		return data.findByTipotrab(tipotrab);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR ID
	@Override
	public List<Tipotrabajo> findByIdiptrab(int idtiptrab) {
		// TODO Auto-generated method stub
		return data.findByIdtiptrab(idtiptrab);
	}
}
