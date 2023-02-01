package com.cartonesa.control.controlador;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cartonesa.control.interfaceService.ITipotrabajoService;
import com.cartonesa.control.modelo.Tipotrabajo;

@RequestMapping
@Controller
public class TipotrabajoControl {
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE TIPO DE TRABAJO
	@Autowired
	private ITipotrabajoService service;
	
	
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODOS LOS TIPOS DE TRABAJO
	@GetMapping("listarTipotrabajo")
	public String tipotrabajomenu(Model model) {
		List<Tipotrabajo> tipostrabajo=service.listar();
		model.addAttribute("tipostrabajo", tipostrabajo);
	    return "tipotrabajomenu";   
    }
	 
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVO TIPO DE TRABAJO
	@GetMapping("/newtipotrabajo")
	public String agregartipotrabajo(Model model) {
		model.addAttribute("tipotrabajo", new Tipotrabajo());
		return "aggtipotrabajo";
	}
	
	// METODO QUE GUARDA EL TIPO DE TRABAJO NUEVO Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/savetipotrabajo")
	public String savetipotrabajo(@Valid Tipotrabajo t, Model model) {
		service.save(t);
		return "redirect:/listarTipotrabajo";
	}
		
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR EL TIPO DE TRABAJO
	@GetMapping("/editartipotrabajo/{idtiptrab}")
	public String editararea(@PathVariable int idtiptrab, Model model) {
		Optional<Tipotrabajo> tipotrabajo=service.listarId(idtiptrab);
		model.addAttribute("tipotrabajo", tipotrabajo);
		return "aggtipotrabajo";
	}
		
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR EL TIPO DE TRABAJO
	@GetMapping("/eliminarTipotrabajo/{id}")
	public String deletearea(Model model,@PathVariable int id) {
		service.delete(id);
		return "redirect:/listarTipotrabajo";
	}
	
	// METODO QUE BUSCA UN OBJETO POR TIPO DE TRABAJO Y REDIRECCIONA AL FORMULARIO DE LISTAR LOS TIPOS DE TRABAJO
	@GetMapping("/findByTipotrabajo/{clave}")
	public String buscarPorTipotrabajo(Model model,@PathVariable String clave){
		List<Tipotrabajo> tipostrabajo = service.findByTipotrab(clave);
		model.addAttribute("tipostrabajo", tipostrabajo);
		return "tipotrabajomenu";
	}
	
	// METODO QUE BUSCA UN OBJETO POR ID Y REDIRECCIONA AL FORMULARIO DE LISTAR LOS TIPOS DE TRABAJO
	@GetMapping("/findByIdtipotrabajo/{clave}")
	public String buscarPorTiIdtipotrabajo(Model model,@PathVariable int clave){
		List<Tipotrabajo> tipostrabajo = service.findByIdiptrab(clave);
		model.addAttribute("tipostrabajo", tipostrabajo);
		return "tipotrabajomenu";
	}
}
