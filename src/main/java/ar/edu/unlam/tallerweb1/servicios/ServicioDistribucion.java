package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface ServicioDistribucion {

	public Map<Establecimiento, List<Insumo>> AsignarInsumos(List<Establecimiento> establecimientos,
			List<Insumo> insumos);

}
