package ar.edu.unlam.tallerweb1.controladorestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ResponsableControlador;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioResponsable;

public class ResponsableControladorTest {


	@Test
	public void meLlevaALaVistaDeResponsasbles(){
		//Preperacion
		ServicioResponsable servicioResponsable = mock(ServicioResponsable.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);

		//Ejecucion
		ResponsableControlador controladorResponsable = new ResponsableControlador(servicioResponsable, servicioEstablecimiento);
		ModelAndView modelAndView = controladorResponsable.responsables();
		
		//Validacion
		assertThat(modelAndView.getViewName()).isEqualTo("responsables");	
	}
	
	@Test
	public void meAgregaElModeloAlaVistaResponsable(){
		//Preperacion
		ServicioResponsable servicioResponsable = mock(ServicioResponsable.class);
		ServicioEstablecimiento servicioEstablecimiento = mock(ServicioEstablecimiento.class);
		ResponsableControlador controladorResponsable = new ResponsableControlador(servicioResponsable, servicioEstablecimiento);
		
		//Ejecucion
		ModelAndView modelAndView = controladorResponsable.responsables();
		
		//Validacion
		assertThat(modelAndView.getModel()).isNotNull();	
	}
}
