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
	
	
	//METODOS PARA BUSCAR REGISTROS POR FALLA
	public List<Causa> findByFalla(String falla);	
	
	//METODOS PARA BUSCAR REGISTROS POR SOLUCION
	public List<Causa> findBySolucion(String solucion);
	
}




