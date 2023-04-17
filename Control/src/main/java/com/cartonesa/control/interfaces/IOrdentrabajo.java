package com.cartonesa.control.interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.interfaceService.IOrdentrabajoService.ConteoArea;
import com.cartonesa.control.interfaceService.IOrdentrabajoService.ConteoMaquina;
import com.cartonesa.control.interfaceService.IOrdentrabajoService.ConteoTecnico;
import com.cartonesa.control.interfaceService.IOrdentrabajoService.ConteoTipotrabajo;
import com.cartonesa.control.interfaceService.IOrdentrabajoService.OrdenTExport;
import com.cartonesa.control.interfaceService.IOrdentrabajoService.OrdenTPendientes;
import com.cartonesa.control.interfaceService.IOrdentrabajoService.OrdenTProgramadas;
import com.cartonesa.control.modelo.Causa;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordentrabajo;
import com.cartonesa.control.modelo.Tecnico;

@Repository
public interface IOrdentrabajo extends JpaRepository<Ordentrabajo, Integer>{

	//LISTAR LOS REGISTROS EN FORMA DESCENDENTE.
	public List<Ordentrabajo> findByOrderByIdordentrabDesc( );
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR MAQUINA
	public List<Ordentrabajo>findByMaquinaOrderByIdordentrabDesc(Maquina maquina);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR TECNICO
	public List<Ordentrabajo>findByTecnicoOrderByIdordentrabDesc(Tecnico tecnico);
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR CAUSA
	public List<Ordentrabajo>findByCausaOrderByIdordentrabDesc(Causa causa);
		
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR ESTADO OT
	public List<Ordentrabajo>findByEstadootContainingOrderByIdordentrabDesc(String estadoot);
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR ESTADO OT
	public List<Ordentrabajo>findByIdordentrabOrderByIdordentrabDesc(int idordentrab);
			
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR TIPO ORDEN
	public List<Ordentrabajo>findByTipoordenOrderByIdordentrabDesc(String tipoorden);

	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR ORDEN DE TRABAJO POR TIPO ORDEN
	public List<Ordentrabajo>findByFallaContainingOrderByIdordentrabDesc(String falla);
	
	//REPOSITORIO QUE CUENTA LOS REGISTROS POR ESTADO DE OT
	long countByEstadoot(String estado);

	//METODO PARA CONTAR LAS OT DIARIAS POR ESTADO
	@Query(value = " select count(*) from ordentrabajo where estadoot=?1 and fecharegistro>=date(now()) "
			, nativeQuery = true)
	public int conteodiarioOT(String estadoot);
	
	
	//REPOSITORIO QUE CUENTA Y AGRUPA POR ESTADO LAS OT FILTRADAS POR TECNICO
	@Query(value = " select t.nombre as Nombre, "
			+ "count(case when estadoot='Pendiente'then 1 else null end)as Pendiente,  "
			+ "count(case when estadoot='Programada'then 1 else null end)as Programada, "
			+ "count(case when estadoot='PXRepuesto'then 1 else null end)as PXRepuesto, "
			+ "count(case when estadoot='Realizado'then 1 else null end)as Realizada,count(estadoot) "
			+ "as Total from ordentrabajo o join tecnico t on o.idtecn=t.idtecn group by t.nombre; "
			, nativeQuery = true)
	public List<ConteoTecnico> conteotecnico();
	
