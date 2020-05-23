package ar.edu.unlam.tallerweb1.servicios;

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
	public Map<Establecimiento, Map<String, Integer>> AsignarInsumos(List<Establecimiento> listaEstablecimientos,
																	 List<Insumo> listaInsumos) {

		Map<Establecimiento, Map<String,Integer>> distribuciones = new HashMap<Establecimiento, Map<String,Integer>>();

		
//		Integer cantInsumos = listaInsumos.size();

		
		//Contador de establecimiento por indice de riesgo mayor a 50
		Integer estMas50 = 00;
		Integer estMenos50 = 00;
		for (Establecimiento establecimientoItem: listaEstablecimientos) {
			if(establecimientoItem.getIndice() > 50) {
				estMas50++;
			}else {
				estMenos50++;
			}
		}
		
		//Variables para el foreach de los atributos/parametros del insumo
		String nombreInsumoActual;
		Integer cantInsumoActual;
		
		for (Establecimiento establecimientoItem: listaEstablecimientos) {
			
			// Mapa donde se almacena el nombre del insumo y su cantidad otorgada			
			Map<String,Integer> mapaInsumoOtorgado = new HashMap<String,Integer>();
			
			for (Insumo insumoItem: listaInsumos) {
				nombreInsumoActual = insumoItem.getNombre();
				cantInsumoActual = insumoItem.getCantidad();
				
				if(establecimientoItem.getIndice() > 50) {	
					
					mapaInsumoOtorgado.put(nombreInsumoActual,(int) (cantInsumoActual*0.80/estMas50));
					distribuciones.put(establecimientoItem,mapaInsumoOtorgado);
					
				}else {
					
					mapaInsumoOtorgado.put(nombreInsumoActual,(int) (cantInsumoActual*0.2/estMenos50));
					distribuciones.put(establecimientoItem,mapaInsumoOtorgado);
				}
			}
		}
		return distribuciones;
	}

	
}



