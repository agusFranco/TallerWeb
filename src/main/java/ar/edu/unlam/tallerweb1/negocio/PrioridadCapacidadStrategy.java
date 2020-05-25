package ar.edu.unlam.tallerweb1.negocio;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

public class PrioridadCapacidadStrategy implements PrioridadStrategy {
	
	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
		Integer capTotal = 0;
		for (Establecimiento itemEst : establecimientos) {
			capTotal += itemEst.getCapacidad();
		}
		for (Establecimiento itemEst : establecimientos) {
			Float prioridad = (((float) itemEst.getCapacidad() / (float) capTotal) * 100);
			itemEst.setPrioridad(prioridad);
		}

		return establecimientos;
	}
}
