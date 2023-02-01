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
import com.cartonesa.control.interfaceService.IMaquinaService;
import com.cartonesa.control.modelo.Area;
import com.cartonesa.control.modelo.Maquina;

@RequestMapping
@Controller
public class MaquinaControl {

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE MAQUINA
	@Autowired
	private IMaquinaService service;

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE AREA
	@Autowired
	private IAreaService servicearea;



	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODAS LAS MAQUINAS
	@GetMapping("listarMaquina")
	public String maquinamenu(Model model) {
		List<Maquina> maquinas = service.listar();
		model.addAttribute("maquinas", maquinas);		
		return "maquinamenu";
	}
	
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA MAQUINA
	@GetMapping("/newmaquina")
	public String agregarmaquina(Model model) {
		List<Area> areaList = servicearea.listar();
		model.addAttribute("maquina", new Maquina());
		model.addAttribute("areas", areaList);
		return "aggmaquina";
	}

	// METODO QUE GUARDA UNA NUEVA MAQUINA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/savemaquina")
	public String savemaquina(@Valid Maquina m, Model model) {
		service.save(m);
		return "redirect:/listarMaquina";
	}

	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR LA MAQUINA
	@GetMapping("/editarmaquina/{idmaq}")
	public String editarmaquina(@PathVariable int idmaq, Model model) {
		Optional<Maquina> maquina = service.listarId(idmaq);
		List<Area> areaList = servicearea.listar();
		model.addAttribute("maquina", maquina);
		model.addAttribute("areas", areaList);
		return "aggmaquina";
	}

	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR LA MAQUINA
	@GetMapping("/eliminarMaquina/{id}")
	public String deletemaquina(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/listarMaquina";
	}

	// METODO QUE BUSCA UN OBJETO POR NOMBRE Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS MAQUINAS
	@GetMapping("/findByMaquinanombre/{clave}")
	public String buscarPorNombre(Model model, @PathVariable String clave){
		List<Maquina> maquinas = service.findByMaquinanombre(clave);
		model.addAttribute("maquinas", maquinas);
		return "maquinamenu";		
	}
	
	// METODO QUE BUSCA UN OBJETO POR NOMBRE DE AREA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS MAQUINAS
	@GetMapping("/findByArea/{clave}")
	public String buscarPorArea(Model model, @PathVariable String clave){
		List<Area> areas = servicearea.findByAreanombre(clave);
		List<Maquina> maquinas = service.findByArea(areas.get(0));
		model.addAttribute("maquinas", maquinas);
		return "maquinamenu";		
	}

}
