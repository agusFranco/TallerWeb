package ar.edu.unlam.tallerweb1.negocio;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

// Estrategia: Calcular prioridad por "CAPACIDAD"
public class CapacidadStrategy implements Strategy {

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

	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		Integer maximo = establecimientos.stream().max(Comparator.comparing(Establecimiento::getCapacidad)).get()
				.getCapacidad();

		for (Establecimiento itemEstabl : establecimientos) {
			Float prioridad = (((float) itemEstabl.getCapacidad() * 100) / (float) maximo);
			itemEstabl.setPrioridad(prioridad);
		}

		List<Establecimiento> establConPrioridad = establecimientos;

		return new HashMap<Establecimiento, List<Insumo>>();
	}
}
