package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

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

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/home");
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("home", this.getDefaultHomeModel());
	}

	@RequestMapping(path = "/home", method = RequestMethod.POST)
	public ModelAndView distribuirInsumos() {
		ModelMap modelo = this.getDefaultHomeModel();

		modelo.put("MapaDistribuido",
				servicioDistribucion.AsignarInsumos((List<Establecimiento>) modelo.get("listaEstablecimientos"),
						(List<Insumo>) modelo.get("listaInsumos")));

		return new ModelAndView("home", modelo);
	}

	// Carga de Establecimientos - Masiva
	@RequestMapping(path = "/cargar-establecimientos", method = RequestMethod.GET)
	public ModelAndView cargarEstablecimientos() {
		servicioEstablecimiento.insertarDatosMasivos();
		return new ModelAndView("redirect:/home");
	}

	// Carga de Insumos - Masiva
	@RequestMapping(path = "/cargar-insumos", method = RequestMethod.GET)
	public ModelAndView cargarInsumos() {
		servicioInsumo.insertarDatosMasivos();
		return new ModelAndView("redirect:/home");
	}

	private ModelMap getDefaultHomeModel() {
		ModelMap modelo = new ModelMap();

		List<Establecimiento> establecimientos = servicioEstablecimiento.obtenerTodos();
		List<Insumo> insumos = servicioInsumo.obtenerTodos();
		Long cantidadEst = servicioEstablecimiento.cantidadItems(establecimientos);
		Long cantidadIns = servicioInsumo.CantTotalInsumos();

		modelo.put("listaEstablecimientos", establecimientos);
		modelo.put("listaInsumos", insumos);
		modelo.put("cantidadEstablecimientos", cantidadEst);
		modelo.put("cantidadInsumos", cantidadIns);

		return modelo;
	}
}
