package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioDistribucion;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;

@Controller
public class HomeControlador {

	private ServicioDistribucion servicioDistribucion;
	private ServicioEstablecimiento servicioEstablecimiento;
	private ServicioInsumo servicioInsumo;

	@Autowired
	public HomeControlador(ServicioDistribucion servicioDistribucion, ServicioEstablecimiento servicioEstablecimiento,
			ServicioInsumo servicioInsumo) {
		this.servicioDistribucion = servicioDistribucion;
		this.servicioEstablecimiento = servicioEstablecimiento;
		this.servicioInsumo = servicioInsumo;
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
//		 cruce entre establecimientos e insumos Map<Establecimiento,Insumo[]>
//		 insumosPorEstablecimiento = null; insumosPorEstablecimiento =
//		 servicioDistribucion.AsignarInsumos(establecimientos, insumos);
//		 
		
		ModelMap modelo = new ModelMap();
		
//		Listas para mostrar en la tabla principal
		List<Establecimiento> listaEst = servicioEstablecimiento.obtenerTodos();
		List<Insumo> listaIns =  servicioInsumo.obtenerTodos();
		modelo.put("listaEstablecimientos", listaEst);
		modelo.put("listaInsumos",listaIns);
		
//		Agrego al modelo cartelito con contador de establecimientos
		Long cantidadEst= servicioEstablecimiento.cantidadItems(listaEst);
		modelo.put("cantidadEstablecimientos",cantidadEst);

//		Agrego al modelo cartelito con contador de insumos
		Long cantidadIns = servicioInsumo.CantTotalInsumos();
		modelo.put("cantidadInsumos", cantidadIns);
		
		return new ModelAndView("home", modelo);
	}

//	Carga de Establecimientos - Masiva
	@RequestMapping(path = "/distribuir-insumos", method = RequestMethod.GET)
	public ModelAndView distribuirInsumos() {
		
		List<Establecimiento> listaEst = servicioEstablecimiento.obtenerTodos();
		List<Insumo> listaIns =  servicioInsumo.obtenerTodos();
		
		ModelMap modelo = new ModelMap();
		
		modelo.put("listaDistrib", servicioDistribucion.AsignarInsumos(listaEst, listaIns));
		
		return new ModelAndView("redirect:/home", modelo);
	}	
	
//	Carga de Establecimientos - Masiva
	@RequestMapping(path = "/cargar-establecimientos", method = RequestMethod.GET)
	public ModelAndView cargarEstablecimientos() {
		servicioEstablecimiento.insertarDatosMasivos();
		return new ModelAndView("redirect:/home");
	}

//	Carga de Insumos - Masiva
	@RequestMapping(path = "/cargar-insumos", method = RequestMethod.GET)
	public ModelAndView cargarInsumos() {
		servicioInsumo.insertarDatosMasivos();
		return new ModelAndView("redirect:/home");
	}
	
	
//	REVISAR ESTE METODO, INTENTAR HACER ESTE METODO DE CARGA GENERICO
//	@RequestMapping(path = "/cargar-est", method = RequestMethod.GET)
//	public ModelAndView cargarEstablecimientos() {
//		
//		List<Establecimiento> listaEstablecimiento = new ArrayList<Establecimiento>();
//		servicioEstablecimiento.insertarDatosMasivos(listaEstablecimiento);
//		return new ModelAndView("home");
//	}
}
