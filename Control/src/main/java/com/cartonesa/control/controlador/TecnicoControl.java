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
import com.cartonesa.control.interfaceService.ITecnicoService;
import com.cartonesa.control.interfaceService.ITipotrabajoService;
import com.cartonesa.control.modelo.Tecnico;
import com.cartonesa.control.modelo.Tipotrabajo;

@RequestMapping
@Controller
public class TecnicoControl {

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE TECNICO
	private ITecnicoService service;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE TIPO DE TRABAJO
	@Autowired
	private ITipotrabajoService servicetipotra;

	
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODOS LOS TECNICOS
	@GetMapping("listarTecnico")
    public String tecnicomenu(Model model){
		List<Tecnico> tecnList = service.listar();
		model.addAttribute("tecnicos", tecnList);
        return "tecnicomenu";
      
    }
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVO TECNICO
	@GetMapping("/newtecnico")
	public String agregartecnico(Model model) {
		List<Tipotrabajo> tipoList = servicetipotra.listar();
		model.addAttribute("tecnico", new Tecnico());
		model.addAttribute("tipostrabajo", tipoList);
		return "aggtecnico";
	}
	
	// METODO QUE GUARDA EL TECNICO NUEVO Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/savetecn")
	public String savetecnico(@Valid Tecnico t, Model model) {
		service.save(t);
		return "redirect:/listarTecnico";
	}
	
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR EL TECNICO
	@GetMapping("/editartecnico/{idtecn}")
	public String editartecnico(@PathVariable int idtecn, Model model) {
		Optional<Tecnico> tecnico= service.listarId(idtecn);
		List<Tipotrabajo> tipoList = servicetipotra.listar();
		model.addAttribute("tecnico", tecnico);
		model.addAttribute("tipostrabajo", tipoList);
		return "aggtecnico";
	}
	
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR EL TECNICO
	@GetMapping("/eliminarTecnico/{id}")
	public String deletetecnico(Model model,@PathVariable int id) {
		service.delete(id);
		return "redirect:/listarTecnico";
	}
	
	// METODO QUE BUSCA UN OBJETO POR NOMBRE Y REDIRECCIONA AL FORMULARIO DE LISTAR LOS TECNICOS
	@GetMapping("/findByNombre/{clave}")
	public String buscarPorNombre(Model model, @PathVariable String clave){
		List<Tecnico> tecnicos = service.findByNombre(clave);
		model.addAttribute("tecnicos", tecnicos);
		return "tecnicomenu";
		
	}
	
	// METODO QUE BUSCA UN OBJETO POR CODIGO TRABAJO Y REDIRECCIONA AL FORMULARIO DE LISTAR LOS TECNICOS
	@GetMapping("/findByCodigotrab/{clave}")
	public String buscarPorCodigotrab(Model model, @PathVariable String clave){
		List<Tecnico> tecnicos = service.findByCodigotrab(clave);
		model.addAttribute("tecnicos", tecnicos);
		return "tecnicomenu";		
	}
	
	// METODO QUE BUSCA UN OBJETO POR TIPO DE TRABAJO Y REDIRECCIONA AL FORMULARIO DE LISTAR LOS TECNICOS
	@GetMapping("/findByTipotrab/{clave}")
	public String buscarPorTipotrab(Model model, @PathVariable String clave){
		List<Tipotrabajo> tipostrabajo = servicetipotra.findByTipotrab(clave);
		List<Tecnico> tecnicos = service.findByTipotrabajo(tipostrabajo.get(0));
		model.addAttribute("tecnicos", tecnicos);
		return "tecnicomenu";		
	}
	
}
