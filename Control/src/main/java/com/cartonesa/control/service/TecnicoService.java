package com.cartonesa.control.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cartonesa.control.interfaceService.ITecnicoService;
import com.cartonesa.control.interfaces.ITecnico;
import com.cartonesa.control.modelo.Tecnico;
import com.cartonesa.control.modelo.Tipotrabajo;

@Service
public class TecnicoService implements ITecnicoService{
	
	//VARIABLE DE LA INTERFAZ DE TECNICO
	@Autowired
	private ITecnico data;

	
	
	//SERVICIO QUE LISTA TODOS LOS TECNICOS
	@Override
	public List<Tecnico> listar() {
		return (List<Tecnico>)data.findAll();
	}

	//SERVICIO PARA EDITAR UN REGISTRO
	@Override
	public Optional<Tecnico> listarId(int idtecn) {
		return data.findById(idtecn);
	}

	//SERVICIO PARA GUARDAR UN NUEVO REGISTRO DE TECNICO
	@Override
	public int save(Tecnico t) {
		int res=0;
		Tecnico tecnico=data.save(t);
		if(!tecnico.equals(null)) {
			res=1;
		}
		return res;
	}

	//SERVICIO PARA ELIMINAR UN TECNICO SELECCIONADO
	@Override
	public void delete(int id) {
		data.deleteById(id);		
	}

	//SERVICIO QUE BUSCAR REGISTROS POR NOMBRE
	@Override
	public List<Tecnico> findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return data.findByNombre(nombre);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR CODIGO DE TRABAJADOR
	@Override
	public List<Tecnico> findByCodigotrab(String codigotrab) {
		// TODO Auto-generated method stub
		return data.findByCodigotrab(codigotrab);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR TIPO DE TRABAJO
	@Override
	public List<Tecnico> findByTipotrabajo(Tipotrabajo tipotrabajo) {
		// TODO Auto-generated method stub
		return data.findByTipotrabajo(tipotrabajo);
	}

}
