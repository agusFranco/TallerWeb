package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.modelo.Responsable;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstablecimiento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstablecimientoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimientoImpl;

public class DistribucionesTest extends SpringTest{

	
	@Test
	@Transactional
	@Rollback
	public void validarDistribución_x_Ocupacion() {

//		Preparación
		RepositorioEstablecimiento repo = new RepositorioEstablecimientoImpl(session().getSessionFactory());		
		ServicioEstablecimientoImpl servicio = new ServicioEstablecimientoImpl(repo);
		
		List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
		Zona zona = new Zona();
		Responsable responsable = new Responsable();
		Establecimiento estabTesteado1 = new Establecimiento("Establecimiento_TEST1", "Ubicacion_TEST", 200, 100, zona, responsable);
		Establecimiento estabTesteado2 = new Establecimiento("Establecimiento_TEST2", "Ubicacion_TEST", 100, 50, zona, responsable);
		Establecimiento estabTesteado3 = new Establecimiento("Establecimiento_TEST3", "Ubicacion_TEST", 100, 10, zona, responsable);
		establecimientos.add(estabTesteado1);
		establecimientos.add(estabTesteado2);
		establecimientos.add(estabTesteado3);

		List<Insumo> insumos = new ArrayList<Insumo>();
		insumos.add(new Insumo(1L,"insumotest","tipotest",103,2F));
		TipoDeStrategy tipoDeStrategy = TipoDeStrategy.OCUPACION;
		
//		Ejecucion	
		Map<Establecimiento,List<Insumo>> estabList = tipoDeStrategy.distribuirInsumos(establecimientos, insumos);
		Integer cantidadTesteada1 = estabList.get(estabTesteado1).get(0).getCantidad();
		Integer cantidadTesteada2 = estabList.get(estabTesteado2).get(0).getCantidad();
		Integer cantidadTesteada3 = estabList.get(estabTesteado3).get(0).getCantidad();
		
//		Comprobacion
		assertEquals(62, cantidadTesteada1, 0.0);
		assertEquals(30, cantidadTesteada2, 0.0);
		assertEquals(10, cantidadTesteada3, 0.0);
	}

	@Test
	@Transactional
	@Rollback
	public void validarPrioridad_x_CapacidadTotal() {


	}
}
