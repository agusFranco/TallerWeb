package ar.edu.unlam.tallerweb1.negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstablecimientoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioInsumo;

//Estrategia: Calcular prioridad por "OCUPACI�N"
public class PrioridadOcupacionStrategy implements PrioridadStrategy {

	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
		
		// Calculo el total de Ocupacion 		
		Integer totalOcupacion = 0;
		for(Establecimiento itemEstablec : establecimientos) {
			totalOcupacion = totalOcupacion + itemEstablec.getOcupacion();
		}
		
		// Asigno su porcentaje de ocupacion correspondiente		
		for (Establecimiento itemEstablec : establecimientos) {
			Float prioridad = (((float) itemEstablec.getOcupacion() * 100) / (float) totalOcupacion);
			itemEstablec.setPrioridad(prioridad);
		}
		
		List<Establecimiento> establConPrioridad = establecimientos;
		return establConPrioridad;
	}

	
	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		
		Map<Establecimiento,List<Insumo>> asignacion =  new HashMap<Establecimiento, List<Insumo>>();

		Integer totalInsumos = 0;
		for(Insumo item : insumos) {
			totalInsumos = totalInsumos + item.getCantidad();
		}
		
		//Cantidad de establecimientos
		Integer cantidadEstablec = establecimientos.size();	
	
		

			
		
		// Calculo de Rangos		
		Float promedioMaximo = establecimientos.stream().max(Comparator.comparing(Establecimiento::getPrioridad)).get().getPrioridad();
		Float promedioMitad = promedioMaximo/2;
		Float promedioUnCuarto = promedioMitad/2;
		
		
		//Contador de establecimientos para distribuir en rangos
		Integer contadorEstAlta = 0;
		Integer contadorEstMedia = 0;
		Integer contadorEstBaja = 0;
		for(Establecimiento itemEstablec : establecimientos) {
				if(itemEstablec.getPrioridad() > promedioMitad /*&& itemEstablec.getPrioridad() < 100*/) {
					contadorEstAlta++;	
				}else if(itemEstablec.getPrioridad() > promedioUnCuarto) {
					contadorEstMedia++;
				}else {
					contadorEstBaja++;		
				}		
		}
		
		
		//	Asignaci�n de Insumos a Establecimientos
		for(Establecimiento itemEstablec : establecimientos) {
			List<Insumo> insumosAsignados = new ArrayList<Insumo>();
			
			for(Insumo itemInsumo : insumos) {
				Insumo insumoAsignado = new Insumo();
				insumoAsignado.setNombre(itemInsumo.getNombre());
				insumoAsignado.setTipo(itemInsumo.getTipo());
				
				
//				Float restoInsumos = 0F;
				if(itemEstablec.getPrioridad() > promedioMitad) {

					insumoAsignado.setCantidad((int) (itemInsumo.getCantidad()*0.6) / contadorEstAlta);
					//Resto de Insumos sobrantes	
//					 restoInsumos = restoInsumos + (float) ((itemInsumo.getCantidad()*0.6) % contadorEstAlta);
					
				}else if(itemEstablec.getPrioridad() > promedioUnCuarto) {
					
					insumoAsignado.setCantidad((int) (itemInsumo.getCantidad()*0.3) / contadorEstMedia);
					//Resto de Insumos sobrantes	
//					 restoInsumos = restoInsumos + (float) ((itemInsumo.getCantidad()*0.3) % contadorEstMedia);
					
				}else {
					
					insumoAsignado.setCantidad((int) (itemInsumo.getCantidad()*0.1) / contadorEstBaja);
					//Resto de Insumos sobrantes	
//					 restoInsumos = restoInsumos + (float) ((itemInsumo.getCantidad()*0.1) % contadorEstBaja);
					
				}
				
				// Suma de Insumo restante
//				int sumaInsumoRestante = (int) (Math.round(restoInsumos+0.1));
				// Insumo al que le voy a sumar el restante
				Integer InsumoASumar = insumoAsignado.getCantidad();
				//	OTRA MANERA DE SOLUCIONAR EL PROBLEMA
				int sumaInsumoRestante = itemInsumo.getCantidad() % cantidadEstablec;
				//	Establecimiento con mayor prioridad		
				Establecimiento establecimientoMaxPrioridad = establecimientos.stream().max(Comparator.comparing(Establecimiento::getPrioridad)).get();
				if(itemEstablec.equals(establecimientoMaxPrioridad)) {
					insumoAsignado.setCantidad(InsumoASumar+sumaInsumoRestante);
				}
				
				
				//Agrego insumo a la lista de insumos				
				insumosAsignados.add(insumoAsignado);
			}
			//Agrego la lista de Insumos al Establecimiento			
			asignacion.put(itemEstablec, insumosAsignados);
		}
		return asignacion;
	}
	
	
}
