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
import com.cartonesa.control.interfaceService.ITecnicoServ;
import com.cartonesa.control.interfaceService.ITipotrabajoService;
import com.cartonesa.control.modelo.Tecnico;
import com.cartonesa.control.modelo.Tipotrabajo;

@RequestMapping
@Controller
public class TipotrabajoControl {
	
	String message;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE TIPO DE TRABAJO
	@Autowired
	private ITipotrabajoService service;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE OT
	@Autowired
	private ITecnicoServ servicetecn;
		
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODOS LOS TIPOS DE TRABAJO
	@GetMapping("listarTipotrabajo")
	public String tipotrabajomenu(Model model) {
		List<Tipotrabajo> tipostrabajo=service.listar();
		model.addAttribute("mensaje", message);
		model.addAttribute("tipostrabajo", tipostrabajo);
		message="";
	    return "tipotrabajomenu";   
    }
	 
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVO TIPO DE TRABAJO
	@GetMapping("/newtipotrabajo")
	public String agregartipotrabajo(Model model) {
		model.addAttribute("tipotrabajo", new Tipotrabajo());
		return "FormTipotrabajo";
	}
	
	// METODO QUE GUARDA EL TIPO DE TRABAJO NUEVO Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/savetipotrabajo")
	public String savetipotrabajo(@Validated Tipotrabajo t, Model model) {
		String valor = t.getTipotrab();
		
		if (valor.trim().length()==0) {
			message="Fallo el registro, campo tipo trabajo vacio.";
			return "redirect:/listarTipotrabajo";
		}else {
			message="Registro completo";
			service.save(t);
			return "redirect:/listarTipotrabajo";
		}	
		
		
	}
		
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR EL TIPO DE TRABAJO
	@GetMapping("/editartipotrabajo/{idtiptrab}")
	public String editararea(@PathVariable int idtiptrab, Model model) {
		Optional<Tipotrabajo> tipotrabajo=service.listarId(idtiptrab);
		model.addAttribute("tipotrabajo", tipotrabajo);
		return "FormTipotrabajo";
	}
		
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR EL TIPO DE TRABAJO
	@GetMapping("/eliminarTipotrabajo/{id}")
	public String deletearea(Model model,@PathVariable int id) {
		List<Tecnico> ot=servicetecn.buscarporidtipotrabajo(id);
		
		if(ot.isEmpty()==true) {
			  message="Registro eliminado";
			 	service.delete(id);
				return "redirect:/listarTipotrabajo";
		 }else { 	
			 message="Este tipo de trabajo esta usado en un registro Tecnico";
			 return "redirect:/listarTipotrabajo";
		}
	}
	
	// METODO QUE BUSCA POR TIPO DE TRABAJO Y REDIRECCIONA AL FORMULARIO DE LISTAR LOS TIPOS DE TRABAJO
	@GetMapping("/findByTipotrabajo/{clave}")
	public String buscarPorTipotrabajo(Model model,@PathVariable String clave){
		List<Tipotrabajo> tipostrabajo = service.findByTipotrab(clave);
		if(tipostrabajo.isEmpty()) {
			message = "No existe ese Tipo de trabajo";
			List<Tipotrabajo> tipotrab= service.listar();
			model.addAttribute("tipostrabajo", tipotrab);
			model.addAttribute("mensaje", message);
			message="";
			return "tipotrabajomenu";
		}else {
		model.addAttribute("tipostrabajo", tipostrabajo);
		return "tipotrabajomenu";
		}
	}
	
	// METODO QUE BUSCA POR ID Y REDIRECCIONA AL FORMULARIO DE LISTAR LOS TIPOS DE TRABAJO
	@GetMapping("/findByIdtipotrabajo/{clave}")
	public String buscarPorTiIdtipotrabajo(Model model,@PathVariable String clave){
		boolean isNumeric = (clave != null && clave.matches("[0-9]+"));
		
		if(isNumeric==true) {
		int id =Integer.parseInt(clave);
		List<Tipotrabajo> tipostrabajo = service.findByIdiptrab(id);
		if(tipostrabajo.isEmpty()) {
			message = "No existe ese Id";
			List<Tipotrabajo> tipotrab= service.listar();
			model.addAttribute("tipostrabajo", tipotrab);
			model.addAttribute("mensaje", message);
			message="";
			return "tipotrabajomenu";
		}else {
		model.addAttribute("tipostrabajo", tipostrabajo);
		return "tipotrabajomenu";
		}}else {
			message = "No ingreso un número";
			List<Tipotrabajo> tipotrab= service.listar();
			model.addAttribute("tipostrabajo", tipotrab);
			model.addAttribute("mensaje", message);
			message="";
			return 	"tipotrabajomenu";
		}
	}
}
