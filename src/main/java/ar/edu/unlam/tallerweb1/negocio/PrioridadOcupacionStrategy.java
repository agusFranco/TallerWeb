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

//Estrategia: Calcular prioridad por "OCUPACIÓN"
public class PrioridadOcupacionStrategy implements PrioridadStrategy {

	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
		
		// Calculo el total de Ocupacion 		
		Integer totalOcupacion = 0;
		for(Establecimiento itemEstablec : establecimientos) {
			totalOcupacion =+ itemEstablec.getOcupacion();
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
			totalInsumos += item.getCantidad();
		}
		
		//Cantidad de establecimientos
		Integer cantidadEstablec = establecimientos.size();
		
		Float restoInsumos = (float) (totalInsumos % cantidadEstablec);
		
		
		for(Establecimiento itemEstablec : establecimientos) {
			
			List<Insumo> insumosAsignados = new ArrayList<Insumo>();
			
			for(Insumo itemInsumo : insumos) {
				Insumo insumoAsignado = new Insumo();
				insumoAsignado.setNombre(itemInsumo.getNombre());
				insumoAsignado.setTipo(itemInsumo.getTipo());
				
				if(itemEstablec.getPrioridad() > 30 && itemEstablec.getPrioridad() < 100) {

					insumoAsignado.setCantidad((int) (itemInsumo.getCantidad()*0.8));
					
				}else {
					insumoAsignado.setCantidad((int) (itemInsumo.getCantidad()*0.2));
				}
				
				
				insumosAsignados.add(insumoAsignado);
			}
			
			asignacion.put(itemEstablec, insumosAsignados);
		}
		return asignacion;
	}
	
	
	
}
