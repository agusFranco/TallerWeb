package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;
import ar.edu.unlam.tallerweb1.configuracion.StringToTipoDeStrategy;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.negocio.OcupacionStrategy;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;

@Controller()
public class DistribucionControlador {
	
	private final ServicioEstablecimiento servicioEstablecimiento;
	private final ServicioInsumo servicioInsumo;

	@Autowired
	public DistribucionControlador(ServicioEstablecimiento servicioEstablecimiento,
			ServicioInsumo servicioInsumo) {
		this.servicioEstablecimiento = servicioEstablecimiento;
		this.servicioInsumo = servicioInsumo;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(TipoDeStrategy.class, new StringToTipoDeStrategy());
	}
	
	@RequestMapping(path = "/distribuirInsumos", method = RequestMethod.GET)
	public ModelAndView distribuirInsumos(@RequestParam(value="strategy", defaultValue = "OCUPACION") TipoDeStrategy strategy) {
		ModelMap modelo = new ModelMap();
		List<Establecimiento> establecimientos = servicioEstablecimiento.obtenerTodos();
		List<Insumo> insumos = servicioInsumo.obtenerTodos();
		
		Map<Establecimiento, List<Insumo>> distribucion;
		distribucion = strategy.distribuirInsumos(establecimientos, insumos);
		modelo.put("MapaDistribuido", distribucion);
		
		Long insumosSobrantes = servicioInsumo.insumosSobrantes();
		modelo.put("insumosSobrantes",insumosSobrantes);
		
		// Requerido por el modelAttribute		
		modelo.put("establecimiento",new Establecimiento());
		
		Establecimiento establecMayorOcupacion = servicioEstablecimiento.establecimientoMayorOcupacion();
		modelo.put("establecMayorOcupacion",establecMayorOcupacion);
		
		return new ModelAndView("distribucion", modelo);
	}

	
	@RequestMapping(path = "/distribucion", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/distribuirInsumos?strategy=OCUPACION");
	}
	
	
	
	
	@RequestMapping(path = "/cambiarInsumos", method = RequestMethod.POST)
	public ModelAndView cambiarInsumos(@ModelAttribute("establecimiento") Establecimiento establecimiento) {
		ModelMap modelo = new ModelMap();
		Map<Establecimiento, List<Insumo>> distribucionCambiada = servicioInsumo.cambiarDeEstablecInsumosSobrantes(establecimiento);
		modelo.put("MapaDistribuido", distribucionCambiada);
		// Sera el que mas prioridad tiene porque es el que selecciona y envia solo el id
		Establecimiento establecMaxprioridad= servicioEstablecimiento.consultarEstablecimiento(establecimiento.getId());
		Long insumosSobrantes = servicioInsumo.insumosSobrantes();
		modelo.put("insumosSobrantes",insumosSobrantes);
		modelo.put("establecMayorOcupacion",establecMaxprioridad);
		
		return new ModelAndView("distribucion", modelo);
	}

}