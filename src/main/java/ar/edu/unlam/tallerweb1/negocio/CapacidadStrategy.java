package ar.edu.unlam.tallerweb1.negocio;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

// Estrategia: Calcular prioridad por "CAPACIDAD"
public class CapacidadStrategy implements Strategy {

	private List<Establecimiento> calcularPorcentaje(List<Establecimiento> establecimientos) {
		Integer capTotal = 0;

		for (Establecimiento itemEstabl : establecimientos) {
			capTotal += itemEstabl.getCapacidad();
		}
		for (Establecimiento itemEstabl : establecimientos) {
			Float prioridad = (((float) itemEstabl.getCapacidad() / (float) capTotal) * 100);
			itemEstabl.setPrioridad(prioridad);
		}

		Collections.sort(establecimientos,(a,b) -> {
			return (int) (b.getPrioridad() - a.getPrioridad());
		});
		
		return establecimientos;
	}
	
	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {

		establecimientos = calcularPorcentaje(establecimientos);

		for (int i = 0; i < establecimientos.size(); i++) {
			establecimientos.get(i).setPrioridad((float)(i+1));
		}
		
		return establecimientos;
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
