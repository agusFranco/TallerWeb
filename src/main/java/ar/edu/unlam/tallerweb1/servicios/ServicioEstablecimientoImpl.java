package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.otros.TipoDePrioridad;
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

	/* SERVICIO QUE CALCULA LA PRIORIDAD DE RIESGO */
	@Override
	public List<Establecimiento> calcularPrioridad(TipoDePrioridad prioridad, List<Establecimiento> establecimientos) {
		
		//Crea un calculador que servirá para calcular la prioridad seleccionada
		CalculadorDePrioridad calculador = this.crearCalculador(prioridad);
		
		// Utilizo el método del calculador para calcular la prioridad		
		List<Establecimiento> establConPrioridad = calculador.calcularPrioridad(establecimientos);
		return establConPrioridad;
	}

	
	//Metodo propio del servicio: Utilizado para crear el calculador con la prioridad correspondiente
	private CalculadorDePrioridad crearCalculador(TipoDePrioridad prioridad) {
		switch (prioridad) {
		case OCUPACION:
			PrioridadOcupacionStrategy strategyOcupacion = new PrioridadOcupacionStrategy();
			CalculadorDePrioridad calculador = new CalculadorDePrioridad(strategyOcupacion);
			return calculador;
		case CAPACIDAD:
			return new CalculadorDePrioridad(new PrioridadCapacidadStrategy());
		case ZONA:
			return new CalculadorDePrioridad(new PrioridadZonaStrategy());
		case COMBINADO:
			return new CalculadorDePrioridad(new PrioridadCombinadoStrategy());
		default:
			return new CalculadorDePrioridad(new PrioridadCombinadoStrategy());
		}
	}
}
