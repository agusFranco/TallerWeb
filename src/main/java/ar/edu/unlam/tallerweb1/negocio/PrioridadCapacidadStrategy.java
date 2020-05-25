package ar.edu.unlam.tallerweb1.negocio;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
// Estrategia: Calcular prioridad por "CAPACIDAD"
public class PrioridadCapacidadStrategy implements PrioridadStrategy {
	
	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
		Integer capTotal = 0;
		for (Establecimiento itemEstabl : establecimientos) {
			capTotal += itemEstabl.getCapacidad();
		}
		for (Establecimiento itemEstabl : establecimientos) {
			Float prioridad = (((float) itemEstabl.getCapacidad() / (float) capTotal) * 100);
			itemEstabl.setPrioridad(prioridad);
		}
		
		List<Establecimiento> establConPrioridad = establecimientos;
		return establConPrioridad;
	}
}
