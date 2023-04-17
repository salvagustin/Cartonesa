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
	
	// METODO QUE  REDIRECCIONA AL FORMULARIO DE ACCESO DENEGADO 
	@GetMapping("/acceso-denegado")
	public String accesodenegado() {
		return "acceso-denegado";
	}
	
}