package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Insumo;

import java.util.List;
import java.util.Map;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

public interface ServicioDistribucion {

	public Map<Establecimiento,List<Insumo>> AsignarInsumos(List<Establecimiento> establecimientos,List<Insumo> insumos);

}
