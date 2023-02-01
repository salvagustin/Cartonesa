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
import com.cartonesa.control.interfaceService.ISubmaquinaService;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Submaquina;
import com.cartonesa.control.interfaceService.IMaquinaService;

@RequestMapping
@Controller
public class SubmaquinaControl {

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE MAQUINA
	@Autowired
	private IMaquinaService service;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE SUBMAQUINA
	@Autowired
	private ISubmaquinaService servicesubmaq;
	
	
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODAS LAS SUBMAQUINAS
	@GetMapping("listarSubmaquina")
    public String submaquinamenu(Model model) {
	 List<Submaquina>submaquinas=servicesubmaq.listar();
		model.addAttribute("submaquinas", submaquinas);
        return "submaquinamenu";      
    }

	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA SUBMAQUINA
	@GetMapping("/newsubmaquina")
	public String agregarsubmaquina(Model model) {
		List<Maquina> maqList = service.listar();
		model.addAttribute("submaquina", new Submaquina());
		model.addAttribute("maquinas", maqList);
		return "aggsubmaquina";
	}
		
	// METODO QUE GUARDA LA SUBMAQUINA NUEVA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/savesubmaquina")
	public String savesubmaquina(@Valid Submaquina s, Model model) {
		servicesubmaq.save(s);
		return "redirect:/listarSubmaquina";
	}
		
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR LA SUBMAQUINA
	@GetMapping("/editarsubmaquina/{id}")
	public String editarmaquina(@PathVariable int id, Model model) {
		Optional<Submaquina> submaquina= servicesubmaq.listarId(id);
		List<Maquina> maqList = service.listar();
		model.addAttribute("submaquina", submaquina);
		model.addAttribute("maquinas", maqList);
		return "aggsubmaquina";
	}
		
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR LA SUBMAQUINA
	@GetMapping("/eliminarSubmaquina/{id}")
	public String deletesubmaquina(Model model,@PathVariable int id) {
		servicesubmaq.delete(id);
		return "redirect:/listarSubmaquina";
	}
			
	// METODO QUE BUSCA UN OBJETO POR NOMBRE Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS SUBMAQUINAS
	@GetMapping("/findByNombres/{clave}")
	public String buscarPorNombres(Model model, @PathVariable String clave){
		List<Submaquina> submaquinas = servicesubmaq.findByNombres(clave);
		model.addAttribute("submaquinas", submaquinas);
		return "submaquinamenu";
	}
			
	// METODO QUE BUSCA UN OBJETO POR MAQUINA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS SUBMAQUINAS	
	@GetMapping("/findByMaquina/{clave}")
	public String buscarPorMaquina(Model model, @PathVariable String clave){
		List<Maquina> maquinas = service.findByMaquinanombre(clave);
		List<Submaquina> submaquinas = servicesubmaq.findByMaquina(maquinas.get(0));
		model.addAttribute("submaquinas", submaquinas);
		return "submaquinamenu";
	}	
}
