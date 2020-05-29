package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDePrioridad;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface ServicioDistribucion {
	
	List<Establecimiento> calcularPrioridad(TipoDePrioridad prioridad, List<Establecimiento> establecimientos);

	Map<Establecimiento, List<Insumo>> distribuirInsumos(TipoDePrioridad prioridad,
														 List<Establecimiento> establecimientos,
														 List<Insumo> insumos);
}
