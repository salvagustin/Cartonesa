package com.cartonesa.control.controlador;

import java.math.BigDecimal;
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
import com.cartonesa.control.interfaceService.IMaquinaService;
import com.cartonesa.control.interfaceService.IOrdencompraService;
import com.cartonesa.control.interfaceService.IUnidadmedidaService;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordencompra;
import com.cartonesa.control.modelo.Unidadmedida;

@RequestMapping
@Controller
public class OrdencompraControl {

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE ORDEN DE COMPRA
	@Autowired
	private IOrdencompraService service;

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE MAQUINA
	@Autowired
	private IMaquinaService servicemaq;

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE UNIDAD DE MEDIDA
	@Autowired
	private IUnidadmedidaService serviceunidad;


	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODAS LAS ORDENES DE COMPRA
	@GetMapping("listarOrdencompra")
	public String ordentmenu(Model model) {
		List<Ordencompra> ordenesc=service.listar();
		model.addAttribute("ordenesc", ordenesc);
		return "ordencmenu";
	}

	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA ORDEN DE COMPRA
	@GetMapping("/newordenc")
	public String agregarordenc(Model model) {
		List<Maquina> maqList = servicemaq.listar();
		List<Unidadmedida> unidadList = serviceunidad.listar();
		model.addAttribute("ordencompra", new Ordencompra());
		model.addAttribute("maquinas", maqList);
		model.addAttribute("unidadesmedida", unidadList);
		return "aggordenc";
	}

	// METODO QUE REDIRECCIONA QUE GUARDA LA ORDEN DE COMPRA NUEVA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/saveordenc")
	public String saveordenc(@Valid Ordencompra c, Model model) {
		service.save(c);
		return "redirect:/listarOrdencompra";
	}

	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR LA ORDEN DE COMPRA
	@GetMapping("/editarOrdencompra/{id}")
	public String editarordenc(@PathVariable int id, Model model) {
		Optional<Ordencompra> ordencompra = service.listarId(id);
		List<Maquina> maqList = servicemaq.listar();
		List<Unidadmedida> unidadList = serviceunidad.listar();
		model.addAttribute("ordencompra", ordencompra);
		model.addAttribute("maquinas", maqList);
		model.addAttribute("unidadesmedida", unidadList);
		return "aggordenc";
	}

	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR LA ORDEN DE COMPRA
	@GetMapping("/eliminarOrdencompra/{id}")
	public String deleteordenc(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/listarOrdentrabajo";
	}
	
	// METODO QUE BUSCA UN OBJETO POR NUMERO DE SOL DE COMPRA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
	@GetMapping("/findByNumsolcompra/{clave}")
	public String buscarPorNumsolcompra(Model model, @PathVariable int clave){
		List<Ordencompra> ordenesc = service.findByNumsolcompra(clave);
		model.addAttribute("ordenesc", ordenesc);
		return "ordencmenu";		
	}
	
	// METODO QUE BUSCA UN OBJETO POR CANTIDAD DE PRODUCTO Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
	@GetMapping("/findByCantprod/{clave}")
	public String buscarPorCantprod(Model model, @PathVariable BigDecimal clave){
		List<Ordencompra> ordenesc = service.findByCantprod(clave);
		model.addAttribute("ordenesc", ordenesc);
		return "ordencmenu";		
	}

	// METODO QUE BUSCA UN OBJETO POR COTIZACION Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
	@GetMapping("/findByCotizacion/{clave}")
	public String buscarPorCotizacion(Model model, @PathVariable String clave){
		List<Ordencompra> ordenesc = service.findByCotizacion(clave);
		model.addAttribute("ordenesc", ordenesc);
		return "ordencmenu";
	}	
	
	// METODO QUE BUSCA UN OBJETO POR PRODUCTO Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
	@GetMapping("/findByProducto/{clave}")
	public String buscarPorProducto(Model model, @PathVariable String clave){
		List<Ordencompra> ordenesc = service.findByProducto(clave);
		model.addAttribute("ordenesc", ordenesc);
		return "ordencmenu";
	}
	
	// METODO QUE BUSCA UN OBJETO POR MAQUINA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
	@GetMapping("/findByMaquinas/{clave}")
	public String buscarPorMaquinas(Model model, @PathVariable String clave){
		List<Maquina> maquinas = servicemaq.findByMaquinanombre(clave);
		List<Ordencompra> ordenesc=service.findByMaquina(maquinas.get(0));
		model.addAttribute("ordenesc", ordenesc);
		return "ordencmenu";
	}
	
	// METODO QUE BUSCA UN OBJETO POR UNIDAD DE MEDIDA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
	@GetMapping("/findByUnidadmed/{clave}")
	public String buscarPorUnidadmed(Model model, @PathVariable String clave){
		List<Unidadmedida> unidades = serviceunidad.findByUnidad(clave);
		List<Ordencompra> ordenesc=service.findByUnidadmedida(unidades.get(0));
		model.addAttribute("ordenesc", ordenesc);
		return "ordencmenu";
	}
}
