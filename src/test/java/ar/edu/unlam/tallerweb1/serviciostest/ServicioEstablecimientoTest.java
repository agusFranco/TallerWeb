package ar.edu.unlam.tallerweb1.serviciostest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstablecimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimientoImpl;

public class ServicioEstablecimientoTest extends SpringTest {
	
	@Test
	public void elServicioEstablecimientoMayorOcupacionMeDevuelveUnEstablecimientoSolo() {
		//Preparacion
		RepositorioEstablecimiento servicioEstablecimientoDao = mock(RepositorioEstablecimiento.class);
		List<Establecimiento> lista = new ArrayList<Establecimiento>();
		when(servicioEstablecimientoDao.getAll()).thenReturn(lista);
		lista.add(new Establecimiento());
		
		//Ejecucion
		ServicioEstablecimientoImpl servicioEstablecimiento = new ServicioEstablecimientoImpl(servicioEstablecimientoDao);
		Establecimiento unicoEstablecimientoDevuelto = servicioEstablecimiento.establecimientoMayorOcupacion();
		unicoEstablecimientoDevuelto.setNombre("Establecimiento con mayor capacidad");
		
		//Validacion
		assertThat(unicoEstablecimientoDevuelto).isNotNull();
	}

}
