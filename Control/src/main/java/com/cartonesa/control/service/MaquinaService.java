package com.cartonesa.control.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cartonesa.control.interfaceService.IMaquinaService;
import com.cartonesa.control.interfaces.IMaquina;
import com.cartonesa.control.modelo.Area;
import com.cartonesa.control.modelo.Maquina;

@Service
public class MaquinaService implements IMaquinaService{

	//VARIABLE DE LA INTERFAZ DE MAQUINA
	@Autowired
	private IMaquina data;
	
	

	//SERVICIO QUE LISTA TODAS LAS MAQUINAS
	@Override
	public List<Maquina> listar() {
		return (List<Maquina>)data.findAll();
	}
	
	//SERVICIO PARA EDITAR UN REGISTRO
	@Override
	public Optional<Maquina> listarId(int idmaq) {
		return data.findById(idmaq);
	}

	//SERVICIO PARA GUARDAR UN NUEVO REGISTRO DE MAQUINA
	@Override
	public int save(Maquina m) {
		int res=0;
		Maquina maquina=data.save(m);
		if(!maquina.equals(null)) {
			res=1;
		}
		return res;
	}

	//SERVICIO PARA ELIMINAR UNA MAQUINA SELECCIONADA
	@Override
	public void delete(int idmaq) {
		data.deleteById(idmaq); 		
	}

	//SERVICIO QUE BUSCAR REGISTROS POR NOMBRE
	@Override
	public List<Maquina> findByMaquinanombre(String maquinanombre) {
		// TODO Auto-generated method stub
		return data.findByMaquinanombre(maquinanombre);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR IDAREA SELECCIONADA
	@Override
	public List<Maquina> buscarPorIdArea(int idArea) {
		// TODO Auto-generated method stub
		return data.findByArea_Id(idArea);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR AREA
	@Override
	public List<Maquina> findByArea(Area area) {
		// TODO Auto-generated method stub
		return data.findAll();
	}

}
