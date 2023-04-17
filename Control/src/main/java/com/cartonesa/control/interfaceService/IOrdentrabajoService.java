package com.cartonesa.control.interfaceService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import com.cartonesa.control.modelo.Causa;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordentrabajo;
import com.cartonesa.control.modelo.Tecnico;

public interface IOrdentrabajoService {	
	
	
	//INTERFAZ PARA GUARDAR LA CONSULTA SQL DEL CONTEO DE OT POR TECNICOS PARA REPORTES 
	public interface ConteoTecnico {
	    String getNombre();
	    Integer getPendiente();
	    Integer getProgramada();
	    Integer getPXRepuesto();
	    Integer getRealizada();
	    Integer getTotal();
	}
	//METODO QUE LISTA LA CUENTA DE OT POR TECNICO PARA REPORTES 
	public List<ConteoTecnico> conteotecnico();
	
		
	//INTERFAZ PARA GUARDAR LA CONSULTA SQL DEL CONTEO DE OT POR AREAS PARA REPORTES 
	public interface ConteoArea{
	    String getArea();
	    Integer getPendiente();
	    Integer getProgramada();
	    Integer getPXRepuesto();
	    Integer getRealizada();
	    Integer getTotal();
	}
	//METODO QUE LISTA LA CUENTA LAS OT POR AREA PARA REPORTES 
	public List<ConteoArea> conteoarea();
	
	
	//INTERFAZ PARA GUARDAR LA CONSULTA SQL DEL CONTEO DE OT POR MAQUINAS PARA REPORTES 
	public interface ConteoMaquina{
	    String getMaquina();
	    Integer getPendiente();
	    Integer getProgramada();
	    Integer getPXRepuesto();
	    Integer getRealizada();
	    Integer getTotal();
	}
	//METODO QUE LISTA LA CUENTA DE OT POR MAQUINA PARA REPORTES 
	public List<ConteoMaquina> conteomaquina();
		
	
	//INTERFAZ PARA GUARDAR LA CONSULTA SQL DEL CONTEO DE OT POR TIPO TRABAJO PARA REPORTES 
	public interface ConteoTipotrabajo{
	    String getTipotrabajo();
	    Integer getPendiente();
	    Integer getProgramada();
	    Integer getPXRepuesto();
	    Integer getRealizada();
	    Integer getTotal();
	}
	//METODO QUE LISTA LA CUENTA DE OT POR TIPOTRABAJO PARA REPORTES 
	public List<ConteoTipotrabajo> conteotipotrabajo();
	
	//INTERFAZ PARA GUARDAR LA CONSULTA SQL DE OT PARA EXPORTAR A PDF 
	public interface OrdenTExport{
	    Integer getIdordentrab();
	    Timestamp getFecharegistro();
	    String getMaquinanombre();
	    String getNombres();
	    String getCodigotrab();
	    String getAreanombre();
	    String getTipotrab();
	    String getNombre();
	    String getFalla();
	    String getDescripcion();
	    String getDescripcioncausa();  
	}
	//METODO PARA EXPORTAR ORDEN DE TRABAJO 
	public Optional<OrdenTExport>listadoOT(int idordentrab);
	
	//INTERFAZ PARA GUARDAR LA CONSULTA SQL DE OT PENDIENTES PARA REPORTES 
	public interface OrdenTPendientes{
	    Integer getIdordentrab();
	    String getMaquinanombre();
	    String getNombres();
	    String getDescripcion();
	    String getDescripcioncausa();
	    String getFalla();
	    Integer getIdordencomp();
	    String getEstadooc(); 
		Date getFechareq();
		Timestamp getFecharegistro();
	}
	//METODO PARA EXPORTAR ORDENES PENDIENTES 
	public List<OrdenTPendientes>listapendts();
	//METODO PARA EXPORAT ORDENES PENDIENTES FILTRADAS POR AREA
	public List<OrdenTPendientes>listapendtsArea(int idarea);
	//METODO PARA EXPORAT ORDENES PENDIENTES FILTRADAS POR MAQUINA
	public List<OrdenTPendientes>listapendtsMaquina(int idmaq);
	
	
	
