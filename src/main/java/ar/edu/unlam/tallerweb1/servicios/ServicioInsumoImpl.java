package ar.edu.unlam.tallerweb1.servicios;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.negocio.OcupacionStrategy;
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

	@Override
	public Map<Establecimiento, List<Insumo>> cambiarDeEstablecInsumosSobrantes(Map<Establecimiento, List<Insumo>> mapa,
			Establecimiento establecimiento) {

		Establecimiento establecimientoMaxPrioridad = mapa.keySet().stream()
				.max(Comparator.comparing(Establecimiento::getOcupacion)).get();
		Establecimiento establecimientoMinPrioridad = mapa.keySet().stream()
				.min(Comparator.comparing(Establecimiento::getOcupacion)).get();

		for (Entry<Establecimiento,List<Insumo>> entry : mapa.entrySet()) {

			if (entry.getKey().getId() == establecimiento.getId()) {
				for (Insumo insumo : entry.getValue()) {
					insumo.setCantidad(insumo.getCantidad() + 8);
				}
			}
			if(entry.getKey().getId() == establecimientoMaxPrioridad.getId()) {
				for (Insumo insumo : entry.getValue()) {
					insumo.setCantidad(insumo.getCantidad() - 8);
				}
			}
		}
		return mapa;
	}
}
