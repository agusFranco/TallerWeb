package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

public class CrearTablaEstablecimiento extends SpringTest {

	@Test
	@Transactional
	@Rollback
	public void crearTablaEstablecimiento() {
		Establecimiento clinicaCovid19 = new Establecimiento();
		clinicaCovid19.setCapacidad(100);
		clinicaCovid19.setPrioridad(10);
		clinicaCovid19.setNombre("clinicaUNO");
		final Session session = session();
		session().save(clinicaCovid19);

		Establecimiento buscado = session.get(Establecimiento.class, 1L);
		assertThat(buscado).isNotNull();
	}
}
