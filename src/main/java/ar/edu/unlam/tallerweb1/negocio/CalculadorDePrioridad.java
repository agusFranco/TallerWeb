package ar.edu.unlam.tallerweb1.negocio;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

public class CalculadorDePrioridad {
	
	private PrioridadStrategy strategy;

	public CalculadorDePrioridad(PrioridadStrategy strategy) {
		this.strategy = strategy;
	}

	public List<Establecimiento> calcularPrioridad(List<Establecimiento> establecimientos) {
		List<Establecimiento> listaEst = this.strategy.calcular(establecimientos);
		return listaEst;
	}
}
