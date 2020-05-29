package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDePrioridad;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
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
}