	//REPOSITORIO QUE CUENTA Y AGRUPA POR ESTADO LAS OT FILTRADAS POR AREA
	@Query( value=" select a.areanombre as Area, "
			+ " count(case when estadoot='Pendiente'then 1 else null end)as Pendiente, "
			+ " count(case when estadoot='Programada'then 1 else null end)as Programada, "
			+ " count(case when estadoot='PXRepuesto'then 1 else null end)as PXRepuesto, "
			+ " count(case when estadoot='Realizado'then 1 else null end)as Realizada ,"
			+ " count(estadoot) as Total from ordentrabajo o join maquina m on o.idmaq=m.id "
			+ " join area a on o.idmaq=a.id group by a.areanombre "
			,nativeQuery=true)
	public List<ConteoArea> conteoarea();
	
	
	//REPOSITORIO QUE CUENTA Y AGRUPA POR ESTADO LAS OT FILTRADAS POR MAQUINA
	@Query(value="select m.maquinanombre as Maquina, "
			+ "count(case when estadoot='Pendiente'then 1 else null end)as Pendiente,"
			+ "	count(case when estadoot='Programada'then 1 else null end)as Programada, "
			+ "	count(case when estadoot='PXRepuesto'then 1 else null end)as PXRepuesto, "
			+ "	count(case when estadoot='Realizado'then 1 else null end)as Realizada ,count(estadoot)"
			+ " as Total from ordentrabajo o join maquina m on o.idmaq=m.id group by m.maquinanombre; "
			,nativeQuery=true)
	public List<ConteoMaquina> conteomaquina();
	
	//REPOSITORIO QUE CUENTA Y AGRUPA POR ESTADO LAS OT FILTRADAS POR TIPO TRABAJO
	@Query(value="select t.tipotrab as Tipotrabajo, "
			+ " count(case when estadoot='Pendiente'then 1 else null end)as Pendiente, "
			+ " count(case when estadoot='Programada'then 1 else null end)as Programada, "
			+ " count(case when estadoot='PXRepuesto'then 1 else null end)as PXRepuesto, "
			+ " count(case when estadoot='Realizado'then 1 else null end)as Realizada ,"
			+ " count(estadoot) as Total from ordentrabajo o join tecnico c on o.idtecn=c.idtecn "
			+ " join tipotrabajo t on t.idtiptrab=c.idtiptrab group by t.tipotrab "
			,nativeQuery=true)
	public List<ConteoTipotrabajo>conteotipotrabajo();
	
	
	//METODO PARA EXPORTAR ORDENES PENDIENTES 
	@Query(value=" select idordentrab, fecharegistro, m.maquinanombre, s.nombres, t.codigotrab, a.areanombre,"
			+ " r.tipotrab, t.nombre, falla, descripcion, c.descripcioncausa"
			+ " from ordentrabajo o"
			+ " JOIN maquina m on o.idmaq =m.id"
			+ " JOIN submaquina s ON o.idsubmaq=s.id"
			+ " JOIN tecnico t on o.idtecn = t.idtecn"
			+ " JOIN area a ON m.area_id = a.id"
			+ " join tipotrabajo r on t.idtiptrab = r.idtiptrab"
			+ " JOIN causa c on o.idcausa = c.idcausa"
			+ " WHERE o.idordentrab=?1"
		,nativeQuery=true)
	public Optional<OrdenTExport>listadoOT(int idordentrab);
	
	//REPOSITORIO QUE BUSCA LAS OT PENDIENTES PARA EXPORTAR
	@Query(value=" SELECT idordentrab  "
			+ ",m.maquinanombre ,"
			+ " s.nombres ,"
			+ " descripcion ,"
			+ " c.descripcioncausa ,"
			+ " falla , "
			+ " d.idordencomp ,"
			+ " d.estadooc ,"
			+ " d.fechareq, fecharegistro "
			+ "FROM ordentrabajo o join maquina m on o.idmaq=m.id "
			+ "join submaquina s on o.idsubmaq = s.id "
			+ "join causa c on o.idcausa = c.idcausa "
			+ "join ordencompra d on o.idordencomp = d.idordencomp "
			+ "where estadoot='Pendiente' ORDER by fecharegistro ASC"
		,nativeQuery=true)
	public List<OrdenTPendientes>listadoPendnts();
	
	
	//REPOSITORIO QUE BUSCA LAS OT PENDIENTES Y FILTRADA POR AREA PARA EXPORTAR
	@Query(value=" SELECT idordentrab ,m.maquinanombre ,a.areanombre, s.nombres , descripcion , "
			+ " c.descripcioncausa , falla, d.idordencomp, d.estadooc, d.fechareq, fecharegistro FROM ordentrabajo "
			+ " o join maquina m on o.idmaq=m.id  join area a on a.id=m.area_id "
			+ " join submaquina s on o.idsubmaq = s.id  join causa c on o.idcausa = c.idcausa  "
			+ " join ordencompra d on o.idordencomp = d.idordencomp  where estadoot='Pendiente' "
			+ " and m.area_id=?1 ORDER by fecharegistro ASC"
		,nativeQuery=true)
	public List<OrdenTPendientes>listapendtsArea(int idarea);
	
