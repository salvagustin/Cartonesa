package com.cartonesa.control.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cartonesa.control.interfaceService.ICausaService;
import com.cartonesa.control.interfaces.ICausa;
import com.cartonesa.control.modelo.Causa;


@Service
public class CausaService implements ICausaService{
	
	//VARIABLE DE LA INTERFAZ DE CAUSA
	@Autowired
	private ICausa data;
	
	
	
	//SERVICIO QUE LISTA TODAS LAS CAUSAS
	@Override
	public List<Causa> listar( ) {
		return (List<Causa>)data.findAll();
	}

	//SERVICIO QUE LISTA TODAS LAS AREAS
	@Override
	public Optional<Causa> listarId(int idcausa) {
		return data.findById(idcausa);
	}

	//SERVICIO PARA GUARDAR UN NUEVO REGISTRO DE CAUSA
	@Override
	public int save(Causa c) {
		int res=0;
		Causa causa=data.save(c);
		if(!causa.equals(null)) {
			res=1;
		}
		return res;
	}

	//SERVICIO PARA ELIMINAR UNA CAUSA SELECCIONADA
	@Override
	public void delete(int idcausa) {
		data.deleteById(idcausa);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR NOMBRE
	@Override
	public List<Causa> findByFalla(String falla) {
		// TODO Auto-generated method stub
		return data.findByFalla(falla);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR NOMBRE
	@Override
	public List<Causa> findBySolucion(String solucion) {
		// TODO Auto-generated method stub
		return data.findBySolucion(solucion);
	}
	

}
