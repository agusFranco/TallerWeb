package ar.edu.unlam.tallerweb1.negocio;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

public class PrioridadOcupacionStrategy implements PrioridadStrategy {
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
		for (Establecimiento itemEst : establecimientos) {
			Float prioridad = (((float) itemEst.getOcupacion() / (float) itemEst.getCapacidad()) * 100);
			itemEst.setPrioridad(prioridad);
		}
		return establecimientos;
	}
}
