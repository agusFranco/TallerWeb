package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioDistribucion;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;


@Controller
public class HomeControlador {

	private ServicioDistribucion servicioDistribucion;
	private ServicioEstablecimiento servicioEstablecimiento;
	private ServicioInsumo servicioInsumo;

	@Autowired
	public HomeControlador(
			ServicioDistribucion servicioDistribucion,
			ServicioEstablecimiento servicioEstablecimiento,
			ServicioInsumo servicioInsumo){
		this.servicioDistribucion = servicioDistribucion;
		this.servicioEstablecimiento = servicioEstablecimiento;
		this.servicioInsumo = servicioInsumo;
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		List<Establecimiento> establecimientos = servicioEstablecimiento.obtenerTodos();
		List<Insumo> insumos = servicioInsumo.obtenerTodos();
		
		// cruce entre establecimientos e insumos
		Map<Establecimiento,Insumo[]> insumosPorEstablecimiento = null;
		insumosPorEstablecimiento = servicioDistribucion.AsignarInsumos(establecimientos, insumos);
		
		return new ModelAndView("home");
	}	
			
}