	//INTERFAZ PARA GUARDAR LA CONSULTA SQL DE OT PROGRAMADAS PARA EXPORTAR
		public interface OrdenTProgramadas{
		    Integer getIdordentrab();
		    String getMaquinanombre();
		    String getNombres();
		    String getDescripcion();
		    String getNombre();
		    String getTipotrab();
		    Integer getNumsolcompra();
		    Timestamp getFecharegistro();
		 }
	//METODO PARA  EXPORTAR ORDENES PENDIENTES 
	public List<OrdenTProgramadas>listaprogra();
	//METODO PARA  EXPORAT ORDENES PENDIENTES FILTRADAS POR AREA
	public List<OrdenTProgramadas>listaprograArea(int idarea);
	//METODO PARA EXPORAT ORDENES PENDIENTES FILTRADAS POR MAQUINA
	public List<OrdenTProgramadas>listaprograMaquina(int idmaq);
		
	
	//METODOS CRUD DE LAS ORDEN DE TRABAJO
	public List<Ordentrabajo>listar();
	public Optional<Ordentrabajo>listarId(int id);
	public int save(Ordentrabajo o);
	public void delete(int id);
	
	//METODO PARA ACTUALIZAR EL ESTADO DE OT
	public int update(int id, String newestado, Ordentrabajo o);
		
	//METODO PARA BUSCAR REGISTROS POR MAQUINA
	public List<Ordentrabajo>findByMaquina(Maquina maquina);
	
	//METODO PARA BUSCAR REGISTROS POR TECNICO
	public List<Ordentrabajo>findByTecnico(Tecnico tecnico);
	
	//METODO PARA BUSCAR REGISTROS POR CAUSA
	public List<Ordentrabajo>findByCausa(Causa causa);
	
	//METODO PARA BUSCAR REGISTROS POR SOLUCION
	public List<Ordentrabajo>findByEstadoot(String estadoot);
	
	//METODO PARA BUSCAR REGISTROS POR CORRELATIVO
	public List<Ordentrabajo>findByIdordentrab(int id);
	
	//METODO PARA BUSCAR REGISTROS POR SOLUCION
	public List<Ordentrabajo>findByTipoorden(String tipoorden);
	
	//METODOS PARA CONTAR REGISTROS POR FALLA
	public List<Ordentrabajo> findByFallaContaining(String falla);	
	
	
	//METODO PARA CONTAR LAS OT DIARIAS
	public int conteodiarioOT(String estadoot);	
	
	//METODOS PARA CONTAR TODOS LOS REGISTROS
	public long getNumberOfOrdentrabajo();
	
	//METODOS PARA HACER CONTEO TOTAL DE REGISTROS POR ESTADO DE ORDEN DE TRABAJO
	public long countByEstadoot(String estado);
	
	
	//METODOS PARA BUSCAR REGISTROS POR IDTECNICO PARA ELIMINAR TECNICO
	public List<Ordentrabajo>buscarporidtecnico(int id);
	
	//METODOS PARA BUSCAR REGISTROS POR IDSUBMAQUINA PARA ELIMINAR SUBMAQUINAS
	public List<Ordentrabajo> buscarporidsubmaq(int id);
	
	//METODOS PARA BUSCAR REGISTROS POR IDMAQUINA PARA ELIMINAR MAQUINAS
	public List<Ordentrabajo> buscarporidmaq(int id);
		
	//METODOS PARA BUSCAR REGISTROS POR IDCAUSA PARA ELIMINAR CAUSA
	public List<Ordentrabajo> buscarporidcausa(int id);
	
	//METODOS PARA BUSCAR REGISTROS POR IDORDENCOMPRA PARA ELIMINAR OC
	public List<Ordentrabajo> buscarporidordencomp(int id);

	
	//METODO PARA LISTAR LAS OT PENDIENTE
	public List<Ordentrabajo>listOTpendiente(String estadoot);
	
	//METODO PARA LISTAR LAS OT PROGRAMADAS
	public List<Ordentrabajo>listOTprogramadas(String estadoot);

	

}