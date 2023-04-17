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
import com.cartonesa.control.modelo.Area;
import com.cartonesa.control.modelo.Maquina;

@RequestMapping
@Controller
public class AreaControl {

	String message;
	
	// VARIABLE DE LA INTERFAZ DE SERVICIO DE AREA
	@Autowired
	private IAreaService servicearea;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE OT
	@Autowired
	private IMaquinaService servicemaq;

	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA AREA
	@GetMapping("/newarea")
	public String agregararea(Model model) {
		model.addAttribute("area", new Area());
		return "FormArea";
	}

	// METODO QUE GUARDA EL AREA NUEVA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/savearea")
	public String savearea(@Validated Area a, Model model) {

		String valor = a.getAreanombre();
		if (valor.trim().length()==0) {
			message="Fallo el registro, campo areanombre vacio.";
			return "redirect:/listarArea";
		}else {
		message="Registro completo";
		servicearea.save(a);
		return "redirect:/listarArea";
		}
	}

	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR EL AREA
	@GetMapping("/editararea/{id}")
	public String editararea(@PathVariable int id, Model model) {
		Optional<Area> area = servicearea.listarId(id);
		model.addAttribute("area", area);
		return "FormArea";
	}

	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR EL AREA
	@GetMapping("/eliminarArea/{id}")
	public String deletearea(Model model, @PathVariable int id) {
		List<Maquina> ot=servicemaq.buscarporidarea(id);
		
		if(ot.isEmpty()==true) {
			  message="Registro eliminado";
			  servicearea.delete(id);
				return "redirect:/listarArea";
		 }else { 	
			 message="Esta area esta usada en una Maquina";
			 return "redirect:/listarArea";
			}	
	}

	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODAS LAS AREAS
	@GetMapping("listarArea")
	public String areamenu(Model model) {
		List<Area> areas = servicearea.listar();
		model.addAttribute("mensaje", message);
		model.addAttribute("areas", areas);	
		message="";
		return "areamenu";
	}

	
	
	// METODO QUE BUSCA UN OBJETO POR NOMBRE Y REDIRECCIONA AL FORMULARIO DE LISTAR 
	@GetMapping("/findByAreanombreContaining/{clave}")
	public String buscarPorAreanombreContaining(Model model, @PathVariable String clave) {
		List<Area> areas = servicearea.findByAreanombreContaining(clave);
		if(areas.isEmpty()) {
			 message="No existe esa Area";
			 List<Area>area=servicearea.listar();
			 model.addAttribute("areas", area);
			 model.addAttribute("mensaje", message);
			 message="";
			 return "areamenu";
		 }else {
		
		model.addAttribute("areas", areas);
		return "areamenu";
		 }
	}
}
