package com.cartonesa.control.controlador;

import java.math.BigDecimal;
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
import com.cartonesa.control.interfaceService.IMaquinaService;
import com.cartonesa.control.interfaceService.IOrdencompraService;
import com.cartonesa.control.interfaceService.IOrdentrabajoService;
import com.cartonesa.control.interfaceService.IUnidadmedidaService;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordencompra;
import com.cartonesa.control.modelo.Ordentrabajo;
import com.cartonesa.control.modelo.Unidadmedida;

@RequestMapping
@Controller
public class OrdencompraControl {

	String message;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE ORDEN DE COMPRA
	@Autowired
	private IOrdencompraService service;

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE MAQUINA
	@Autowired
	private IMaquinaService servicemaq;

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE UNIDAD DE MEDIDA
	@Autowired
	private IUnidadmedidaService serviceunidad;

	//VARIABLE DE LA INTERFAZ DE SERVICIO DE OT
	@Autowired
	private IOrdentrabajoService serviceot;

	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODAS LAS ORDENES DE COMPRA
	@GetMapping("listarOrdencompra")
	public String ordentmenu(Model model) {
		List<Ordencompra> ordenesc=service.listar();
		model.addAttribute("mensaje", message);
		model.addAttribute("ordenesc", ordenesc);
		message="";
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
		return "FormOC";
	}

	// METODO QUE GUARDA LA ORDEN DE COMPRA  Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/saveordenc")
	public String saveordenc(@Validated Ordencompra c, Model model) {
		String cot = c.getCotizacion();
		String prod = c.getProducto();
		
		if (cot.trim().length()==0) {
			message="Fallo el registro, campo cotizacion vacio.";
			return "redirect:/listarOrdencompra";
		}else if(prod.trim().length()==0){
			message="Fallo el registro, campo producto vacio.";
			return "redirect:/listarOrdencompra";
		}else {
			message="Registro completo";
			service.save(c);
			return "redirect:/listarOrdencompra";
		}
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
		return "FormOC";
	}

	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR LA ORDEN DE COMPRA
	@GetMapping("/eliminarOrdencompra/{id}")
	public String deleteordenc(Model model, @PathVariable int id) {
		List<Ordentrabajo> ot=serviceot.buscarporidordencomp(id);
		
		if(ot.isEmpty()==true) {
			  message="Registro eliminado";
			  service.delete(id);
				return "redirect:/listarOrdencompra";
		 }else { 	
			 message="Esta orden de compra esta usada en un registro OT";
				return "redirect:/listarOrdencompra";
		 }
	}
	
	// METODO QUE BUSCA POR NUMERO DE SOL DE COMPRA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
	@GetMapping("/findByNumsolcompra/{clave}")
	public String buscarPorNumsolcompra(Model model, @PathVariable String clave){
		boolean isNumeric = (clave != null && clave.matches("[0-9]+"));
		
		if(isNumeric==true) {
			int sol =Integer.parseInt(clave);
			List<Ordencompra> ordenesc = service.findByNumsolcompra(sol);
		
				if(ordenesc.isEmpty()) {
					message = "No existe ese Numero de Sol de Compra";
					List<Ordencompra> ordenc= service.listar();
					model.addAttribute("ordenesc", ordenc);
					model.addAttribute("mensaje", message);
					message="";
					return "ordencmenu";
				}else {
					model.addAttribute("ordenesc", ordenesc);
					return "ordencmenu";
				}
				}else {
					message = "No ingreso un número";
					List<Ordencompra> ordenc= service.listar();
					model.addAttribute("ordenesc", ordenc);
					model.addAttribute("mensaje", message);
					message="";
					return 	"ordencmenu";
				}
		}
	
