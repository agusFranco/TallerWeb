package ar.edu.unlam.tallerweb1.controladorestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;
import ar.edu.unlam.tallerweb1.controladores.HomeControlador;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;

public class HomeControladorTest {

	@Test
	public void inicioMeRedireccionaALaVistaHome() {
		// Preparacion
		ServicioInsumo servicioInsumo = mock(ServicioInsumo.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);

		HomeControlador homeControlador = new HomeControlador(servicioEstablecimiento, servicioInsumo);

		// Ejecucion
		ModelAndView modelAndView = homeControlador.inicio();

		// Validacion
		assertThat(modelAndView.getViewName()).contains("home");
	}

	@Test
	public void homeMeLlevaALaVistaHome() {
		// Preparacion
		ServicioInsumo servicioInsumo = mock(ServicioInsumo.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);

		HomeControlador homeControlador = new HomeControlador(servicioEstablecimiento, servicioInsumo);

		// Ejecucion
		ModelAndView modelAndView = homeControlador.irAHome();

		// Validacion
		assertThat(modelAndView.getViewName()).contains("home");
	}

	@Test
	public void homeMeLlenaElModeloCorrectamente() {
		// Preparacion
		ServicioInsumo servicioInsumo = mock(ServicioInsumo.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);

		HomeControlador homeControlador = new HomeControlador(servicioEstablecimiento, servicioInsumo);

		// Ejecucion
		ModelAndView modelAndView = homeControlador.irAHome();
		ModelMap modelMap = modelAndView.getModelMap();

		// Validacion
		assertTrue(modelMap.containsKey("listaEstablecimientos"));
		assertTrue(modelMap.containsKey("listaInsumos"));
		assertTrue(modelMap.containsKey("cantidadEstablecimientos"));
		assertTrue(modelMap.containsKey("cantTotalInsumos"));
		assertTrue(modelMap.containsKey("establConPrioridad"));
		assertTrue(modelMap.containsKey("filtrosActivos"));
	}

	@Test
	public void calcularPrioridadPorStrategyDevuelveFiltroActivo() {
		// Preparacion
		ServicioInsumo servicioInsumo = mock(ServicioInsumo.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);

		HomeControlador homeControlador = new HomeControlador(servicioEstablecimiento, servicioInsumo);

		// Ejecucion
		ModelAndView modelAndView = homeControlador.homeCalcularPrioridad(TipoDeStrategy.ZONA);
		ModelMap modelMap = modelAndView.getModelMap();

		// Validacion
		assertTrue("Zona".equalsIgnoreCase((String) modelMap.get("filtrosActivos")));
	}
}
