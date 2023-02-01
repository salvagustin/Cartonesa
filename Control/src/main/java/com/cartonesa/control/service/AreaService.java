package com.cartonesa.control.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cartonesa.control.interfaceService.IAreaService;
import com.cartonesa.control.interfaces.IArea;
import com.cartonesa.control.modelo.Area;

@Service
public class AreaService implements IAreaService{

	//VARIABLE DE LA INTERFAZ DE AREA
	@Autowired
	private IArea data;
	
	
	
	//SERVICIO QUE LISTA TODAS LAS AREAS
	@Override
	public List<Area> listar() {
		return (List<Area>)data.findAll();
	}
	
	//SERVICIO PARA EDITAR UN REGISTRO
	@Override
	public Optional<Area> listarId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}
	
	//SERVICIO PARA GUARDAR UN NUEVO REGISTRO DE AREA
	@Override
	public int save(Area a) {
		int res=0;
		Area area=data.save(a);
		if(!area.equals(null)) {
			res=1;
		}
		return res;
	}

	//SERVICIO PARA ELIMINAR UN AREA SELECCIONADA
	@Override
	public void delete(int id) {
		data.deleteById(id);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR NOMBRE
	@Override
	public List<Area> findByAreanombre(String areanombre) {
		// TODO Auto-generated method stub
		return data.findByAreanombre(areanombre);
	}

}
