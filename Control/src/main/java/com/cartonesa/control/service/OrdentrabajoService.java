package com.cartonesa.control.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cartonesa.control.interfaceService.IOrdentrabajoService;
import com.cartonesa.control.interfaces.IOrdentrabajo;
import com.cartonesa.control.modelo.Causa;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordentrabajo;
import com.cartonesa.control.modelo.Tecnico;

@Service
public class OrdentrabajoService implements IOrdentrabajoService{

	//VARIABLE DE LA INTERFAZ DE AREA
	@Autowired
	private IOrdentrabajo data;

	
	Ordentrabajo ordentrabajo;
	
	//SERVICIO QUE LISTA TODAS LAS ORDENES DE TRABAJO
	@Override
	public List<Ordentrabajo>  listar() {
		return (List<Ordentrabajo>)data.findByOrderByIdordentrabDesc();
	}

	
	//SERVICIO QUE BUSCA UNA ORDEN DE TRABAJO POR ID
	@Override	
	public Optional<Ordentrabajo> listarId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}

	//SERVICIO PARA GUARDAR UN NUEVO REGISTRO DE ORDEN DE TRABAJO
	@Override
	public int save(Ordentrabajo o) {
		
        Calendar fecha = new GregorianCalendar();         
        Timestamp timestamp = new Timestamp(fecha.getTimeInMillis());
		o.setFecharegistro(timestamp);
		int res=0;
			Ordentrabajo ordentrabajo=data.save(o);
		if(!ordentrabajo.equals(null)) {
			res=1;
		}
		return res;
	}

	
	//SERVICIO QUE OBTIENE EL ID DE LA ORDEN Y EL NUEVO PARAMETRO PARA ACTUALIZA EL ESTADO OT
	@Override
	public int update(int idorden, String newestado,Ordentrabajo o) {
		// TODO Auto-generated method stub	
		int res=0;
		o = data.findById(idorden).get();
		o.setEstadoot(newestado);
		Ordentrabajo orden=data.save(o);
		if(!orden.equals(null)) {
		res=1;
		}
		return res;
	}
	
	//SERVICIO PARA ELIMINAR UNA ORDEN SELECCIONADA
	@Override
	public void delete(int id) {
		data.deleteById(id);		
	}

	//SERVICIO QUE BUSCA REGISTROS POR MAQUINA
	@Override
	public List<Ordentrabajo> findByMaquina(Maquina maquina) {
		// TODO Auto-generated method stub
		return data.findByMaquinaOrderByIdordentrabDesc(maquina);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR TECNICO
	@Override
	public List<Ordentrabajo> findByTecnico(Tecnico tecnico) {
		// TODO Auto-generated method stub
		return data.findByTecnicoOrderByIdordentrabDesc(tecnico);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR CAUSA
	@Override
	public List<Ordentrabajo> findByCausa(Causa causa) {
		// TODO Auto-generated method stub
		return data.findByCausaOrderByIdordentrabDesc(causa);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR ESTADO OT
	@Override
	public List<Ordentrabajo> findByEstadoot(String estadoot) {
		// TODO Auto-generated method stub
		return data.findByEstadootContainingOrderByIdordentrabDesc(estadoot);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR CORRELATIVO
	@Override
	public List<Ordentrabajo> findByIdordentrab(int idordentrab) {
		// TODO Auto-generated method stub
		return data.findByIdordentrabOrderByIdordentrabDesc(idordentrab);
	}

	//SERVICIO QUE BUSCAR REGISTROS POR TIPO ORDEN
	@Override
	public List<Ordentrabajo> findByTipoorden(String tipoorden) {
		// TODO Auto-generated method stub
		return data.findByTipoordenOrderByIdordentrabDesc(tipoorden);
	}
	
	//SERVICIO QUE BUSCAR REGISTROS POR FALLA DE LA OT
	@Override
	public List<Ordentrabajo> findByFallaContaining(String falla) {
		// TODO Auto-generated method stub
		return data.findByFallaContainingOrderByIdordentrabDesc(falla);
	}
	
	//SERVICIO QUE CUENTA REGISTROS POR ESTADO DE ORDEN DE TRABAJO
	@Override
	public long countByEstadoot(String estado) {
		// TODO Auto-generated method stub
		return data.countByEstadoot(estado);		
	}

	//SERVICIO QUE CUENTA TODOS LOS REGISTROS DE ORDEN DE TRABAJO
	@Override
	public long getNumberOfOrdentrabajo() {
		// TODO Auto-generated method stub
		return data.count();
	}

	
	//SERVICIO QUE LISTA LAS OT PENDIENTES
	@Override
	public List<Ordentrabajo> listOTpendiente(String estadoot) {
		// TODO Auto-generated method stub
		return data.findByEstadootContainingOrderByIdordentrabDesc(estadoot);
	}

	
	//SERVICIO QUE LISTA LAS OT PROGRAMADAS
	@Override
	public List<Ordentrabajo> listOTprogramadas(String estadoot) {
		// TODO Auto-generated method stub
		return data.findByEstadootContainingOrderByIdordentrabDesc(estadoot);
	}

	//SERVICIO QUE CUENTA EL REGISTRO DE OT DIARIA
	@Override
	public int conteodiarioOT(String estadoot) {
		// TODO Auto-generated method stub
		return data.conteodiarioOT(estadoot);
	}

	//SERVICIO QUE CUENTA LOS REGISTROS DE OT POR TECNICO
	@Override
	public List<ConteoTecnico> conteotecnico() {
		// TODO Auto-generated method stub	
		return data.conteotecnico();
	}

	//SERVICIO QUE CUENTA LOS REGISTROS DE OT POR AREA
	@Override
	public List<ConteoArea> conteoarea() {
		// TODO Auto-generated method stub
		return data.conteoarea();
	}

	//SERVICIO QUE CUENTA LOS REGISTROS DE OT POR MAQUINA
	@Override
	public List<ConteoMaquina> conteomaquina() {
		// TODO Auto-generated method stub
		return data.conteomaquina();
	}

	//SERVICIO QUE CUENTA LOS REGISTROS DE OT POR TIPO TRABAJO
		@Override
		public List<ConteoTipotrabajo> conteotipotrabajo() {
			// TODO Auto-generated method stub
			return data.conteotipotrabajo();
		}


		//SERVICIO PARA BUSCAR REGISTROS DE OT POR IDTECNICO PARA ELIMINAR TECNICOS
		@Override
		public List<Ordentrabajo> buscarporidtecnico(int id) {
			// TODO Auto-generated method stub
			return data.buscarporidtecnico(id);
		}


		//SERVICIO PARA BUSCAR REGISTROS DE OT POR IDSUBMAQ PARA ELIMINAR SUBMAQUINA
		@Override
		public List<Ordentrabajo> buscarporidsubmaq(int id) {
			// TODO Auto-generated method stub
			return data.buscarporidsubmaq(id);
		}

		//SERVICIO PARA BUSCAR REGISTROS DE OT POR IDMAQUINA PARA ELIMINAR MAQUINA
		@Override
		public List<Ordentrabajo> buscarporidmaq(int id) {
			// TODO Auto-generated method stub
			return data.buscarporidmaq(id);
		}

		//SERVICIO PARA BUSCAR REGISTROS DE OT POR IDCAUSA PARA ELIMINAR CAUSA
		@Override
		public List<Ordentrabajo> buscarporidcausa(int id) {
			// TODO Auto-generated method stub
			return data.buscarporidcausa(id);
		}

		//SERVICIO PARA BUSCAR REGISTROS DE OT POR IDORDENCOMPRA PARA ELIMINAR OC
		@Override
		public List<Ordentrabajo> buscarporidordencomp(int id) {
			// TODO Auto-generated method stub
			return data.buscarporidordencomp(id);
		}

		
		//SERVICIO PARA BUSCAR OT PENDIENTES PARA EXPORTAR
		@Override
		public List<OrdenTPendientes> listapendts() {
			// TODO Auto-generated method stub
			return data.listadoPendnts();
		}

		//SERVICIO PARA BUSCAR OT PENDIENTES FILTRADAS POR AREA PARA EXPORTAR
		@Override
		public List<OrdenTPendientes> listapendtsArea(int idarea) {
			// TODO Auto-generated method stub
			return data.listapendtsArea(idarea);
		}
		
		//SERVICIO PARA BUSCAR OT PENDIENTES FILTRADAS POR MAQUINA PARA EXPORTAR
		@Override
		public List<OrdenTPendientes> listapendtsMaquina(int idmaq) {
			// TODO Auto-generated method stub
			return data.listapendtsMaquina(idmaq);
		}
		

		//SERVICIO PARA BUSCAR OT PROGRAMADAS PARA EXPORTAR
		@Override
		public List<OrdenTProgramadas> listaprogra() {
			// TODO Auto-generated method stub
			return data.listadoprogra();
		}

		//SERVICIO PARA BUSCAR OT PROGRAMADAS FILTRADAS POR AREA PARA EXPORTAR
		@Override
		public List<OrdenTProgramadas> listaprograArea(int idarea) {
			// TODO Auto-generated method stub
			return data.listaprograArea(idarea);
		}

		//SERVICIO PARA BUSCAR OT PROGRAMADAS FILTRADAS POR MAQUINA PARA EXPORTAR
		@Override
		public List<OrdenTProgramadas> listaprograMaquina(int idmaq) {
			// TODO Auto-generated method stub
			return data.listaprograMaquina(idmaq);
		}

		//SERVICIO PARA BUSCAR OT POR ID PARA EXPORTAR
		@Override
		public Optional<OrdenTExport> listadoOT(int idordentrab) {
			// TODO Auto-generated method stub
			return data.listadoOT(idordentrab);
		}
}
