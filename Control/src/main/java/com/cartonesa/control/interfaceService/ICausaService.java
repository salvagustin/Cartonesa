package com.cartonesa.control.interfaceService;

import java.util.List;
import java.util.Optional;
import com.cartonesa.control.modelo.Causa;

public interface ICausaService {

	//METODOS CRUD DE LAS CAUSAS
	public List<Causa>listar();
	public Optional<Causa>listarId(int idcausa);
	public int save(Causa c);
	public void delete(int idcausa);
	
	//METODOS PARA BUSCAR REGISTROS POR ID CAUSA
	public List<Causa> findByIdcausa(int id);
	
	//METODOS PARA BUSCAR REGISTROS DE OT POR DESCRIPCION DE CAUSA
	public List<Causa> findByDescripcioncausa(String descripcioncausa);
	
	
	//METODOS PARA BUSCAR REGISTROS POR CAUSA DESCRIPCION O COINCIDENCIAS
	public List<Causa> findByDescripcioncausaContaining(String descripcioncausa);
	
}




