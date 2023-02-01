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
import com.cartonesa.control.interfaceService.IUnidadmedidaService;
import com.cartonesa.control.modelo.Unidadmedida;

@RequestMapping
@Controller
public class UnidadmedidaControl {

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE UNIDAD DE MEDIDA
	@Autowired
	private IUnidadmedidaService service;
	
	
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA AREA
	@GetMapping("/newunidad")
	public String agregarunidad(Model model) {
		model.addAttribute("unidad", new Unidadmedida());
		return "aggunidad";
	}
	
	// METODO QUE GUARDA LA UNIDAD NUEVA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/saveunidad")
	public String saveunidad(@Valid Unidadmedida d, Model model) {
		service.save(d);
		return "redirect:/listarUnidad";
	}
	
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR LA UNIDAD
	@GetMapping("/editarunidad/{id}")
	public String editarunidad(@PathVariable int id, Model model) {
		Optional<Unidadmedida>unidad=service.listarId(id);
		model.addAttribute("unidad", unidad);
		return "aggunidad";
	}
	
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR LA UNIDAD
	@GetMapping("/eliminarUnidad/{id}")
	public String deleteunidad(Model model,@PathVariable int id) {
		service.delete(id);
		return "redirect:/listarUnidad";
	}
		
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODAS LAS UNIDADES DE MEDIDA
	@GetMapping("listarUnidad")
	public String unidadmenu(Model model) {
		List<Unidadmedida>unidades=service.listar();
		model.addAttribute("unidades", unidades);
		return "unidadmenu";		
	}
			 
	// METODO QUE BUSCA UN OBJETO POR ID Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS UNIDADES
	@GetMapping("/findByIdunidad/{clave}")
	public String buscarPorIdunidad(Model model, @PathVariable int clave){
		List<Unidadmedida> unidades = service.findByIdunidad(clave);
		model.addAttribute("unidades", unidades);
		return "unidadmenu";					
	}
	
	// METODO QUE BUSCA UN OBJETO POR UNIDAD Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS UNIDADES
	@GetMapping("/findByUnidad/{clave}")
	public String buscarPorUnidad(Model model, @PathVariable String clave){
		List<Unidadmedida> unidades = service.findByUnidad(clave);
		model.addAttribute("unidades", unidades);
		return "unidadmenu";					
	}
}