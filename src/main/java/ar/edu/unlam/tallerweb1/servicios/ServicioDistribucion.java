package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface ServicioDistribucion {

	Map<Establecimiento, List<Insumo>> asignarInsumos(List<Establecimiento> listaEstablecimientos,
													  List<Insumo> listaInsumos);

}
