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
import com.cartonesa.control.interfaceService.ISubmaquinaService;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordentrabajo;
import com.cartonesa.control.modelo.Submaquina;
import com.cartonesa.control.interfaceService.IMaquinaService;
import com.cartonesa.control.interfaceService.IOrdentrabajoService;

@RequestMapping
@Controller
public class SubmaquinaControl {

	String message;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE MAQUINA
	@Autowired
	private IMaquinaService service;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE SUBMAQUINA
	@Autowired
	private ISubmaquinaService servicesubmaq;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE OT
	@Autowired
	private IOrdentrabajoService serviceot;
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODAS LAS SUBMAQUINAS
	@GetMapping("listarSubmaquina")
    public String submaquinamenu(Model model) {
	 List<Submaquina>submaquinas=servicesubmaq.listar();
	 model.addAttribute("mensaje", message);
		model.addAttribute("submaquinas", submaquinas);
		message="";
		return "submaquinamenu";      
    }

	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA SUBMAQUINA
	@GetMapping("/newsubmaquina")
	public String agregarsubmaquina(Model model) {
		List<Maquina> maqList = service.listar();
		model.addAttribute("submaquina", new Submaquina());
		model.addAttribute("maquinas", maqList);
		return "FormSubmaq";
	}
		
	// METODO QUE GUARDA LA SUBMAQUINA NUEVA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/savesubmaquina")
	public String savesubmaquina(@Validated Submaquina s, Model model) {
		String valor = s.getNombres();
		
		if (valor.trim().length()==0) {
			message="Fallo el registro, campo nombre vacio.";
			
			return "redirect:/listarSubmaquina";
		}else {
			message="Registro completo";
			servicesubmaq.save(s);
			return "redirect:/listarSubmaquina";		}
		
	}
		
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR LA SUBMAQUINA
	@GetMapping("/editarsubmaquina/{id}")
	public String editarmaquina(@PathVariable int id, Model model) {
		Optional<Submaquina> submaquina= servicesubmaq.listarId(id);
		List<Maquina> maqList = service.listar();
		model.addAttribute("submaquina", submaquina);
		model.addAttribute("maquinas", maqList);
		return "FormSubmaq";
	}
		
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR LA SUBMAQUINA
	@GetMapping("/eliminarSubmaquina/{id}")
	public String deletesubmaquina(Model model,@PathVariable int id) {
			List<Ordentrabajo> ot=serviceot.buscarporidsubmaq(id);
		
		if(ot.isEmpty()==true) {
			  message="Registro eliminado";
			  servicesubmaq.delete(id);
				return "redirect:/listarSubmaquina";
		 }else { 	
			 message="Esta submaquina esta usada en un registro OT";
			 return "redirect:/listarSubmaquina";
		}	
	}
			
	// METODO QUE BUSCA POR NOMBRE Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS SUBMAQUINAS
	@GetMapping("/findByNombres/{clave}")
	public String buscarPorNombres(Model model, @PathVariable String clave){
		List<Submaquina> submaquinas = servicesubmaq.findByNombres(clave);
		if(submaquinas.isEmpty()) {
			message = "No existe esa Submaquina";
			List<Submaquina> submaquina= servicesubmaq.listar();
			model.addAttribute("submaquinas", submaquina);
			model.addAttribute("mensaje", message);
			message="";
			return "submaquinamenu";
		}else {
		model.addAttribute("submaquinas", submaquinas);
		return "submaquinamenu";
		}
	}
			
	// METODO QUE BUSCA POR MAQUINA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS SUBMAQUINAS	
	@GetMapping("/findByMaquina/{clave}")
	public String buscarPorMaquina(Model model, @PathVariable String clave){
		List<Maquina> maquinas = service.findByMaquinanombre(clave);
		
		if(maquinas.isEmpty()) {
			message = "No existe la Maquina";
			List<Submaquina> submaq= servicesubmaq.listar();
			model.addAttribute("submaquinas", submaq);
			model.addAttribute("mensaje", message);
			message="";
			return "submaquinamenu";
		}else {
			List<Submaquina> submaquinas = servicesubmaq.findByMaquina(maquinas.get(0));
			model.addAttribute("submaquinas", submaquinas);
			return "submaquinamenu";
		}
	}	
}
