package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstablecimiento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInsumo;

@Service("servicioInsumo")
@Transactional
public class ServicioInsumoImpl implements ServicioInsumo {

	private RepositorioInsumo servicioInsumoDao;
	private RepositorioEstablecimiento servicioEstablecimientoDao;

	@Autowired
	public ServicioInsumoImpl(RepositorioInsumo servicioInsumoDao,
			RepositorioEstablecimiento servicioEstablecimientoDao) {
		this.servicioInsumoDao = servicioInsumoDao;
		this.servicioEstablecimientoDao = servicioEstablecimientoDao;
	}

	@Override
	public List<Insumo> obtenerTodos() {
		return servicioInsumoDao.getAll();
	}

	// Retorna la cantidad de insumos disponibles, realiza un SUM del campo
	// 'cantidad'
	@Override
	public Long cantTotalInsumos() {
		return servicioInsumoDao.cantTotalInsumos();
	}

	@Override
	public Long insumosSobrantes() {
		Long totalEstablec = (long) servicioEstablecimientoDao.getAll().size();
		List<Insumo> listaInsumos = servicioInsumoDao.getAll();

		Long insumosSobrantes = 0L;
		for (Insumo itemInsumo : listaInsumos) {
			insumosSobrantes = insumosSobrantes + (itemInsumo.getCantidad() % totalEstablec);
		}
		return insumosSobrantes;
	}

}
