package com.cartonesa.control.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cartonesa.control.interfaceService.IAreaService;
import com.cartonesa.control.interfaceService.ICausaService;
import com.cartonesa.control.interfaceService.IMaquinaService;
import com.cartonesa.control.interfaceService.IOrdencompraService;
import com.cartonesa.control.interfaceService.IOrdentrabajoService;
import com.cartonesa.control.interfaceService.IOrdentrabajoService.OrdenTExport;
import com.cartonesa.control.interfaceService.ISubmaquinaService;
import com.cartonesa.control.interfaceService.ITecnicoServ;
import com.cartonesa.control.modelo.Area;
import com.cartonesa.control.modelo.Causa;
import com.cartonesa.control.modelo.Maquina;
import com.cartonesa.control.modelo.Ordencompra;
import com.cartonesa.control.modelo.Ordentrabajo;
import com.cartonesa.control.modelo.Submaquina;
import com.cartonesa.control.modelo.Tecnico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;



@RequestMapping
@Controller
public class OrdentrabajoControl {
	
	String message;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE ORDEN DE TRABAJO
	@Autowired
	private IOrdentrabajoService service;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE MAQUINA
	@Autowired
	private IMaquinaService servicemaq;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE SUBMAQUINA
	@Autowired
	private ISubmaquinaService servicesubmaq;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE TECNICO
	@Autowired
	private ITecnicoServ servicetecnico;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE CAUSA
	@Autowired
	private ICausaService servicecausa;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE AREA
	@Autowired
	private IAreaService servicearea;
	
	//VARIABLE DE LA INTERFAZ DE SERVICIO DE OC
	@Autowired
	private IOrdencompraService serviceOC;
	
	
	int idArea = 0;
	int idMaquina = 0;
	
	//VARIABLE PARA ASIGNAR EL ESTADO DE ORDEN DE TRABAJO
	String pendiente="Pendiente";
	String realizada="Realizado";
	String programada="Programada";
	String pxrepuesto="PXRepuesto";
	
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE LISTAR TODAS LAS ORDENES DE TRABAJO
	@GetMapping("listarOrdentrabajo")
    public String ordentmenu(Model model) {
		List<Ordentrabajo> ordentrabajo = service.listar();
		model.addAttribute("ordenest", ordentrabajo);
		model.addAttribute("mensaje", message);
		message="";
        return "ordentmenu";
    }
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE AGREGAR NUEVA ORDEN DE TRABAJO
	@GetMapping("/newot")
	public String agregarordentpreventivo(Model model) {
		List<Area> areaList = servicearea.listar();
		List<Maquina> maqList = servicemaq.listar();
		List<Submaquina> submaqList = servicesubmaq.buscarPorIdMaquina(idMaquina);
		List<Tecnico> tecnList = servicetecnico.listar();
		List<Causa> causaList = servicecausa.listar();
		List<Ordencompra> OCList = serviceOC.listar();
		model.addAttribute("ordentrabajo", new Ordentrabajo());
		model.addAttribute("areas", areaList);
		model.addAttribute("maquinas", maqList);
		model.addAttribute("submaquinas", submaqList);
		model.addAttribute("tecnicos", tecnList);
		model.addAttribute("causas", causaList);
		model.addAttribute("OClist", OCList);
		return "FormOT";
	}
		
	// METODO QUE GUARDA LA ORDEN DE TRABAJO NUEVA Y REDIRECCIONA AL LISTADO DE REGISTROS
	@PostMapping("/saveordent")
	public String saveordent(@Validated Ordentrabajo t, Model model) {
		String descr = t.getDescripcion();
		String falla = t.getFalla();
		if (descr.trim().length()==0) {
			message="Fallo el registro, campo descripcion vacio.";
			return "redirect:/listarOrdentrabajo";
		}else if(falla.trim().length()==0){
			message="Fallo el registro, campo falla vacio.";
			return "redirect:/listarOrdentrabajo";
		}else {
			message="Registro completo";
			service.save(t);
			return "redirect:/listarOrdentrabajo";
		}
		
	}
	
	@GetMapping("/BuscarOTEdit")
	public String editot(Model model) {
		model.addAttribute("mensaje", message);
		message="";
		return "EditarOrden";
	}
	
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR LA ORDEN DE TRABAJO
	@GetMapping("/editarordent/{id}")
	public String editarordent(@PathVariable int id, Model model) {				
		Optional<Ordentrabajo> ordentrabajo= service.listarId(id);
		
		
		if(ordentrabajo.isEmpty()) {
			 message="No existe esa Orden de trabajo";
			 List<Ordentrabajo>ot=service.listar();
			 model.addAttribute("ordenest", ot);
			 model.addAttribute("mensaje", message);
			 message="";
			 return "ordentmenu";
		 }else {
			 int idmaquina = ordentrabajo.get().getMaquina().getId();
				int idsubmaq = ordentrabajo.get().getSubmaquina().getId();
				List<Maquina> maqList = servicemaq.listar();
				List<Submaquina> submaqList = servicesubmaq.listar();
				List<Tecnico> tecnList = servicetecnico.listar();
				List<Causa> causaList = servicecausa.listar();
				List<Ordencompra> OCList = serviceOC.listar();
				model.addAttribute("idmaquina", idmaquina);
				model.addAttribute("idsubmaq", idsubmaq);
				model.addAttribute("ordentrabajo", ordentrabajo);
				model.addAttribute("maquinas", maqList);
				model.addAttribute("submaquinas", submaqList);
				model.addAttribute("tecnicos", tecnList);
				model.addAttribute("causas", causaList);
				model.addAttribute("OClist", OCList);
				return "FormOT";
		 }		
	}
	
	
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE ELIMINAR LA ORDEN DE TRABAJO
	@GetMapping("/eliminarOrdentrabajo/{id}")
	public String deleteordent(Model model,@PathVariable int id) {
		message="Registro eliminado";
		model.addAttribute("mensaje", message);
		service.delete(id);
		return "redirect:/listarOrdentrabajo";
	}
	