	//REPOSITORIO QUE BUSCA LAS OT PENDIENTES Y FILTRADA POR MAQUINA PARA EXPORTAR
		@Query(value=" SELECT idordentrab  "
				+ ",m.maquinanombre ,o.idmaq,"
				+ " s.nombres , "
				+ " descripcion ,"
				+ " c.descripcioncausa ,"
				+ " falla , "
				+ " d.idordencomp ,"
				+ " d.estadooc ,"
				+ " d.fechareq, fecharegistro   "
				+ "FROM ordentrabajo o join maquina m on o.idmaq=m.id "
				+ "join submaquina s on o.idsubmaq = s.id "
				+ "join causa c on o.idcausa = c.idcausa "
				+ "join ordencompra d on o.idordencomp = d.idordencomp "
				+ "where estadoot='Pendiente' and o.idmaq=?1 ORDER by fecharegistro ASC"
			,nativeQuery=true)
	public List<OrdenTPendientes>listapendtsMaquina(int idmaq);
	
		//REPOSITORIO QUE BUSCA LAS OT PROGRAMADAS PARA EXPORTAR
		@Query(value=" SELECT idordentrab "
				+ ",m.maquinanombre,"
				+ " s.nombres , "
				+ " descripcion ,"
				+ "t.nombre ,"
				+ " p.tipotrab ,"
				+ " d.numsolcompra ,fecharegistro "
				+ "FROM ordentrabajo o join maquina m on o.idmaq=m.id "
				+ "join submaquina s on o.idsubmaq = s.id "
				+ "join tecnico t on o.idtecn = t.idtecn "
				+ "join tipotrabajo p on t.idtiptrab = p.idtiptrab "
				+ "join ordencompra d on o.idordencomp = d.idordencomp "
				+ "where estadoot='Programada' ORDER by fecharegistro  ASC"
			,nativeQuery=true)
		public List<OrdenTProgramadas>listadoprogra();
		
		
		//REPOSITORIO QUE BUSCA LAS OT PROGRAMADAS Y FILTRADA POR AREA PARA EXPORTAR
		@Query(value="SELECT idordentrab "
				+ " ,m.maquinanombre ,a.areanombre, s.nombres , descripcion , t.nombre , "
				+ " p.tipotrab ,d.numsolcompra, fecharegistro  FROM ordentrabajo o join maquina m on o.idmaq=m.id "
				+ " join submaquina s on o.idsubmaq = s.id join tecnico t on o.idtecn = t.idtecn "
				+ " join tipotrabajo p on t.idtiptrab = p.idtiptrab join area a on a.id=m.area_id join ordencompra d "
				+ " on o.idordencomp = d.idordencomp where estadoot='Programada' and m.area_id=?1 ORDER by fecharegistro ASC"
			,nativeQuery=true)
		public List<OrdenTProgramadas>listaprograArea(int idarea);
		
		//REPOSITORIO QUE BUSCA LAS OT PROGRAMADAS Y FILTRADA POR MAQUINA PARA EXPORTAR
		@Query(value=" SELECT idordentrab "
				+ ",m.maquinanombre ,o.idmaq,"
				+ " s.nombres , "
				+ " descripcion ,"
				+ "t.nombre ,"
				+ " p.tipotrab ,"
				+ " d.numsolcompra, fecharegistro "
				+ "FROM ordentrabajo o join maquina m on o.idmaq=m.id "
				+ "join submaquina s on o.idsubmaq = s.id "
				+ "join tecnico t on o.idtecn = t.idtecn "
				+ "join tipotrabajo p on t.idtiptrab = p.idtiptrab "
				+ "join ordencompra d on o.idordencomp = d.idordencomp "
				+ "where estadoot='Programada' and o.idmaq=?1 ORDER by fecharegistro ASC"
				,nativeQuery=true)
		public List<OrdenTProgramadas>listaprograMaquina(int idmaq);	
		
		
		
		
		
