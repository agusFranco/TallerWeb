package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.DistribucionDetalle;

public interface RepositorioDistribucionDetalle extends RepositorioBase<DistribucionDetalle, Integer> {
	
	void guardarFechaDeDistribucion(DistribucionDetalle distribucionDetalle);
	
	List<DistribucionDetalle> totalDistribucionesPorTipo();
	

}