	// METODO QUE BUSCA POR MAQUINA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE TRABAJO
	@GetMapping("/findByMaq/{clave}")
	public String buscarPorMaq(Model model, @PathVariable String clave){
		List<Maquina> maquinas = servicemaq.findByMaquinanombre(clave);
		if(maquinas.isEmpty()) {
			 message="No existe esa Maquina";
			 List<Ordentrabajo>ot=service.listar();
			 model.addAttribute("ordenest", ot);
			 model.addAttribute("mensaje", message);
			 message="";
			 return "ordentmenu";
		 }else {
			 List<Ordentrabajo> ordentrabajo = service.findByMaquina(maquinas.get(0));
			 model.addAttribute("ordenest", ordentrabajo);
			 return "ordentmenu";
		 }
	}
	
	// METODO QUE BUSCA POR TECNICO Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE TRABAJO
	@GetMapping("/findByTecn/{clave}")
	public String buscarPorTecn(Model model, @PathVariable String clave){
		List<Tecnico> tecnicos = servicetecnico.findByNombre(clave);
		if(tecnicos.isEmpty()) {
			 message="No existe ese Tecnico";
			 List<Ordentrabajo>ot=service.listar();
			 model.addAttribute("ordenest", ot);
			 model.addAttribute("mensaje", message);
			 message="";
			 return "ordentmenu";
		 }else {
			 List<Ordentrabajo> ordentrabajo = service.findByTecnico(tecnicos.get(0));
			 model.addAttribute("ordenest", ordentrabajo);
			 return "ordentmenu";
		 }
	}
	
	// METODO QUE BUSCA POR CAUSA Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE TRABAJO
	@GetMapping("/findByCausa/{clave}")
	public String buscarPorCausa(Model model, @PathVariable String clave){
		List<Causa> causas = servicecausa.findByDescripcioncausa(clave);
		if(causas.isEmpty()) {
			 message="No existe esa Causa";
			 List<Ordentrabajo>ot=service.listar();
			 model.addAttribute("ordenest", ot);
			 model.addAttribute("mensaje", message);
			 message="";
			 return "ordentmenu";
		 }else {
			 List<Ordentrabajo> ordentrabajo = service.findByCausa(causas.get(0));
			 model.addAttribute("ordenest", ordentrabajo);
			 return "ordentmenu";
		 }
	}
	
	// METODO QUE BUSCA POR ESTADO OT Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE TRABAJO
	@GetMapping("/findByEstadoot/{clave}")
	public String buscarPorEstadoot(Model model, @PathVariable String clave){
		List<Ordentrabajo> ordentrabajo = service.findByEstadoot(clave);
		if(ordentrabajo.isEmpty()) {
			 message="No existe ese Estado";
			 List<Ordentrabajo>ot=service.listar();
			 model.addAttribute("ordenest", ot);
			 model.addAttribute("mensaje", message);
			 message="";
			 return "ordentmenu";
		 }else {
			 model.addAttribute("ordenest", ordentrabajo);
			 return "ordentmenu";
		 }
	}
	
	// METODO QUE BUSCA POR TIPO ORDEN Y REDIRECCIONA AL FORMULARIO DE LISTAR LAS ORDENES DE TRABAJO
	@GetMapping("/findByTipoorden/{clave}")
	public String buscarPorTipoorden(Model model, @PathVariable String clave){
		List<Ordentrabajo> ordentrabajo = service.findByTipoorden(clave);
		if(ordentrabajo.isEmpty()) {
			 message="No existe ese Tipo de orden";
			 List<Ordentrabajo>ot=service.listar();
			 model.addAttribute("ordenest", ot);
			 model.addAttribute("mensaje", message);
			 message="";
			 return "ordentmenu";
		 }else {
			 model.addAttribute("ordenest", ordentrabajo);
			return "ordentmenu";
		 }
	}
	
