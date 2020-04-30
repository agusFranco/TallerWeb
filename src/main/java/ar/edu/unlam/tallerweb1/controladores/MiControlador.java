package ar.edu.unlam.tallerweb1.controladores;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MiControlador {
	@RequestMapping(value = "/saludar", method = RequestMethod.GET)
	public ModelAndView saludar(@RequestParam() Map<String, String> parametros) {
		ModelMap modelo = new ModelMap();
		modelo.put("nombre", "agustin");
		modelo.put("apellido", parametros.get("apellido"));
		return new ModelAndView("saludo", modelo);
	}

	@RequestMapping(value = "/saludar/{id}", method = RequestMethod.GET)
	public ModelAndView saludarConPathVariable(@RequestParam() Map<String, String> parametros,
			@PathVariable String id) {
		ModelMap modelo = new ModelMap();
		modelo.put("nombre", "agustin");
		modelo.put("apellido", parametros.get("apellido"));
		modelo.put("id", id);
		return new ModelAndView("saludo", modelo);
	}
}
