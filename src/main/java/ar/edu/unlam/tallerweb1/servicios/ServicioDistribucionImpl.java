package ar.edu.unlam.tallerweb1.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

@Service("servicioDistribucion")
@Transactional
public class ServicioDistribucionImpl implements ServicioDistribucion {

	@Override
	public Map<Establecimiento, List<Insumo>> AsignarInsumos(List<Establecimiento> establecimientos,
			List<Insumo> insumos) {

		Map<Establecimiento, List<Insumo>> distribuciones = new HashMap<Establecimiento, List<Insumo>>();

		Integer contador = 0;

		for (Insumo insumo : insumos) {
			contador = insumo.getCantidad();
		}

		distribuciones.put(establecimientos.get(0), insumos);

		return distribuciones;
	}

}
