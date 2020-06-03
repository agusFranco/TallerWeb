package ar.edu.unlam.tallerweb1.negocio;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.modelo.Zona;

//Estrategia: Calcular prioridad por "ZONA"
public class ZonaStrategy implements Strategy {

	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {

		// Establezco una funcion para obtener la zona del establecimiento
		Function<Establecimiento, Zona> obtenerZona = Establecimiento::getZona;

		// Creo un comparador.
		Comparator comparator = Comparator.comparing(obtenerZona.andThen(Zona::getPuntaje));

		// Orderno la lista.
		Collections.sort(establecimientos, comparator.reversed());

		Integer prioridad = 1;

		for (Establecimiento item : establecimientos) {
			item.setPrioridad((float) prioridad);
			prioridad++;
		}

		return establecimientos;
	}

	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		return new HashMap<Establecimiento, List<Insumo>>();
	}
}
