package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDeStrategy;
import ar.edu.unlam.tallerweb1.configuracion.RandomDate;
import ar.edu.unlam.tallerweb1.modelo.Distribucion;
import ar.edu.unlam.tallerweb1.modelo.DistribucionDetalle;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.modelo.TipoDistribucion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDistribucion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDistribucionDetalle;

@Service
@Transactional
public class ServicioDistribucionImpl implements ServicioDistribucion {

	private RepositorioDistribucion repositorioDistribucion;

	// Inyección de dependencia
	@Autowired
	public ServicioDistribucionImpl(RepositorioDistribucion repositorioDistribucion,RepositorioDistribucionDetalle RepositorioDistribucionDetalle) {
		this.repositorioDistribucion = repositorioDistribucion;
	}

	@Override
	public void guardarDistribucion(Map<Establecimiento, List<Insumo>> distribucion, TipoDeStrategy tipoDeStrategy) {
		
		//LocalDate end = LocalDate.now();
		LocalDate localDateRandom = new RandomDate(LocalDate.of(2019, 1, 1), LocalDate.of(2021, 1, 1)).nextDate();
		
		//Podemos agregar mas detalles a la distribucion
		DistribucionDetalle distribucionDetalle = new DistribucionDetalle();
		TipoDistribucion tipoDistribucion = new TipoDistribucion();
		
		//Guardo el tipo de distribucion como detalle
		tipoDistribucion.setId((long) tipoDeStrategy.getId());
		
		distribucionDetalle.setFechaDistribucion(localDateRandom);
		distribucionDetalle.setTipoDistribucion(tipoDistribucion);
		
		// Si la creación de las instancias de los objetos auxiliares no están en este órden, se cargan mal a la base, POR QUE???
		for(Entry<Establecimiento, List<Insumo>> establec_listaInsumo :distribucion.entrySet()) {		
			for(Insumo itemInsumo : establec_listaInsumo.getValue()) {
				// Creo un insumo auxilir, no puedo usar directamente itemInsumo porque falla				
				Insumo insumoAAsignar = new Insumo();
				insumoAAsignar.setId(itemInsumo.getId());
				
				// Creo el objeto distribucion y le asigno sus atributos para ser cargados como un registro				
				Distribucion registro_est_insum = new Distribucion();
				registro_est_insum.setEstablecimiento(establec_listaInsumo.getKey());
				registro_est_insum.setInsumo(insumoAAsignar);
				registro_est_insum.setCantidad(itemInsumo.getCantidad());
				
				
				// Seteo la fecha de la distribucion
				registro_est_insum.setDistribucionDetalle(distribucionDetalle);;
				
				
				// Persisto el registro de la asignacion de una cantidad de un Insumo a un Establecimiento	
				repositorioDistribucion.guardarDistribucion(registro_est_insum);
			}	
		}
	}
	

}
