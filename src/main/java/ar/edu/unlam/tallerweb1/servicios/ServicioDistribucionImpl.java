package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDePrioridad;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

@Service("servicioDistribucion")
@Transactional
public class ServicioDistribucionImpl implements ServicioDistribucion {

	/* SERVICIO QUE CALCULA LA PRIORIDAD DE RIESGO */
	@Override
	public List<Establecimiento> calcularPrioridad(TipoDePrioridad prioridad, List<Establecimiento> establecimientos) {
		// Utilizo el método del enum para calcular la prioridad.		
		List<Establecimiento> establecConPrioridad = prioridad.calcularPrioridad(establecimientos);
		return establecConPrioridad;
	}

	@Override
	public Map<Establecimiento, List<Insumo>> distribuirInsumos(TipoDePrioridad prioridad,
																List<Establecimiento> listaEstablecimientos,
																List<Insumo> listaInsumos) {

		Map<Establecimiento, List<Insumo>> establecInsumosDistribuidos = prioridad.distribuirInsumos(listaEstablecimientos, listaInsumos);

		return establecInsumosDistribuidos;
	}
}
