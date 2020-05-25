package ar.edu.unlam.tallerweb1.negocio;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
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
}
