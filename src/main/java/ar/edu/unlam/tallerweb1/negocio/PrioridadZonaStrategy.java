package ar.edu.unlam.tallerweb1.negocio;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
//Estrategia: Calcular prioridad por "ZONA"
public class PrioridadZonaStrategy implements PrioridadStrategy {
	
	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
		return establecimientos;
	}
}
