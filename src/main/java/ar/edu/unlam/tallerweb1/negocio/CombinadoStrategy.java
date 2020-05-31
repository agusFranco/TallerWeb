package ar.edu.unlam.tallerweb1.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

//Estrategia: Calcular prioridad por "COMBINADO"
public class CombinadoStrategy implements Strategy {

	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {

		float puntajeTotal = 0;
		for (Establecimiento est : establecimientos) {
			// Factor A: del 0 al 100 que porcentaje de ocupado esta
			float factorA = (est.getOcupacion() * 100) / est.getCapacidad(); 
			// Factor B: puntaje de la zona
			float factorB = est.getZona().getPuntaje(); 
			// Factor C: Entre mas capacidad mas prioritario,10 puntos cada 100 de capacidad.
			float factorC = est.getCapacidad() / 10; 
			// Factor D: Numero random para desempatar.
			float factorD = (float) Math.random(); 
			// Total
			float puntaje = factorA + factorB + factorC + factorD;
			puntajeTotal += puntaje;
			
			est.setPrioridad(puntaje);
		}
		
		Collections.sort(establecimientos,(a,b) -> {
			return (int) (b.getPrioridad() - a.getPrioridad());
		});
		
		for (int i = 0; i < establecimientos.size(); i++) {
			establecimientos.get(i).setPrioridad((float)(i+1));
		}

		return establecimientos;
	}

	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		 /*	Logica de este algoritmo
		  * Se dividen los establecimientos en 5 grupos
		  * grupo 1 recibe el 40 de los insumos > 40% de cada tipo
		  * grupo 2 recibe el 28 de los insumos > 28% de cada tipo
		  * grupo 3 recibe el 17 de los insumos > 17% de cada tipo
		  * grupo 4 recibe el 10 de los insumos > 10% de cada tipo
		  * grupo 5 recibe el 5  de los insumos >  5% de cada tipo
		  * Si al separar en 5 grupos hay resto, quedan en el grupo 5.
		 */
		
		// RETORNO
		Map<Establecimiento,List<Insumo>> model = new HashMap<Establecimiento, List<Insumo>>();
		
		// DIVIDO LOS ESTABLECIMIENTOS EN GRUPOS
		Map<Integer, List<Establecimiento>> grupos = new HashMap<Integer, List<Establecimiento>>();
		int limiteGruppo = (int) establecimientos.size()/5;
		int grupoActual = 1;
		List<Establecimiento> grupoEstablecimiento = new ArrayList<Establecimiento>();
		for (int i = 0; i < establecimientos.size(); i++) {
			if(i % limiteGruppo == 0 && i > 0 && grupoActual < 5) {
				grupos.put(grupoActual, grupoEstablecimiento);
				grupoEstablecimiento = new ArrayList<Establecimiento>();
				grupoActual++;
			}
			grupoEstablecimiento.add(establecimientos.get(i));
			if(i == establecimientos.size()-1) {
				grupos.put(grupoActual, grupoEstablecimiento);
			}
		}
		
		// RECORRO LOS ESTABLECIMIENTOS
		
		
		
		// AGRUPO LOS INSUMOS POR TIPO
		Map<String, List<Insumo>> grupoInsumos = insumos.stream().collect(Collectors.groupingBy(w -> w.getTipo()));
		// RECORRO LOS TIPOS
		for (Map.Entry<String,List<Insumo>> grInsumos : grupoInsumos.entrySet()) {  
			// RECORRO LOS INSUMOS DE ESTE TIPO
			List<Insumo> insumosDeTipo = grInsumos.getValue();
			for(Insumo insumo : insumosDeTipo) {
				//ESTABLECIMIENTO GRUPO 1
				List<Establecimiento> grupo1 = grupos.get(1);
				for(Establecimiento est : grupo1) {
					List<Insumo> auxInsumo = new ArrayList<Insumo>();
					insumo.setCantidad((int)(insumo.getCantidad()*0.4)/grupo1.size());
					auxInsumo.add(insumo);
					model.put(est, auxInsumo);
				}
			}
			
		}
		return new HashMap<Establecimiento, List<Insumo>>();
	}
}
