package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;

@Controller
public class DonacionController {
	private final ServicioInsumo servicioInsumo;

	@Autowired
	public DonacionController(ServicioInsumo servicioInsumo) {
		this.servicioInsumo = servicioInsumo;
	}

	@RequestMapping(path = "/donacion", method = RequestMethod.GET)
	public ModelAndView donacion() {
		ModelMap modelo = this.obtenerModelo();
		return new ModelAndView("donacion", modelo);
	}

	@RequestMapping(path = "/cargar-donacion", method = RequestMethod.POST)
	public ModelAndView enviarDonacion(@ModelAttribute("insumo") Insumo insumo, String cantidad) {
		ArrayList<String> msg= new ArrayList<String>();
		
		Insumo insumoAModificar = servicioInsumo.obtenerPorId(insumo.getId());
		try {
			servicioInsumo.actualizarInsumo(insumoAModificar, cantidad);
			msg.add("Se ha cargado la donación de "+insumoAModificar.getNombre()+" correctamente");
			msg.add("success");
		} catch (Exception e) {
			msg.add("Debe ingresar un valor positivo");
			msg.add("danger");
		}
		
		ModelMap modelo = this.obtenerModelo();
		modelo.put("msg", msg);

		return new ModelAndView("donacion", modelo);
	}

	@RequestMapping(path = "/recibir-donacion", method = RequestMethod.POST)
	public ModelAndView recibirDonacion(@ModelAttribute("insumo") Insumo insumo, String cantidad) {
		ArrayList<String> msg= new ArrayList<String>();
		
		Insumo insumoAModificar = servicioInsumo.obtenerPorId(insumo.getId());
		try {
			servicioInsumo.actualizarInsumo(insumoAModificar, cantidad);
			msg.add("Se ha recibido la donación de "+insumoAModificar.getNombre()+" correctamente");
			msg.add("success");
		} catch (Exception e) {
			msg.add("No se ha podido recibir correctamente la donación");
			msg.add("danger");
		}

		ModelMap modelo = this.obtenerModelo();
		modelo.put("msg", msg);
		
		return new ModelAndView("donacion", modelo);
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
