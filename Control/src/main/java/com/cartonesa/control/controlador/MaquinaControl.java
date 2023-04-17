package com.cartonesa.control.controlador;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cartonesa.control.interfaceService.IAreaService;
import com.cartonesa.control.interfaceService.IMaquinaService;
import com.cartonesa.control.interfaceService.IOrdencompraService;
import com.cartonesa.control.interfaceService.IOrdentrabajoService;
import com.cartonesa.control.interfaceService.ISubmaquinaService;
import com.cartonesa.control.modelo.Area;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordencompra;
import com.cartonesa.control.modelo.Ordentrabajo;
import com.cartonesa.control.modelo.Submaquina;

@RequestMapping
@Controller
public class MaquinaControl {
	String message;
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE MAQUINA
	@Autowired
	private IMaquinaService service;

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE MAQUINA
	@Autowired
	private ISubmaquinaService servicesub; 
		
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE AREA
	@Autowired
	private IAreaService servicearea;

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE OT
	@Autowired
	private IOrdentrabajoService serviceot;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE OC
	@Autowired
	private IOrdencompraService serviceoc;

	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODAS LAS MAQUINAS
	@GetMapping("listarMaquina")
	public String maquinamenu(Model model) {
		List<Maquina> maquinas = service.listar();
		model.addAttribute("mensaje", message);
		model.addAttribute("maquinas", maquinas);		
		message="";
		return "maquinamenu";
	}
	
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA MAQUINA
	@GetMapping("/newmaquina")
	public String agregarmaquina(Model model) {
		List<Area> areaList = servicearea.listar();
		model.addAttribute("maquina", new Maquina());
		model.addAttribute("areas", areaList);
		return "FormMaquina";
	}

	// METODO QUE GUARDA UNA NUEVA MAQUINA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/savemaquina")
	public String savemaquina(@Validated Maquina m, Model model) {
		String valor = m.getMaquinanombre();
		
		if (valor.trim().length()==0) {
			message="Fallo el registro, campo maquina nombre vacio.";
			
			return "redirect:/listarMaquina";
		}else {
			message="Registro completo";
			service.save(m);
			return "redirect:/listarMaquina";
		}
	}

	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR LA MAQUINA
	@GetMapping("/editarmaquina/{idmaq}")
	public String editarmaquina(@PathVariable int idmaq, Model model) {
		Optional<Maquina> maquina = service.listarId(idmaq);
		List<Area> areaList = servicearea.listar();
		model.addAttribute("maquina", maquina);
		model.addAttribute("areas", areaList);
		return "FormMaquina";
	}

	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR LA MAQUINA
	@GetMapping("/eliminarMaquina/{id}")
	public String deletemaquina(Model model, @PathVariable int id) {
		List<Ordentrabajo> ot=serviceot.buscarporidmaq(id);
		List<Ordencompra> oc=serviceoc.buscarporidmaq(id);
		List<Submaquina> maq=servicesub.buscarporidmaq(id);
		if(ot.isEmpty()==true) {
					
			if(oc.isEmpty()==true) {
						
				if(maq.isEmpty()==true) {
						message="Registro eliminado";
						  service.delete(id);
							return "redirect:/listarMaquina";
				}else {
					message="Esta maquina esta usada en un registro Submaquina";
					 return "redirect:/listarMaquina";
				}
				}else {
						message="Esta maquina esta usada en un registro OC";
						 return "redirect:/listarMaquina";
					}	
		 }else { 	
			 message="Esta maquina esta usada en un registro OT";
			 return "redirect:/listarMaquina";
			}
	}

	// METODO QUE BUSCA UN OBJETO POR NOMBRE Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS MAQUINAS
	@GetMapping("/findByMaquinanombreContaining/{clave}")
	public String buscarPorNombreContaining(Model model, @PathVariable String clave){
		List<Maquina> maquinas = service.findByMaquinanombre(clave);
		
		if(maquinas.isEmpty()) {
			message = "No existe la Maquina";
			List<Maquina> maquinas1= service.listar();
			model.addAttribute("maquinas", maquinas1);
			model.addAttribute("mensaje", message);
			message="";
			return "maquinamenu";
		}else {
			model.addAttribute("maquinas", maquinas);
		return "maquinamenu";	
		}
			
	}
	
	// METODO QUE BUSCA UN OBJETO POR AREA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS MAQUINAS
	@GetMapping("/findByArea/{clave}")
		public String buscarPorArea(Model model, @PathVariable String clave) {
		List<Area> areas = servicearea.findByAreanombre(clave);
	
		if(areas.isEmpty()) {
			message = "No existe el Area";
			List<Maquina> maquinas= service.listar();
			model.addAttribute("maquinas", maquinas);
			model.addAttribute("mensaje", message);
			message="";
		return "maquinamenu";
		}else {
			List<Maquina> maquinas = service.findByArea(areas.get(0));
			model.addAttribute("maquinas", maquinas);
		}
		return "maquinamenu";
	}
	
	
	

}
