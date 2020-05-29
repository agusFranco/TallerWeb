package ar.edu.unlam.tallerweb1.negocio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

//Estrategia: Calcular prioridad por "OCUPACIÓN"
public class PrioridadOcupacionStrategy implements PrioridadStrategy {

	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
		for (Establecimiento itemEst : establecimientos) {
			Float prioridad = (((float) itemEst.getOcupacion() / (float) itemEst.getCapacidad()) * 100);
			itemEst.setPrioridad(prioridad);
		}

		List<Establecimiento> establConPrioridad = establecimientos;
		return establConPrioridad;
	}

	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		return new HashMap<Establecimiento, List<Insumo>>();
	}
}
