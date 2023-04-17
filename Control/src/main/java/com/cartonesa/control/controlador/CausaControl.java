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
import com.cartonesa.control.interfaceService.ICausaService;
import com.cartonesa.control.interfaceService.IOrdentrabajoService;
import com.cartonesa.control.modelo.Causa;
import com.cartonesa.control.modelo.Ordentrabajo;

@RequestMapping
@Controller
public class CausaControl {
	
	String message;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE CAUSA
	@Autowired
	private ICausaService service;

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE OT
	@Autowired
	private IOrdentrabajoService serviceot;

	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR LAS CAUSAS EXISTENTES
	@GetMapping("listarCausa")
    public String causamenu( Model model){
		List<Causa> causas = service.listar();
		model.addAttribute("mensaje", message);
		model.addAttribute("causas", causas);
		message="";
		return "causamenu";   
    }
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA CAUSA
	@GetMapping("/newcausa")
	public String agregarcausa(Model model) {
		model.addAttribute("causa", new Causa());
		return "FormCausa";
	}
		
	// METODO QUE GUARDA UNA NUEVA CAUSA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/savecausa")
	public String savecausa(@Validated Causa a, Model model) {
		String valor = a.getDescripcioncausa();
		
		if (valor.trim().length()==0) {
			message="Fallo el registro, campo descripcion vacio.";
			
			return "redirect:/listarCausa";
		}else {
			message="Registro completo";
			service.save(a);
			return "redirect:/listarCausa";}
	}
		
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR LA CAUSA
	@GetMapping("/editarcausa/{id}")
	public String editarcausa(@PathVariable int id, Model model) {
		Optional<Causa> causa= service.listarId(id);
		model.addAttribute("causa", causa);
		return "FormCausa";
	}
		
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR LA CAUSA
	@GetMapping("/eliminarCausa/{id}")
	public String deletecausa(Model model,@PathVariable int id) {
		List<Ordentrabajo> ot=serviceot.buscarporidcausa(id);
		
		if(ot.isEmpty()==true) {
			  message="Registro eliminado";
			  service.delete(id);
				return "redirect:/listarCausa";
		 }else { 	
			 message="Esta causa esta usada en un registro OT";
			 return "redirect:/listarCausa";
			}		
	}
			
			
	// METODO QUE BUSCA UN OBJETO POR IDCAUSA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS CAUSAS
	@GetMapping("/findByIdcausa/{clave}")
	public String buscarPorIdcausa(Model model, @PathVariable String clave){
		boolean isNumeric = (clave != null && clave.matches("[0-9]+"));
		
		if(isNumeric==true) {
		int id =Integer.parseInt(clave);
		
		List<Causa> causas = service.findByIdcausa(id);
		if(causas.isEmpty()) {
			message = "No existe este Id";
			List<Causa> causa= service.listar();
			model.addAttribute("causas", causa);
			model.addAttribute("mensaje", message);
			message="";
			return "causamenu";
		}else {
		
		model.addAttribute("causas", causas);
		return "causamenu";
		}
		}else {
			message = "No ingreso un número";
			List<Causa> causa= service.listar();
			model.addAttribute("causas", causa);
			model.addAttribute("mensaje", message);
			message="";
			return 	"causamenu";
		}
	}
	
	
	// METODO QUE BUSCA UN OBJETO POR DESCRIPCION DE CAUSA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS CAUSAS
	@GetMapping("/findByDescripcioncausa/{clave}")
	public String buscarPorDescripcioncausaContaining(Model model, @PathVariable String clave){
		List<Causa> causas = service.findByDescripcioncausaContaining(clave);
		 if(causas.isEmpty()) {
			 message="No existe esa descripcion";
			 List<Causa>causa=service.listar();
			 model.addAttribute("causas", causa);
			 model.addAttribute("mensaje", message);
			 message="";
			 return "causamenu";
		 }else {
		
		model.addAttribute("causas", causas);
		return "causamenu";
		 }
	}
			
}
