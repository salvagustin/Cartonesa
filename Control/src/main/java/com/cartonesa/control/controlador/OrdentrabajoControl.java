package com.cartonesa.control.controlador;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cartonesa.control.interfaceService.IAreaService;
import com.cartonesa.control.interfaceService.ICausaService;
import com.cartonesa.control.interfaceService.IMaquinaService;
import com.cartonesa.control.interfaceService.IOrdentrabajoService;
import com.cartonesa.control.interfaceService.ISubmaquinaService;
import com.cartonesa.control.interfaceService.ITecnicoService;
import com.cartonesa.control.interfaceService.ITipotrabajoService;
import com.cartonesa.control.modelo.Area;
import com.cartonesa.control.modelo.Causa;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordentrabajo;
import com.cartonesa.control.modelo.Submaquina;
import com.cartonesa.control.modelo.Tecnico;
import com.cartonesa.control.modelo.Tipotrabajo;


@RequestMapping
@Controller
public class OrdentrabajoControl {

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE ORDEN DE TRABAJO
	@Autowired
	private IOrdentrabajoService service;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE MAQUINA
	@Autowired
	private IMaquinaService servicemaq;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE SUBMAQUINA
	@Autowired
	private ISubmaquinaService servicesubmaq;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE TECNICO
	@Autowired
	private ITecnicoService servicetecnico;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE CAUSA
	@Autowired
	private ICausaService servicecausa;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE TIPO DE TRABAJO
	@Autowired
	private ITipotrabajoService servicetipotrab;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE AREA
	@Autowired
	private IAreaService servicearea;
	
	//VARIABLE PARA BUSCAR MAQUINAS POR AREA
	int idArea = 0;
	
	//VARIABLE PARA BUSCAR SUBMAQUINAS POR MAQUINA
	int idMaquina = 0;
	
	//VARIABLE PARA ASIGNAR EL ESTADO DE ORDEN DE TRABAJO
	String pendiente="Pendiente";
	String realizada="Realizado";
	String programada="Programada";
	String pxrepuesto="PXRepuesto";
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODAS LAS ORDENES DE TRABAJO
	@GetMapping("listarOrdentrabajo")
    public String ordentmenu(Model model) {
		List<Ordentrabajo> ordentrabajo = service.listar();
		long conteototal= service.getNumberOfOrdentrabajo();
		long conteopndt= service.countByEstadoot(pendiente);
		long conteorealizada= service.countByEstadoot(realizada);
		long conteoprogramada= service.countByEstadoot(programada);
		long conteopxrepuesto= service.countByEstadoot(pxrepuesto);
		model.addAttribute("conteototal", conteototal);
		model.addAttribute("conteopndnt", conteopndt);
		model.addAttribute("conteoreal", conteorealizada);
		model.addAttribute("conteoprog", conteoprogramada);
		model.addAttribute("conteopxrep", conteopxrepuesto);
		model.addAttribute("ordenest", ordentrabajo);
        return "ordentmenu";
    }
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE CERRAR ORDENE DE TRABAJO
		@GetMapping("cerrarOrden")
	    public String iracerrar(Model model) {

	        return "cierreOrden";
	    }
		
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA ORDEN DE TRABAJO
	@GetMapping("/newot")
	public String agregarordentpreventivo(Model model) {
		List<Area> areaList = servicearea.listar();
		List<Maquina> maqList = servicemaq.buscarPorIdArea(idArea);
		List<Submaquina> submaqList = servicesubmaq.buscarPorIdMaquina(idMaquina);
		List<Tecnico> tecnList = servicetecnico.listar();
		List<Causa> causaList = servicecausa.listar();
		List<Tipotrabajo> tipotrabList = servicetipotrab.listar();
		model.addAttribute("ordentrabajo", new Ordentrabajo());
		model.addAttribute("areas", areaList);
		model.addAttribute("maquinas", maqList);
		model.addAttribute("submaquinas", submaqList);
		model.addAttribute("tecnicos", tecnList);
		model.addAttribute("causas", causaList);
		model.addAttribute("tipostrabajo", tipotrabList);
		return "aggordent";
	}
		
