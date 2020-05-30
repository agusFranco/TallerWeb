package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;
import ar.edu.unlam.tallerweb1.configuracion.StringToTipoDeStrategy;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;

@Controller("/")
public class HomeControlador {

	private final ServicioEstablecimiento servicioEstablecimiento;
	private final ServicioInsumo servicioInsumo;

	@Autowired
	public HomeControlador(ServicioEstablecimiento servicioEstablecimiento,
			ServicioInsumo servicioInsumo) {
		this.servicioEstablecimiento = servicioEstablecimiento;
		this.servicioInsumo = servicioInsumo;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(TipoDeStrategy.class, new StringToTipoDeStrategy());
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("home", this.getDefaultHomeModel());
	}

	// Calcular Indice de Prioridad
	// Busca el modelo default / Ejecuta la Prioridad
	@RequestMapping(path = "/calcularPrioridad", method = RequestMethod.GET)
	public ModelAndView calcularPrioridad(@RequestParam("strategy") TipoDeStrategy strategy) {
		// Busco el modelo default
		ModelMap modelo = this.getDefaultHomeModel();
		List<Establecimiento> listaEstablec = (List<Establecimiento>) modelo.get("listaEstablecimientos");
		
		// Calcula prioridad de acuerdo al RequestParam
		List<Establecimiento> establecConPrioridad = strategy.calcularPrioridad(listaEstablec);
		
		modelo.put("establConPrioridad", establecConPrioridad);

		return new ModelAndView("home", modelo);
	}


	// Obtiene los datos por default de la vista home.
	private ModelMap getDefaultHomeModel() {
		ModelMap modelo = new ModelMap();

		// Listas para mostrar de Establecimientos e insumos.
		List<Establecimiento> listaEstablec = servicioEstablecimiento.obtenerTodos();
		List<Insumo> listaInsumos = servicioInsumo.obtenerTodos();

		// Valores para los widget
		Long cantEstablec = servicioEstablecimiento.cantidadItems(listaEstablec);
		Long cantTotalInsumos = servicioInsumo.cantTotalInsumos();

		modelo.put("listaEstablecimientos", listaEstablec);
		modelo.put("listaInsumos", listaInsumos);
		modelo.put("cantidadEstablecimientos", cantEstablec);
		modelo.put("cantTotalInsumos", cantTotalInsumos);

		return modelo;
	}

	// SI EN ALGUN MOMENTO NECESITAMOS USARLO
	// // Calcula la prioridad (por ajax) en base a el modelo de entrada.
	// @RequestMapping(path = "/calcularPrioridad", method = RequestMethod.POST)
	// public @ResponseBody List<Establecimiento> calcularPrioridad(@RequestBody
	// CalcularPrioridadMdE parametro) {
	// // Busco los establecimientos
	// List<Establecimiento> establecimientos =
	// servicioEstablecimiento.obtenerTodos();
	//
	// // Calculo prioridad en base al modelo de entrada.
	// List<Establecimiento> estXPrioridad =
	// this.servicioDistribucion.calcularPrioridad(parametro.getPrioridad(),
	// establecimientos);
	//
	// return estXPrioridad;
	// }
}
