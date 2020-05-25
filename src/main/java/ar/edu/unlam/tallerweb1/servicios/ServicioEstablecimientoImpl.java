package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.negocio.CalculadorDePrioridad;
import ar.edu.unlam.tallerweb1.negocio.PrioridadCapacidadStrategy;
import ar.edu.unlam.tallerweb1.negocio.PrioridadCombinadoStrategy;
import ar.edu.unlam.tallerweb1.negocio.PrioridadOcupacionStrategy;
import ar.edu.unlam.tallerweb1.negocio.PrioridadZonaStrategy;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstablecimiento;

@Service("servicioEstablecimiento")
@Transactional
public class ServicioEstablecimientoImpl implements ServicioEstablecimiento {

	private RepositorioEstablecimiento servicioEstablecimientoDao;

	// Inyección de dependencia
	@Autowired
	public ServicioEstablecimientoImpl(RepositorioEstablecimiento servicioEstablecimientoDao) {
		this.servicioEstablecimientoDao = servicioEstablecimientoDao;
	}

	@Override
	public Establecimiento consultarEstablecimiento(int id) {
		return servicioEstablecimientoDao.getById(id);
	}

	@Override
	public List<Establecimiento> obtenerTodos() {
		return servicioEstablecimientoDao.getAll();
	}

	@Override
	public Long cantidadItems(List<Establecimiento> listaEstablecimiento) {
		return servicioEstablecimientoDao.cantidadItems(listaEstablecimiento);
	}

	/* SERVICIOS QUE CALCULAR EL ORDEN DE PRIORIDAD */

	@Override
	public List<Establecimiento> calcularPrioridad(String prioridad, List<Establecimiento> establecimientos) {
		CalculadorDePrioridad calculador;

		switch (prioridad) {
		case "ocupacion":
			calculador = new CalculadorDePrioridad(new PrioridadOcupacionStrategy());
			break;
		case "capacidad":
			calculador = new CalculadorDePrioridad(new PrioridadCapacidadStrategy());
			break;
		case "zona":
			calculador = new CalculadorDePrioridad(new PrioridadZonaStrategy());
			break;
		case "combinado":
			calculador = new CalculadorDePrioridad(new PrioridadCombinadoStrategy());
			break;
		default:
			calculador = new CalculadorDePrioridad(new PrioridadCombinadoStrategy());
			break;
		}

		return calculador.calcularPrioridad(establecimientos);
	}
}
