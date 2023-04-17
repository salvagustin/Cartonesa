package com.cartonesa.control.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cartonesa.control.interfaceService.ISubmaquinaService;
import com.cartonesa.control.interfaces.ISubmaquina;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Submaquina;

@Service
public class SubmaquinaService implements ISubmaquinaService{
	
	//VARIABLE DE LA INTERFAZ DE AREA
	@Autowired
	private ISubmaquina data;

	
	
	//SERVICIO QUE LISTA TODAS LAS SUBMAQUINAS
	@Override
	public List<Submaquina> listar(){		
		return (List<Submaquina>)data.findByOrderByIdDesc();
	}

	//SERVICIO PARA EDITAR UN REGISTRO
	@Override
	public Optional<Submaquina> listarId(int id) {
		return data.findById(id);
	}

	//SERVICIO PARA GUARDAR UN NUEVO REGISTRO DE SUBMAQUINA
	@Override
	public int save(Submaquina s) {
		int res=0;
		Submaquina submaquina=data.save(s);
		if(!submaquina.equals(null)) {
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
	public List<Submaquina> findByNombres(String nombres) {
		// TODO Auto-generated method stub
		return data.findByNombresContaining(nombres);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR MAQUINA
	@Override
	public List<Submaquina> findByMaquina(Maquina maquina) {
		// TODO Auto-generated method stub
		return data.findByMaquina(maquina);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR IDMAQUINA SELECIONADA
	@Override
	public List<Submaquina> buscarPorIdMaquina(int idMaquina) {
		// TODO Auto-generated method stub
		return data.findByMaquina_Id(idMaquina);
	}

	@Override
	public List<Submaquina> buscarporidmaq(int id) {
		// TODO Auto-generated method stub
		return data.buscarporidmaq(id);
	}
	

}
