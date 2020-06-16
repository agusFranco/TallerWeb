package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;
import ar.edu.unlam.tallerweb1.configuracion.StringToTipoDeStrategy;
import ar.edu.unlam.tallerweb1.modelo.Distribucion;
import ar.edu.unlam.tallerweb1.modelo.DistribucionDetalle;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioDistribucion;
import ar.edu.unlam.tallerweb1.servicios.ServicioDistribucionDetalle;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;

@Controller()
public class DistribucionControlador {

	private final ServicioEstablecimiento servicioEstablecimiento;
	private final ServicioInsumo servicioInsumo;
	private final ServicioDistribucion servicioDistribucion;
	private final ServicioDistribucionDetalle servicioDistribucionDetalle;

	@Autowired
	public DistribucionControlador(ServicioEstablecimiento servicioEstablecimiento, ServicioInsumo servicioInsumo,
			ServicioDistribucion servicioDistribucion, ServicioDistribucionDetalle servicioDistribucionDetalle) {
		this.servicioEstablecimiento = servicioEstablecimiento;
		this.servicioInsumo = servicioInsumo;
		this.servicioDistribucion = servicioDistribucion;
		this.servicioDistribucionDetalle = servicioDistribucionDetalle;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(TipoDeStrategy.class, new StringToTipoDeStrategy());
	}

	@RequestMapping(path = "/distribucion", method = RequestMethod.GET)
	public ModelAndView irADistribucion() {
		ModelMap modelo = this.obtenerModeloDeDistribucion(TipoDeStrategy.ZONA);
		return new ModelAndView("distribucion", modelo);
	}

	@RequestMapping(path = "/distribucion", method = RequestMethod.POST)
	public ModelAndView calcularDistribucion(@ModelAttribute("strategy") TipoDeStrategy strategy) {
		ModelMap modelo = this.obtenerModeloDeDistribucion(strategy);
		return new ModelAndView("distribucion", modelo);
	}

	@RequestMapping(path = "/cambiarInsumos", method = RequestMethod.POST)
	public ModelAndView cambiarInsumos(@ModelAttribute("establecimiento") Establecimiento establecimiento) {
		ModelMap modelo = new ModelMap();

		Map<Establecimiento, List<Insumo>> distribucionCambiada = servicioInsumo
				.cambiarDeEstablecInsumosSobrantes(establecimiento);
		modelo.put("MapaDistribuido", distribucionCambiada);

		// Sera el que mas prioridad tiene porque es el que selecciona y envia solo el
		// id
		Establecimiento nuevoEstabMaxPrioridad = servicioEstablecimiento
				.consultarEstablecimiento(establecimiento.getId());
		modelo.put("establecMayorOcupacion", nuevoEstabMaxPrioridad);

		Long insumosSobrantes = servicioInsumo.insumosSobrantes();
		modelo.put("insumosSobrantes", insumosSobrantes);

		// Fines presentativos.
		modelo.put("estrategia", TipoDeStrategy.EQUITATIVO.getTipo());

		return new ModelAndView("distribucion", modelo);
	}

	@RequestMapping(path = "/confirmarDistribucion", method = RequestMethod.POST)
	public RedirectView confirmarDistribucion(@ModelAttribute("strategy") TipoDeStrategy strategy) {

		List<Establecimiento> establecimientos = servicioEstablecimiento.obtenerTodos();
		List<Insumo> insumos = servicioInsumo.obtenerTodos();

		Map<Establecimiento, List<Insumo>> distribucion = strategy.distribuirInsumos(establecimientos, insumos);

		// Persiste la distribucion en la base, junto a sus detalles tambien
		servicioDistribucion.guardarDistribucion(distribucion, strategy);

		// Si no hago esto, me lleva un querystrring
		RedirectView rv = new RedirectView("historialDistribuciones");
		rv.setExposeModelAttributes(false);
		return rv;
	}

	@RequestMapping(path = "/historialDistribuciones", method = RequestMethod.GET)
	public ModelAndView historialDistribuciones() {
		ModelMap modelo = this.obtenerModeloDeHistorial();
		return new ModelAndView("historialDistribuciones", modelo);
	}

	@RequestMapping(path = "/distribucion/{id}", method = RequestMethod.GET)
	public ModelAndView detalleDeDistribucion(@PathVariable("id") Long id) {
		ModelMap modelo = new ModelMap();

		Distribucion distribucion = this.servicioDistribucion.obtenerConDetallesPorId(id);

		if (distribucion == null) {
			return new ModelAndView("redirect:/404");
		}

		modelo.put("distribucion", distribucion);

		Map<Establecimiento, List<DistribucionDetalle>> detalles = distribucion.getDetalles().stream()
				.collect(Collectors.groupingBy(d -> d.getEstablecimiento()));

		modelo.put("detalles", detalles);

		return new ModelAndView("detalleDistribucion", modelo);
	}

	private ModelMap obtenerModeloDeHistorial() {
		ModelMap modelo = new ModelMap();

		List<Distribucion> distribuciones = servicioDistribucion.obtenerDistribuciones();
		modelo.put("distribuciones", distribuciones);

		ArrayList<Distribucion> cantidadPorTipo = (ArrayList<Distribucion>) servicioDistribucion
				.totalDistribucionesPorTipo();
		modelo.put("cantidadPorTipo", cantidadPorTipo);

		return modelo;
	}

	private ModelMap obtenerModeloDeDistribucion(TipoDeStrategy strategy) {
		ModelMap modelo = new ModelMap();

		List<Establecimiento> establecimientos = servicioEstablecimiento.obtenerTodos();
		List<Insumo> insumos = servicioInsumo.obtenerTodos();

		// Distribuyo
		Map<Establecimiento, List<Insumo>> distribucion = strategy.distribuirInsumos(establecimientos, insumos);
		modelo.put("MapaDistribuido", distribucion);

		// Calculo lo sobrante
		Long insumosSobrantes = servicioInsumo.insumosSobrantes();
		modelo.put("insumosSobrantes", insumosSobrantes);

		Establecimiento establecMayorOcupacion = servicioEstablecimiento.establecimientoMayorOcupacion();
		modelo.put("establecMayorOcupacion", establecMayorOcupacion);

		// Requerido por el modelAttribute
		modelo.put("establecimiento", new Establecimiento());

		// Fines presentativos.
		modelo.put("estrategia", strategy.getTipo());

		return modelo;
	}
}