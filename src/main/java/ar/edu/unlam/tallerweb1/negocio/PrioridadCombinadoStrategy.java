package ar.edu.unlam.tallerweb1.negocio;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

public class PrioridadCombinadoStrategy implements PrioridadStrategy {
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
		return establecimientos;
	}
}
