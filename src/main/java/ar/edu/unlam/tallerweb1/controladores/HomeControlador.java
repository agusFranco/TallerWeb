package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioDistribucion;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;

@Controller
public class HomeControlador {

	private final ServicioDistribucion servicioDistribucion;
	private final ServicioEstablecimiento servicioEstablecimiento;
	private final ServicioInsumo servicioInsumo;

	@Autowired
	public HomeControlador(ServicioDistribucion servicioDistribucion, ServicioEstablecimiento servicioEstablecimiento,
			ServicioInsumo servicioInsumo) {
		this.servicioDistribucion = servicioDistribucion;
		this.servicioEstablecimiento = servicioEstablecimiento;
		this.servicioInsumo = servicioInsumo;
	}


	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/home");
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("home", this.getDefaultHomeModel());
	}

	// Action de distribuir los insumos.
	// Busca el modelo default y le agrega la asignacion de insumos.
	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/home", method = RequestMethod.POST)
	public ModelAndView distribuirInsumos() {
		// Busco el modelo default
		ModelMap modelo = this.getDefaultHomeModel();

		// cruce entre establecimientos e insumos Map<Establecimiento,Insumo[]>
		Map<Establecimiento, List<Insumo>> asignacion = servicioDistribucion.asignarInsumos(
				(List<Establecimiento>) modelo.get("listaEstablecimientos"), (List<Insumo>) modelo.get("listaInsumos"));

		// La agrego al modelo
		modelo.put("MapaDistribuido", asignacion);
		
		return new ModelAndView("home", modelo);
	}

	
	
	
	// Action de distribuir los insumos.
	// Busca el modelo default y le agrega la asignacion de insumos.
	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/calcular-prioridad", method = RequestMethod.GET)
	public ModelAndView calcularPrioridadOcupacion(@RequestParam("prioridad") String prioridad ) {
		// Busco el modelo default
		ModelMap modelo = this.getDefaultHomeModel();
		
		List<Establecimiento> estXPrioridad;
		//Calcula prioridad de acuerdo al RequestParam
		switch(prioridad) {
		case "ocupacion":
			estXPrioridad = servicioEstablecimiento.calcularPrioridad_Ocupacion((List<Establecimiento>) modelo.get("listaEstablecimientos"));
			break;
			
		case "capacidad": estXPrioridad = servicioEstablecimiento.calcularPrioridad_Capacidad((List<Establecimiento>) modelo.get("listaEstablecimientos"));
			break;
		
		case "zona": estXPrioridad = null;
		default: estXPrioridad = null;
		}

		modelo.put("estXPrioridad", estXPrioridad);
		
		return new ModelAndView("home", modelo);
	}


	// Obtiene los datos por default de la vista home.
	private ModelMap getDefaultHomeModel() {
		ModelMap modelo = new ModelMap();

		// Listas para mostrar en la tabla principal
		List<Establecimiento> establecimientos = servicioEstablecimiento.obtenerTodos();
		List<Insumo> insumos = servicioInsumo.obtenerTodos();

		// Valores para los widget
		Long cantidadEst = servicioEstablecimiento.cantidadItems(establecimientos);
		Long cantidadIns = servicioInsumo.CantTotalInsumos();

		modelo.put("listaEstablecimientos", establecimientos);
		modelo.put("listaInsumos", insumos);
		modelo.put("cantidadEstablecimientos", cantidadEst);
		modelo.put("cantidadInsumos", cantidadIns);

		return modelo;
	}
	
}
