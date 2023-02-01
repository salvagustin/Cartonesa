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
import com.cartonesa.control.interfaceService.ICausaService;
import com.cartonesa.control.modelo.Causa;

@RequestMapping
@Controller
public class CausaControl {
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE CAUSA
	@Autowired
	private ICausaService service;

	
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR LAS CAUSAS EXISTENTES
	@GetMapping("listarCausa")
    public String causamenu( Model model){
		List<Causa> causas = service.listar();
		model.addAttribute("causas", causas);
		return "causamenu";   
    }
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA CAUSA
	@GetMapping("/newcausa")
	public String agregarcausa(Model model) {
		model.addAttribute("causa", new Causa());
		return "aggcausa";
	}
		
	// METODO QUE GUARDA UNA NUEVA CAUSA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/savecausa")
	public String savecausa(@Valid Causa a, Model model) {
		service.save(a);
		return "redirect:/listarCausa";
	}
		
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR LA CAUSA
	@GetMapping("/editarcausa/{id}")
	public String editarcausa(@PathVariable int id, Model model) {
		Optional<Causa> causa= service.listarId(id);
		model.addAttribute("causa", causa);
		return "aggcausa";
	}
		
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR LA CAUSA
	@GetMapping("/eliminarCausa/{id}")
	public String deletecausa(Model model,@PathVariable int id) {
		service.delete(id);
		return "redirect:/listarCausa";
	}
	
	// METODO QUE BUSCA UN OBJETO POR FALLA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS CAUSAS
	@GetMapping("/findByFalla/{clave}")
	public String buscarPorFalla(Model model, @PathVariable String clave){
		List<Causa> causas = service.findByFalla(clave);
		model.addAttribute("causas", causas);
		return "causamenu";
	}
			
	// METODO QUE BUSCA UN OBJETO POR SOLUCION Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS CAUSAS
	@GetMapping("/findBySolucion/{clave}")
	public String buscarPorSolucion(Model model, @PathVariable String clave){
		List<Causa> causas = service.findBySolucion(clave);
		model.addAttribute("causas", causas);
		return "causamenu";
	}
			
}
