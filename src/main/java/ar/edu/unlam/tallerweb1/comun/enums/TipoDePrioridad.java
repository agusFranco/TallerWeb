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

// Nuevo Contexto
public enum TipoDePrioridad {
	COMBINADO("combinado", new PrioridadCombinadoStrategy()), OCUPACION("ocupacion", new PrioridadOcupacionStrategy()),
	CAPACIDAD("capacidad", new PrioridadCapacidadStrategy()), ZONA("zona", new PrioridadZonaStrategy());

	private String tipo;
	private PrioridadStrategy estrategiaDeCalculoDePrioridad;

	// Constructor privado en los enum
	private TipoDePrioridad(String tipo, PrioridadStrategy estrategiaDeCalculoDePrioridad) {
		this.tipo = tipo;
		this.estrategiaDeCalculoDePrioridad = estrategiaDeCalculoDePrioridad;
	}

	// Los enum solo tienen getters
	public String getTipo() {
		return this.tipo;
	}

	public List<Establecimiento> calcularPrioridad(List<Establecimiento> lista) {
		return estrategiaDeCalculoDePrioridad.calcular(lista);
	}

	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		return estrategiaDeCalculoDePrioridad.distribuir(establecimientos, insumos);
	}
}