	//REPOSITORIO QUE CONSULTA A LA BASE LAS ORDENES DE TRABAJO POR SUBMAQUINA Y  ESTADO PENDIENTE
	@Query(value = " SELECT * FROM Ordentrabajo WHERE estadoot= 'pendiente' AND idarea= ?1 ", nativeQuery = true)
	public List<Ordentrabajo>listOTpendienteArea(int id);
	
	//REPOSITORIO QUE CONSULTA A LA BASE LAS ORDENES DE TRABAJO POR MAQUINA Y  ESTADO PENDIENTE
	@Query(value = " SELECT * FROM Ordentrabajo WHERE estadoot= 'pendiente' AND idmaq= ?1 ", nativeQuery = true)
	public List<Ordentrabajo>listOTpendienteMaquina(int id);
		
	
	//REPOSITORIO QUE CONSULTA A LA BASE LAS ORDENES DE TRABAJO CON ESTADO PROGRAMADA
	@Query(value = " SELECT * FROM Ordentrabajo WHERE estadoot='programada' AND idarea= ?1", nativeQuery = true)
	public List<Ordentrabajo>listOTprogramadaArea(int id);
	
	//REPOSITORIO QUE CONSULTA A LA BASE LAS ORDENES DE TRABAJO POR MAQUINA Y  ESTADO PROGRAMADA
	@Query(value = " SELECT * FROM Ordentrabajo WHERE estadoot= 'programada' AND idmaq= ?1 ", nativeQuery = true)
	public List<Ordentrabajo>listOTprogramadaMaquina(int id);
	
	//REPOSITORIO QUE CONSULTA A LA BASE SI EXISTE UN IDTECNICO EN LAS ORDENES DE TRABAJO PARA ELIMINAR TECNICO
	@Query(value = " SELECT * FROM Ordentrabajo WHERE idtecn=  ?1 ", nativeQuery = true)
	public List<Ordentrabajo>buscarporidtecnico(int id);
	
	//REPOSITORIO QUE CONSULTA A LA BASE SI EXISTE UN IDSUBMAQUINA EN LAS ORDENES DE TRABAJO PARA ELIMINAR SUBMAQUINA
	@Query(value = " SELECT * FROM Ordentrabajo WHERE idsubmaq=  ?1 ", nativeQuery = true)
	public List<Ordentrabajo>buscarporidsubmaq(int id);
	
	//REPOSITORIO QUE CONSULTA A LA BASE SI EXISTE UN IDMAQUINA EN LAS ORDENES DE TRABAJO PARA ELIMINAR MAQUINA
	@Query(value = " SELECT * FROM Ordentrabajo WHERE idmaq=  ?1 ", nativeQuery = true)
	public List<Ordentrabajo>buscarporidmaq(int id);
	
	//REPOSITORIO QUE CONSULTA A LA BASE SI EXISTE UN IDCAUSA EN LAS ORDENES DE TRABAJO PARA ELIMINAR CAUSA
	@Query(value = " SELECT * FROM Ordentrabajo WHERE idcausa=  ?1 ", nativeQuery = true)
	public List<Ordentrabajo>buscarporidcausa(int id);
	
	//REPOSITORIO QUE CONSULTA A LA BASE SI EXISTE UN IDORDENCOMPRA EN LAS ORDENES DE TRABAJO PARA ELIMINAR OC
	@Query(value = " SELECT * FROM Ordentrabajo WHERE idordencomp=  ?1 ", nativeQuery = true)
	public List<Ordentrabajo>buscarporidordencomp(int id);
}