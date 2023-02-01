package com.cartonesa.control.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Cartonesacontrol {

	// METODO QUE  REDIRECCIONA AL FORMULARIO DE LOGIN 
	@GetMapping({"/","/login"})
	public String index() {
		return "index";
	}
	
	// METODO QUE  REDIRECCIONA AL FORMULARIO DE MENU PRINCIPAL 
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	// METODO QUE  REDIRECCIONA AL FORMULARIO DE USUARIOS SIN PRIVILEGIOS 
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	// METODO QUE  REDIRECCIONA AL FORMULARIO DE USUARIO CON PRIVILEGIOS O ADMINISTRADOR 
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
}