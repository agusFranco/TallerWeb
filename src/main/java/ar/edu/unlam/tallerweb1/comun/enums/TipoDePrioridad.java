package ar.edu.unlam.tallerweb1.comun.enums;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.negocio.PrioridadCapacidadStrategy;
import ar.edu.unlam.tallerweb1.negocio.PrioridadCombinadoStrategy;
import ar.edu.unlam.tallerweb1.negocio.PrioridadOcupacionStrategy;
import ar.edu.unlam.tallerweb1.negocio.PrioridadStrategy;
import ar.edu.unlam.tallerweb1.negocio.PrioridadZonaStrategy;

// Contiene el "Contexto del strategy"
public enum TipoDePrioridad {
	COMBINADO("combinado", new PrioridadCombinadoStrategy()), 
	OCUPACION("ocupacion", new PrioridadOcupacionStrategy()),
	CAPACIDAD("capacidad", new PrioridadCapacidadStrategy()), 
	ZONA("zona", new PrioridadZonaStrategy());

	private String tipo;
	private PrioridadStrategy prioridadStrategy;

	// Constructor privado del enum
	private TipoDePrioridad(String tipo, PrioridadStrategy prioridadStrategy) {
		this.tipo = tipo;
		this.prioridadStrategy = prioridadStrategy;
	}

	// Los enum solo tienen getters
	public String getTipo() {
		return this.tipo;
	}

	public List<Establecimiento> calcularPrioridad(List<Establecimiento> establecimientos) {
		return prioridadStrategy.calcular(establecimientos);
	}

	public Map<Establecimiento, List<Insumo>> distribuirInsumos(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		return prioridadStrategy.distribuir(establecimientos, insumos);
	}
}
