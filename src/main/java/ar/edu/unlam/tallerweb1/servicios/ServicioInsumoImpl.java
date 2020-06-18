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

	@Override
	public Map<Establecimiento, List<Insumo>> cambiarDeEstablecInsumosSobrantes(Establecimiento establecimiento) {
		List<Establecimiento> establecimientos = servicioEstablecimientoDao.getAll();
		List<Insumo> insumos = servicioInsumoDao.getAll();
		Map<Establecimiento, List<Insumo>> asignacion = new HashMap<Establecimiento, List<Insumo>>();
		Integer totalInsumos = 0;
		for (Insumo item : insumos) {
			totalInsumos = totalInsumos + item.getCantidad();
		}
		Integer cantidadEstablec = establecimientos.size();
		for (Establecimiento itemEstablec : establecimientos) {
			itemEstablec.setPrioridad((float) 1);
			List<Insumo> insumosAsignados = new ArrayList<Insumo>();
			for (Insumo itemInsumo : insumos) {
				Insumo insumoAsignado = new Insumo();
				insumoAsignado.setNombre(itemInsumo.getNombre());
				insumoAsignado.setTipo(itemInsumo.getTipo());
				insumoAsignado.setCantidad((int) itemInsumo.getCantidad() / cantidadEstablec);
				
				//Aca es donde cambio que se lo asigne al que me vino por parametro
				if (itemEstablec.getId() == establecimiento.getId()) {
					Integer InsumoASumar = insumoAsignado.getCantidad();
					int InsumoRestante = itemInsumo.getCantidad() % cantidadEstablec;
					insumoAsignado.setCantidad(InsumoASumar + InsumoRestante);
				}
				insumosAsignados.add(insumoAsignado);
			}
			asignacion.put(itemEstablec, insumosAsignados);
		}
		return asignacion;
	}
}