	// METODO QUE BUSCA UN OBJETO POR CORRELATIVO
	@GetMapping("/findByIdorden/{clave}")
	public String buscarPorIdordentrab(Model model, @PathVariable String clave){
		boolean isNumeric = (clave != null && clave.matches("[0-9]+"));
		if(isNumeric==true) {
		int id =Integer.parseInt(clave);
		List<Ordentrabajo> ordentrabajo = service.findByIdordentrab(id);
		if(ordentrabajo.isEmpty()) {
			 message="No existe ese Id";
			 List<Ordentrabajo>ordent=service.listar();
			 model.addAttribute("ordenest", ordent);
			 model.addAttribute("mensaje", message);
			 message="";
			 return "ordentmenu";
		 }else {
			
		model.addAttribute("ordenest", ordentrabajo);
		return "ordentmenu";
	 }}else {
		 	message = "No ingreso un número";
		 	List<Ordentrabajo>ordent=service.listar();
			model.addAttribute("ordenest", ordent);
			model.addAttribute("mensaje", message);
					message="";
					return 	"ordentmenu";
			 }
	}
			
		
	// METODO QUE BUSCA UN OBJETO POR FALLA
	@GetMapping("/findByFalla/{clave}")
	public String buscarPorFalla1(Model model, @PathVariable String clave){
	List<Ordentrabajo> ordentrabajo = service.findByFallaContaining(clave);
	if(ordentrabajo.isEmpty()) {
		 message="No existe esa Falla";
		 List<Ordentrabajo>ot=service.listar();
		 model.addAttribute("ordenest", ot);
		 model.addAttribute("mensaje", message);
		 message="";
		 return "ordentmenu";
	 }else {
		model.addAttribute("ordenest", ordentrabajo);
		return "ordentmenu";
		 }
	}
		
		
	// METODO PARA BUSCAR SUBMAQUINAS POR MAQUINA SELECCIONADA
	// OBTIENE LA LISTA DE MAQUINAS
	@ModelAttribute("maquinas")
	public List<Maquina> getMaquina() {
		return servicemaq.listar();
	}

	// FILTRA LAS SUBMAQUINAS POR LA MAQUINA SELECCIONADA
	@GetMapping(value = "/buscarPorMaquina")
	public @ResponseBody List<Submaquina> todasSubmaquinasPorMaquina(
			@RequestParam(value = "idMaquina", required = true) int idMaquina) {
		return servicesubmaq.buscarPorIdMaquina(idMaquina);
	}
	
	
	// METODO QUE RECIBE UN OBJETO CON ID Y REDIRECCIONA AL FORMULARIO DE EDITAR LA ORDEN DE TRABAJO
	@GetMapping("/cambioestado/{idorden}/{newestado}")
	public String cambioestadoot(@PathVariable int idorden, @PathVariable String newestado, Model model,@Validated Ordentrabajo o) {				
		Optional<Ordentrabajo> respuesta = service.listarId(idorden);
		int res = respuesta.hashCode();
		if(res==0) {
			message = "Orden no encontrada";	
			return "redirect:/iracerrar";
		}else {
			message = "Cambio efectuado";			
			service.update(idorden, newestado,o);	
			return "redirect:/iracerrar";
		}
	}	
			
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE CERRAR ORDENE DE TRABAJO O CAMBIAR ESTADOO
	@GetMapping("/iracerrar")
	public String iracerrar( Model model) {
		model.addAttribute("mensaje", message);
		message="";
		 return "cierreOrden";
	}
	
	// METODO QUE REDIRECCIONA AL FORMULARIO DE PARA BUSCAR Y EXPORTAR  ORDEN DE TRABAJO
	@GetMapping("/irabuscar")
	public String irabuscar( Model model) {
		List<Ordentrabajo> ordenes=service.listar();
		model.addAttribute("OTList", ordenes);
		model.addAttribute("mensaje", message);
		message="";
		 return "buscarOrden";
	}
	
	
	@RequestMapping(value="/ExportarOT/{selected}", method=RequestMethod.GET)
	public ResponseEntity<Resource> exportJasperReport( @PathVariable int[] selected) throws JRException, IOException {
		
		File file = ResourceUtils.getFile("classpath:static/reporte/OrdenTrabajo.jasper");
		File imgLogo = ResourceUtils.getFile("classpath:static/img/cartonesalogo.jpg");
		JasperReport report =(JasperReport) JRLoader.loadObject(file);
		Map<String, Object> parameters = new HashMap<>();
		String sdf = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
		
		ArrayList<OrdenTExport> ordenes = new ArrayList();
		for(int idordentrab : selected) {
			Optional<OrdenTExport> ot = service.listadoOT(idordentrab);
			OrdenTExport orden =ot.get();
			ordenes.add(orden);
		}	
		try {		
			parameters.put("imglogo", new FileInputStream(imgLogo));				
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ordenes);			
			
			//Fill Jasper report
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
			//Export report
      		
			byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
			StringBuilder stringBuilder = new StringBuilder().append("OTPdf:");
			ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
					.filename(stringBuilder.append("")
					.append("generateDate:")
	                .append(sdf)
	                .append(".pdf")
	                .toString())
	            .build();
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentDisposition(contentDisposition);
	        return ResponseEntity.ok().contentLength((long) reporte.length)
	            .contentType(MediaType.APPLICATION_PDF)
	            .headers(headers).body(new ByteArrayResource(reporte));
	        } 
		catch (FileNotFoundException | JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					return ResponseEntity.noContent().build(); //No se encontro el reporte
		}
}



