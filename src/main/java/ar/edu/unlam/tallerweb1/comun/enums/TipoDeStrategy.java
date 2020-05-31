package ar.edu.unlam.tallerweb1.comun.enums;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.negocio.CombinadoStrategy;
import ar.edu.unlam.tallerweb1.negocio.OcupacionStrategy;
import ar.edu.unlam.tallerweb1.negocio.CapacidadStrategy;
import ar.edu.unlam.tallerweb1.negocio.Strategy;
import ar.edu.unlam.tallerweb1.negocio.ZonaStrategy;
import ar.edu.unlam.tallerweb1.negocio.EquitativoStrategy;

// Contiene el "Contexto del strategy"
public enum TipoDeStrategy {
	COMBINADO("combinado", new CombinadoStrategy()), 
	OCUPACION("ocupacion", new OcupacionStrategy()),
	CAPACIDAD("capacidad", new CapacidadStrategy()), 
	ZONA("zona", new ZonaStrategy()),
	EQUITATIVO("equitativo", new EquitativoStrategy());

	private String tipo;
	private Strategy strategy;

	// Constructor privado del enum
	private TipoDeStrategy(String tipo, Strategy strategy) {
		this.tipo = tipo;
		this.strategy = strategy;
	}

	// Los enum solo tienen getters
	public String getTipo() {
		return this.tipo;
	}

	public List<Establecimiento> calcularPrioridad(List<Establecimiento> establecimientos) {
		return strategy.calcular(establecimientos);
	}

	public Map<Establecimiento, List<Insumo>> distribuirInsumos(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		return strategy.distribuir(establecimientos, insumos);
	}
}
