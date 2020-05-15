package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Establecimientos;

public class CrearTablaEstablecimiento extends SpringTest {

	@Test
	@Transactional
	@Rollback
	public void crearTablaEstablecimiento() {
		Establecimientos clinicaCovid19 = new Establecimientos();
		clinicaCovid19.setCapacidadDelEstablecimiento(100);
		clinicaCovid19.setIndiceDeRiesgo(10);
		clinicaCovid19.setNombreDelEstablecimiento("clinicaUNO");
		final Session session = session();
		session().save(clinicaCovid19);

		Establecimientos buscado = session.get(Establecimientos.class, 1L);
		assertThat(buscado).isNotNull();
	}
}
