package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

@Service("servicioDistribucion")
@Transactional
public class ServicioDistribucionImpl implements ServicioDistribucion {

	@Override
	public Map<Establecimiento, List<Insumo>> asignarInsumos(List<Establecimiento> listaEstablecimientos,
															 List<Insumo> listaInsumos) {

		Map<Establecimiento, List<Insumo>> distribuciones = new HashMap<Establecimiento, List<Insumo>>();

		
		//Variables para el foreach de los atributos/parametros del insumo
		String nombreInsumoActual;
		Integer cantInsumoActual;
		
		for (Establecimiento estItem: listaEstablecimientos) {
			
			// Mapa donde se almacena el nombre del insumo y su cantidad otorgada			
			List<Insumo> listaInsumoOtorgado = new ArrayList<Insumo>();
			Integer prioridadEst = estItem.getCapacidad()/estItem.getOcupacion();
			
			for (Insumo insumoItem: listaInsumos) {
				nombreInsumoActual = insumoItem.getNombre();
				cantInsumoActual = insumoItem.getCantidad();
				
				Insumo insumoActual = new Insumo();
				insumoActual.setNombre(nombreInsumoActual);
				

				if(prioridadEst > 50) {	

					insumoActual.setCantidad(1000);
					listaInsumoOtorgado.add(insumoActual);

					distribuciones.put(estItem,listaInsumoOtorgado);
					
				}else {
					insumoActual.setCantidad((int) (1000));
					listaInsumoOtorgado.add(insumoActual);

					distribuciones.put(estItem,listaInsumoOtorgado);
				}
			}
		}
		return distribuciones;
	}

	
}



