package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;
import ar.edu.unlam.tallerweb1.configuracion.StringToTipoDeStrategy;
import ar.edu.unlam.tallerweb1.modelo.Distribucion;
import ar.edu.unlam.tallerweb1.modelo.DistribucionDetalle;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.negocio.EquitativoStrategy;
import ar.edu.unlam.tallerweb1.servicios.ServicioDistribucion;
import ar.edu.unlam.tallerweb1.servicios.ServicioDistribucionDetalle;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller()
public class MapaControlador {

	private final ServicioEstablecimiento servicioEstablecimiento;
	private ServicioLogin servicioLogin;
	
	@Autowired
	public MapaControlador(ServicioEstablecimiento servicioEstablecimiento,ServicioLogin servicioLogin) {
		this.servicioEstablecimiento = servicioEstablecimiento;
		this.servicioLogin = servicioLogin;
	}

	@RequestMapping(path = "/mapa", method = RequestMethod.GET)
	public ModelAndView irAMapa() {
		if(!servicioLogin.verificarSesionActiva()) return new ModelAndView("redirect:/login?msg=1");
		ModelMap modelo = new ModelMap();
		return new ModelAndView("mapa", modelo);
	}
	
	@RequestMapping(path = "/getData", method = RequestMethod.GET)
	public @ResponseBody List<Establecimiento> obtenerJSONMapa() {
		List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
		try {
			establecimientos = servicioEstablecimiento.obtenerTodos();		
		}
		catch (Exception ex) {
			
		}
		return establecimientos;
	}
	
}