	// METODO QUE REDIRECCIONA QUE GUARDA LA ORDEN DE TRABAJO NUEVA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/saveordent")
	public String saveordent(@Valid Ordentrabajo t, Model model) {
		service.save(t);
		return "redirect:/listarOrdentrabajo";
	}
	
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR LA ORDEN DE TRABAJO
	@GetMapping("/editarordent/{id}")
	public String editarordent(@PathVariable int id, Model model) {				
		Optional<Ordentrabajo> ordentrabajo= service.listarId(id);
		List<Area> areaList = servicearea.listar();
		List<Maquina> maqList = servicemaq.listar();
		List<Submaquina> submaqList = servicesubmaq.listar();
		List<Tecnico> tecnList = servicetecnico.listar();
		List<Causa> causaList = servicecausa.listar();
		List<Tipotrabajo> tipotrabList = servicetipotrab.listar();
		model.addAttribute("ordentrabajo", ordentrabajo);
		model.addAttribute("areas", areaList);
		model.addAttribute("maquinas", maqList);
		model.addAttribute("submaquinas", submaqList);
		model.addAttribute("tecnicos", tecnList);
		model.addAttribute("causas", causaList);
		model.addAttribute("tipostrabajo", tipotrabList);
		return "aggordent";
	}
	
	
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR LA ORDEN DE TRABAJO
	@GetMapping("/eliminarOrdentrabajo/{id}")
	public String deleteordent(Model model,@PathVariable int id) {
		service.delete(id);
		return "redirect:/listarOrdentrabajo";
	}
	
	// METODO QUE BUSCA UN OBJETO POR MAQUINA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE TRABAJO
	@GetMapping("/findByMaq/{clave}")
	public String buscarPorMaq(Model model, @PathVariable String clave){
		List<Maquina> maquinas = servicemaq.findByMaquinanombre(clave);
		List<Ordentrabajo> ordentrabajo = service.findByMaquina(maquinas.get(0));
		model.addAttribute("ordenest", ordentrabajo);
		return "ordentmenu";
	}
	
	// METODO QUE BUSCA UN OBJETO POR TECNICO Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE TRABAJO
	@GetMapping("/findByTecn/{clave}")
	public String buscarPorTecn(Model model, @PathVariable String clave){
		List<Tecnico> tecnicos = servicetecnico.findByNombre(clave);
		List<Ordentrabajo> ordentrabajo = service.findByTecnico(tecnicos.get(0));
		model.addAttribute("ordenest", ordentrabajo);
		return "ordentmenu";
	}
	
	// METODO QUE BUSCA UN OBJETO POR CAUSA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE TRABAJO
	@GetMapping("/findByCausa/{clave}")
	public String buscarPorCausa(Model model, @PathVariable String clave){
		List<Causa> causas = servicecausa.findByFalla(clave);
		List<Ordentrabajo> ordentrabajo = service.findByCausa(causas.get(0));
		model.addAttribute("ordenest", ordentrabajo);
		return "ordentmenu";
	}
	
	// METODO QUE BUSCA UN OBJETO POR ESTADO OT Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE TRABAJO
	@GetMapping("/findByEstadoot/{clave}")
	public String buscarPorEstadoot(Model model, @PathVariable String clave){
		List<Ordentrabajo> ordentrabajo = service.findByEstadoot(clave);
		model.addAttribute("ordenest", ordentrabajo);
		return "ordentmenu";
	}
	
	// METODO QUE BUSCA UN OBJETO POR CORRELATIVO
	@GetMapping("/findByIdordentrab/{clave}")
	public String buscarPorIdordentrab(Model model, @PathVariable int clave){
		List<Ordentrabajo> ordentrabajo = service.findByIdordentrab(clave);
		model.addAttribute("ordenacerrar", ordentrabajo);
		return "cierreOrden";
	}
	
	// METODO QUE BUSCA UN OBJETO POR TIPO ORDEN Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE TRABAJO
	@GetMapping("/findByTipoorden/{clave}")
	public String buscarPorTipoorden(Model model, @PathVariable String clave){
		List<Ordentrabajo> ordentrabajo = service.findByTipoorden(clave);
		model.addAttribute("ordenest", ordentrabajo);
		return "ordentmenu";
	}
	
	// METODO PARA BUSCAR MAQUINAS POR AREA SELECCIONADA Y SUBMAQUINAS POR MAQUINA SELECCIONADA
	// OBTIENE LA LISTA DE AREAS
	@ModelAttribute("areas")
	public List<Area> getAreas() {
		return servicearea.listar();
	}

	// FILTRA LAS MAQUINAS POR EL AREA SELECCIONADA
	@GetMapping(value = "/buscarPorArea")
	public @ResponseBody List<Maquina> todasMaquinasPorArea(
			@RequestParam(value = "idArea", required = true) int idArea) {
		return servicemaq.buscarPorIdArea(idArea);
	}

	// FILTTA LAS SUBMAQUINAS POR LA SUBMAQUINA SELECCIONADA
	@GetMapping(value = "/buscarPorMaquina")
	public @ResponseBody List<Submaquina> todasSubmaquinasPorMaquina(
			@RequestParam(value = "idMaquina", required = true) int idMaquina) {
		return servicesubmaq.buscarPorIdMaquina(idMaquina);
	}
	
	
	
	
	
	
	
}
