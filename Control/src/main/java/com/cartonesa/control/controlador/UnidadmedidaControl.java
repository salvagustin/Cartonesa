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
import com.cartonesa.control.interfaceService.IOrdencompraService;
import com.cartonesa.control.interfaceService.IUnidadmedidaService;
import com.cartonesa.control.modelo.Ordencompra;
import com.cartonesa.control.modelo.Unidadmedida;

@RequestMapping
@Controller
public class UnidadmedidaControl {

	String message;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE UNIDAD DE MEDIDA
	@Autowired
	private IUnidadmedidaService service;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE OT
	@Autowired
	private IOrdencompraService serviceoc;

	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA UNIDAD
	@GetMapping("/newunidad")
	public String agregarunidad(Model model) {
		model.addAttribute("unidad", new Unidadmedida());
		return "FormUnidad";
	}
	
	// METODO QUE GUARDA LA UNIDAD NUEVA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/saveunidad")
	public String saveunidad(@Validated Unidadmedida d, Model model) {
		String unidad = d.getUnidad();
		
		if (unidad.trim().length()==0) {
			message="Fallo el registro, campo unidad vacio.";
			return "redirect:/listarUnidad";
		}else {
			message="Registro completo";
			service.save(d);
			return "redirect:/listarUnidad";
		}	
		
	}
	
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR LA UNIDAD
	@GetMapping("/editarunidad/{id}")
	public String editarunidad(@PathVariable int id, Model model) {
		Optional<Unidadmedida>unidad=service.listarId(id);
		model.addAttribute("unidad", unidad);
		return "FormUnidad";
	}
	
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR LA UNIDAD
	@GetMapping("/eliminarUnidad/{id}")
	public String deleteunidad(Model model,@PathVariable int id) {
		List<Ordencompra> ot=serviceoc.buscarporidunidad(id);
		
		if(ot.isEmpty()==true) {
			  message="Registro eliminado";
			  service.delete(id);
				return "redirect:/listarUnidad";
		 }else { 	
			 message="Esta Unidad esta usada en un registro OC";
				return "redirect:/listarUnidad";
			}	
	}
		
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODAS LAS UNIDADES DE MEDIDA
	@GetMapping("listarUnidad")
	public String unidadmenu(Model model) {
		List<Unidadmedida>unidades=service.listar();
		model.addAttribute("mensaje", message);
		model.addAttribute("unidades", unidades);
		message="";
		return "unidadmenu";		
	}
			 
	// METODO QUE BUSCA POR ID Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS UNIDADES
	@GetMapping("/findByIdunidad/{clave}")
	public String buscarPorIdunidad(Model model, @PathVariable String clave){
		boolean isNumeric = (clave != null && clave.matches("[0-9]+"));
		
		if(isNumeric==true) {
		int id =Integer.parseInt(clave);
		List<Unidadmedida> unidades = service.findByIdunidad(id);
		if(unidades.isEmpty()) {
			message = "No existe el Id";
			List<Unidadmedida> unidad= service.listar();
			model.addAttribute("unidades", unidad);
			model.addAttribute("mensaje", message);
			message="";
			return "unidadmenu";
		}else {
		model.addAttribute("unidades", unidades);
		return "unidadmenu";
		}}else {
			message = "No ingreso un número";
			List<Unidadmedida> unidad= service.listar();
			model.addAttribute("unidades", unidad);
			model.addAttribute("mensaje", message);
			message="";
			return 	"unidadmenu";
		}
	}
	
	// METODO QUE BUSCA POR UNIDAD Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS UNIDADES
	@GetMapping("/findByUnidad/{clave}")
	public String buscarPorUnidad(Model model, @PathVariable String clave){
		List<Unidadmedida> unidades = service.findByUnidad(clave);
		if(unidades.isEmpty()) {
			message = "No existe esa unidad";
			List<Unidadmedida> unidad= service.listar();
			model.addAttribute("unidades", unidad);
			model.addAttribute("mensaje", message);
			message="";
			return "unidadmenu";
		}else {
		model.addAttribute("unidades", unidades);
		return "unidadmenu";
		}
	}
}