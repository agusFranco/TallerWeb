package ar.edu.unlam.tallerweb1.negocio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

//Estrategia: Calcular prioridad por "ZONA"
public class PrioridadZonaStrategy implements PrioridadStrategy {

	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
		return establecimientos;
	}

	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		return new HashMap<Establecimiento, List<Insumo>>();
	}
}