	// METODO QUE BUSCA  POR CANTIDAD DE PRODUCTO Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
	@GetMapping("/findByCantprod/{clave}")
	public String buscarPorCantprod(Model model, @PathVariable String clave){
				
		boolean isNumeric = (clave != null && clave.matches("[0-9]+"));
		
		if(isNumeric==true) {
			BigDecimal cantidad = new BigDecimal(clave);
			List<Ordencompra> ordenesc = service.findByCantprod(cantidad);
			if(ordenesc.isEmpty()) {
				message = "No existe esa cantidad";
				List<Ordencompra> ordenc= service.listar();
				model.addAttribute("ordenesc", ordenc);
				model.addAttribute("mensaje", message);
				message="";
				return "ordencmenu";
			}else {
				model.addAttribute("ordenesc", ordenesc);
				return "ordencmenu";
			}
			}else {
				message = "No ingreso un número";
				List<Ordencompra> ordenc= service.listar();
				model.addAttribute("ordenesc", ordenc);
				model.addAttribute("mensaje", message);
				message="";
				return 	"ordencmenu";
			}
		}
		
	

	// METODO QUE BUSCA  POR COTIZACION Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
	@GetMapping("/findByCotizacion/{clave}")
	public String buscarPorCotizacion(Model model, @PathVariable String clave){
		List<Ordencompra> ordenesc = service.findByCotizacion(clave);
		if(ordenesc.isEmpty()) {
			message = "No existe esa Cotizacion";
			List<Ordencompra> ordenc= service.listar();
			model.addAttribute("ordenesc", ordenc);
			model.addAttribute("mensaje", message);
			message="";
			return "ordencmenu";
		}else {
		model.addAttribute("ordenesc", ordenesc);
		return "ordencmenu";
		}
	}	
	
	// METODO QUE BUSCA POR PRODUCTO Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
	@GetMapping("/findByProducto/{clave}")
	public String buscarPorProducto(Model model, @PathVariable String clave){
		List<Ordencompra> ordenesc = service.findByProducto(clave);
		if(ordenesc.isEmpty()) {
			message = "No existe ese Producto";
			List<Ordencompra> ordenc= service.listar();
			model.addAttribute("ordenesc", ordenc);
			model.addAttribute("mensaje", message);
			message="";
			return "ordencmenu";
		}else {
		model.addAttribute("ordenesc", ordenesc);
		return "ordencmenu";
		}
	}
	
	// METODO QUE BUSCA POR MAQUINA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
	@GetMapping("/findByMaquinas/{clave}")
	public String buscarPorMaquinas(Model model, @PathVariable String clave){
		List<Maquina> maquinas = servicemaq.findByMaquinanombre(clave);
		if(maquinas.isEmpty()) {
			message = "No existe esa Maquina";
			List<Ordencompra> ordenc= service.listar();
			model.addAttribute("ordenesc", ordenc);
			model.addAttribute("mensaje", message);
			message="";
			return "ordencmenu";
		}else {
		
		List<Ordencompra> ordenesc=service.findByMaquina(maquinas.get(0));
		model.addAttribute("ordenesc", ordenesc);
		return "ordencmenu";
		}
	}
	
	// METODO QUE BUSCA POR UNIDAD DE MEDIDA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
	@GetMapping("/findByUnidadmed/{clave}")
	public String buscarPorUnidadmed(Model model, @PathVariable String clave){
		List<Unidadmedida> unidades = serviceunidad.findByUnidad(clave);
		if(unidades.isEmpty()) {
			message = "No existe esa Unidad";
			List<Ordencompra> ordenc= service.listar();
			model.addAttribute("ordenesc", ordenc);
			model.addAttribute("mensaje", message);
			message="";
			return "ordencmenu";
		}else {
		List<Ordencompra> ordenesc=service.findByUnidadmedida(unidades.get(0));
		model.addAttribute("ordenesc", ordenesc);
		return "ordencmenu";
		}
	}
	
	// METODO QUE BUSCA POR ESTADO OC Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE COMPRA
		@GetMapping("/findByEstadooc/{clave}")
		public String buscarPorEstadooc(Model model, @PathVariable String clave){
			List<Ordencompra> ordenesc = service.findByEstadooc(clave);
			if(ordenesc.isEmpty()) {
				message = "No existe ese Estado";
				List<Ordencompra> ordenc= service.listar();
				model.addAttribute("ordenesc", ordenc);
				model.addAttribute("mensaje", message);
				message="";
				return "ordencmenu";
			}else {
				model.addAttribute("ordenesc", ordenesc);
			return "ordencmenu";
			}
		}
}
