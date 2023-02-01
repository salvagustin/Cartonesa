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
import com.cartonesa.control.interfaceService.IUsersService;
import com.cartonesa.control.modelo.Users;

@RequestMapping
@Controller
public class UserControl {

	
	//@Autowired
	//private IUsersService service;
	

	@GetMapping("listarUser")
	public String usermenu(Model model) {
		//List<Users> usuarios = service.listar();
		//model.addAttribute("usuarios", usuarios);
	return "prueba";
	}
/*	
	@GetMapping("/newuser")
	public String agregaruser(Model model) {
		model.addAttribute("user", new Users());
		return "agguser";
	}

	@PostMapping("/saveuser")
	public String saveuser(@Valid Users a, Model model) {
		service.save(a);
		return "redirect:/listarUser";
	}

	@GetMapping("/editaruser/{id}")
	public String editaruser(@PathVariable int id, Model model) {
		Optional<Users> user = service.listarId(id);
		model.addAttribute("user", user);
		return "agguser";
	}
		
	@GetMapping("/eliminarUser/{id}")
	public String deleteuser(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/listarUser";
	}

	
		@GetMapping("/findByUsername/{clave}")
		public String buscarPorUsername(Model model, @PathVariable String clave){
			List<Users> usuarios = service.findByUsername(clave);
			model.addAttribute("usuarios", usuarios);
			return "usermenu";
			
		}*/
			
}
