package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;
import ar.edu.unlam.tallerweb1.modelo.Distribucion;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface ServicioDistribucion {

	// GetAll
	List<Distribucion> obtenerDistribuciones();

	// GetById
	Distribucion obtenerPorId(Long id);

	// GetById With LazyEntities
	Distribucion obtenerConDetallesPorId(Long id);

	// Count
	List<Distribucion> totalDistribucionesPorTipo();

	// Save
	void guardarDistribucion(Map<Establecimiento, List<Insumo>> distribucion, TipoDeStrategy tipoDeStrategy);
}
