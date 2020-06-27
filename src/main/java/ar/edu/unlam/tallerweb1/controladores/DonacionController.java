package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class DonacionController {
	private final ServicioInsumo servicioInsumo;

	@Inject
	private ServicioLogin servicioLogin;
	
	@Autowired
	public DonacionController(ServicioInsumo servicioInsumo) {
		this.servicioInsumo = servicioInsumo;
	}

	@RequestMapping(path = "/donaciones", method = RequestMethod.GET)
	public ModelAndView donacion() {
		if(!servicioLogin.verificarSesionActiva()) return new ModelAndView("redirect:/login?msg=1");	
		
		ModelMap modelo = this.obtenerModelo();
		return new ModelAndView("donaciones", modelo);
	}

	@RequestMapping(path = "/cargar-donacion", method = RequestMethod.POST)
	public ModelAndView enviarDonacion(@ModelAttribute("insumo") Insumo insumo, String cantidad) {
		if(!servicioLogin.verificarSesionActiva()) return new ModelAndView("redirect:/login?msg=1");
		
		ArrayList<String> msg= new ArrayList<String>();
		
		Insumo insumoAModificar = servicioInsumo.obtenerPorId(insumo.getId());
		try {
			servicioInsumo.actualizarInsumo(insumoAModificar, cantidad);
			msg.add("Se ha cargado la donaci�n de "+insumoAModificar.getNombre()+" correctamente");
			msg.add("success");
		} catch (Exception e) {
			msg.add("Debe ingresar un valor positivo");
			msg.add("danger");
		}
		
		ModelMap modelo = this.obtenerModelo();
		modelo.put("msg", msg);

		return new ModelAndView("donaciones", modelo);
	}

	@RequestMapping(path = "/recibir-donacion", method = RequestMethod.POST)
	public ModelAndView recibirDonacion(@ModelAttribute("insumo") Insumo insumo, String cantidad) {
		if(!servicioLogin.verificarSesionActiva()) return new ModelAndView("redirect:/login?msg=1");
		
		ArrayList<String> msg= new ArrayList<String>();
		
		Insumo insumoAModificar = servicioInsumo.obtenerPorId(insumo.getId());
		try {
			servicioInsumo.actualizarInsumo(insumoAModificar, cantidad);
			msg.add("Se ha recibido la donaci�n de "+insumoAModificar.getNombre()+" correctamente");
			msg.add("success");
		} catch (Exception e) {
			msg.add("No se ha podido recibir correctamente la donaci�n");
			msg.add("danger");
		}

		ModelMap modelo = this.obtenerModelo();
		modelo.put("msg", msg);
		
		return new ModelAndView("donaciones", modelo);
	}
	
	// Obtiene los datos por default
	private ModelMap obtenerModelo() {
		ModelMap modelo = new ModelMap();
		
		modelo.put("insumo", new Insumo());

		// Mostrar insumos actuales
		List<Insumo> insumosDisponibles = servicioInsumo.obtenerTodos();
		modelo.put("insumosDisponibles", insumosDisponibles);
		
		// Mostrar donaciones recibidas
		Map<Insumo, Integer> insumosRecibidos = servicioInsumo.recibirDonaciones();
		modelo.put("insumosRecibidos", insumosRecibidos);

		return modelo;
	}

}