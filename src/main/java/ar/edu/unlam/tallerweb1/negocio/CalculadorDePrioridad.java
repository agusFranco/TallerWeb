package ar.edu.unlam.tallerweb1.negocio;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
// Contexto para PrioridadStrategy, seg�n las caracter�sticas del contexto, optar� por una estrategia detarminada.
public class CalculadorDePrioridad {
	
	private PrioridadStrategy prioridadStrategy;

	public CalculadorDePrioridad(PrioridadStrategy prioridadStrategy) {
		this.prioridadStrategy = prioridadStrategy;
	}

	public List<Establecimiento> calcularPrioridad(List<Establecimiento> establecimientos) {
		List<Establecimiento> establConPrioridad = this.prioridadStrategy.calcular(establecimientos);
		return establConPrioridad;
	}
}
