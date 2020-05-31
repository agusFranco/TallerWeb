package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Responsable;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioResponsable;

@Controller
public class ResponsableControlador {
	
	private final ServicioResponsable servicioResponsable;
	private final ServicioEstablecimiento servicioEstablecimiento;
	
	
	@Autowired
	public ResponsableControlador(ServicioResponsable servicioResponsable, ServicioEstablecimiento servicioEstablecimiento) {
		super();
		this.servicioResponsable = servicioResponsable;
		this.servicioEstablecimiento = servicioEstablecimiento;
	}



	@RequestMapping(path = "/responsables", method = RequestMethod.GET)
	public ModelAndView responsables() {
		
		ModelMap modelo = new ModelMap();
		
		List<Responsable> responsables = servicioResponsable.obtenerTodos();
		List<Establecimiento> establecimientos = servicioEstablecimiento.obtenerTodos();
		

		modelo.put("responsables", responsables);
		modelo.put("establecimientos",establecimientos);
		return new ModelAndView("responsables", modelo);
	}

}
