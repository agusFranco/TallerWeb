package ar.edu.unlam.tallerweb1.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

//Estrategia: Calcular prioridad por "OCUPACIÓN"
public class OcupacionStrategy implements Strategy {

	private List<Establecimiento> calcularPorcentaje(List<Establecimiento> establecimientos) {
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
		
		Collections.sort(establecimientos,(a,b) -> {
			return (int) (b.getPrioridad() - a.getPrioridad());
		});
		
		return establecimientos;
	}
	
	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
	
		List<Establecimiento> establecimientosAux = new ArrayList<Establecimiento>(establecimientos);

		for (int i = 0; i < establecimientosAux.size(); i++) {
			establecimientosAux.get(i).setPrioridad((float)(i+1));
		}
		
		return establecimientosAux;
		
	}

	
	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		
		establecimientos = this.calcularPorcentaje(establecimientos);

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
				if(itemEstablec.getPrioridad() > promedioMitad) {
					contadorEstAlta++;	
				}else if(itemEstablec.getPrioridad() > promedioUnCuarto) {
					contadorEstMedia++;
				}else {
					contadorEstBaja++;		
				}		
		}	
		
		//	Asignación de Insumos a Establecimientos
		for(Establecimiento itemEstablec : establecimientos) {
			List<Insumo> insumosAsignados = new ArrayList<Insumo>();
			
			for(Insumo itemInsumo : insumos) {
				Insumo insumoAsignado = new Insumo();
				insumoAsignado.setNombre(itemInsumo.getNombre());
				insumoAsignado.setTipo(itemInsumo.getTipo());
				
				
//				Float restoInsumos = 0F;
				if(itemEstablec.getPrioridad() > promedioMitad) {
					insumoAsignado.setCantidad((int) (itemInsumo.getCantidad()*0.6) / contadorEstAlta);
				}else if(itemEstablec.getPrioridad() > promedioUnCuarto) {				
					insumoAsignado.setCantidad((int) (itemInsumo.getCantidad()*0.3) / contadorEstMedia);
				}else {					
					insumoAsignado.setCantidad((int) (itemInsumo.getCantidad()*0.1) / contadorEstBaja);			
				}
				

				Integer InsumoASumar = insumoAsignado.getCantidad();
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
		
		// HAGO ENROQUE ENTRE EL PORCENTAJE Y EL PUNTAJE MACHEANDO POR ID
		List<Establecimiento> establecimientosPtos = calcular(establecimientos);
		for (Establecimiento est : establecimientosPtos) {
			for (Map.Entry<Establecimiento,List<Insumo>> asg : asignacion.entrySet()) {  
				if(asg.getKey().getId() == est.getId()) {
					asg.getKey().setPrioridad(est.getPrioridad());
				}
			}
		}
		
		return asignacion;
	}
	
	
}
