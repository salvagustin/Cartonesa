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
import com.cartonesa.control.interfaceService.IAreaService;
import com.cartonesa.control.modelo.Area;

@RequestMapping
@Controller
public class AreaControl {

	// VARIABLE DE LA INTERFAZ DE SERVICIO DE AREA
	@Autowired
	private IAreaService servicearea;

	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA AREA
	@GetMapping("/newarea")
	public String agregararea(Model model) {
		model.addAttribute("area", new Area());
		return "aggarea";
	}

	// METODO QUE GUARDA EL AREA NUEVA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/savearea")
	public String savearea(@Valid Area a, Model model) {
		servicearea.save(a);
		return "redirect:/listarArea";
	}

	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR EL
	// AREA
	@GetMapping("/editararea/{id}")
	public String editararea(@PathVariable int id, Model model) {
		Optional<Area> area = servicearea.listarId(id);
		model.addAttribute("area", area);
		return "aggarea";
	}

	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR
	// EL AREA
	@GetMapping("/eliminarArea/{id}")
	public String deletearea(Model model, @PathVariable int id) {
		servicearea.delete(id);
		return "redirect:/listarArea";
	}

	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODAS LAS AREAS
	@GetMapping("listarArea")
	public String areamenu(Model model) {
		List<Area> areas = servicearea.listar();
		model.addAttribute("areas", areas);
		return "areamenu";
	}

	// METODO QUE BUSCA UN OBJETO POR NOMBRE Y REDIRECCIONA AL FORMULARIO DE LISTAR
	// EL AREA
	@GetMapping("/findByAreanombre/{clave}")
	public String buscarPorAreanombre(Model model, @PathVariable String clave) {
		List<Area> areas = servicearea.findByAreanombre(clave);
		model.addAttribute("areas", areas);
		return "areamenu";

	}

}
