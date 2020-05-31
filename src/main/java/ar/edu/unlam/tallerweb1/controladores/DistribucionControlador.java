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

@Controller("/distribucion")
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
	public ModelAndView distribuirInsumos(@RequestParam("strategy") TipoDeStrategy strategy) {
		ModelMap modelo = new ModelMap();
		List<Establecimiento> establecimientos = servicioEstablecimiento.obtenerTodos();
		List<Insumo> insumos = servicioInsumo.obtenerTodos();
		
		Map<Establecimiento, List<Insumo>> distribucion;
		distribucion = strategy.distribuirInsumos(establecimientos, insumos);
		modelo.put("MapaDistribuido", distribucion);
		
		Long insumosSobrantes = servicioInsumo.insumosSobrantes();
		modelo.put("insumosSobrantes",insumosSobrantes);
		
		return new ModelAndView("distribucion", modelo);
	}

	
	@RequestMapping(path = "/distribucion", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/distribuirInsumos?strategy=OCUPACION");
	}

}