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
import com.cartonesa.control.interfaceService.IRolService;
import com.cartonesa.control.interfaceService.IUsersService;
import com.cartonesa.control.modelo.Authority;
import com.cartonesa.control.modelo.User;

@RequestMapping
@Controller
public class UserControl {

	String message;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE USUARIOS
	@Autowired
	private IUsersService service;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE ROLES
	@Autowired
	private IRolService servicerol;

	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR LOS USUARIOS
	@GetMapping("listarUsers")
	public String usermenu(Model model) {
		List<User> usuarios = service.listar();
		model.addAttribute("mensaje", message);
		model.addAttribute("usuarios", usuarios);
		message="";
	return "usermenu";
	}
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVO USUARIO
	@GetMapping("/newuser")
	public String agregaruser(Model model) {
		List<Authority> roles = servicerol.listar();
		model.addAttribute("roles", roles);
		model.addAttribute("user", new User());
		return "FormUser";
	}

	// METODO QUE GUARDAR NUEVO USUARIO
	@PostMapping("/saveuser")
	public String saveuser(@Validated User u, Model model) {
		String nombre = u.getUsername();
		String pass = u.getPassword();
		if (nombre.trim().length()==0) {
			message="Fallo el registro, campo nombre vacio.";
			return "redirect:/listarUsers";
		}else if(pass.trim().length()==0){
			message="Fallo el registro, campo contraseña vacio.";
			return "redirect:/listarUsers";
		}else {
			message="Registro completo";
		service.save(u);	
		return "redirect:/listarUsers";
		}
		
	}
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE EDITAR UN USUARIO
	@GetMapping("/editaruser/{id}")
	public String editaruser(@PathVariable int id, Model model) {
		List<Authority> roles = servicerol.listar();
		Optional<User> user = service.listarId(id);
		model.addAttribute("roles", roles);
		model.addAttribute("user", user);
		return "FormUser";
	}
		
	// METODO QUE BUSCA USUARIOS POR NOMBRE DE USUARIO
	@GetMapping("/findByUsername/{clave}")
	public String buscarPorUsernamee(Model model, @PathVariable String clave){
		List<User> usuarios = service.findByUsernamee(clave);
		if(usuarios.isEmpty()) {
			 message="No existe ese Usuario";
			 List<User>users=service.listar();
			 model.addAttribute("usuarios", users);
			 model.addAttribute("mensaje", message);
			 message="";
			 return "usermenu";
		 }else {
		model.addAttribute("usuarios", usuarios);
		return "usermenu";
		 }
	}
			
}
