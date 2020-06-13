package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Distribucion;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDistribucion;

@Service
@Transactional
public class ServicioDistribucionImpl implements ServicioDistribucion {

	private RepositorioDistribucion repositorioDistribucion;

	// Inyección de dependencia
	@Autowired
	public ServicioDistribucionImpl(RepositorioDistribucion repositorioDistribucion) {
		this.repositorioDistribucion = repositorioDistribucion;
	}

	@Override
	public void guardarDistribucion(Map<Establecimiento, List<Insumo>> distribucion) {
		

		for(Entry<Establecimiento, List<Insumo>> unRegistro :distribucion.entrySet()) {
			
			
			for(Insumo itemInsumo : unRegistro.getValue()) {
				Insumo insumoAAsignar = new Insumo();
				insumoAAsignar.setId(itemInsumo.getId());
				insumoAAsignar.setCantidad(itemInsumo.getCantidad());
				
				Distribucion asignacionRegistro = new Distribucion();
				asignacionRegistro.setEstablecimiento(unRegistro.getKey());
				asignacionRegistro.setInsumo(insumoAAsignar);
				repositorioDistribucion.guardarDistribucion(asignacionRegistro);
			}
			
		}
		
	}
	

}
