package ar.edu.unlam.tallerweb1.negocio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		// Si la prioridad maxima es el 100%, asigno a cada establecimiento lo que le corresponde de prioridad.
		for (Establecimiento itemEstablec : establecimientos) {
			Float prioridad = (((float) itemEstablec.getPrioridad() * 100) / (float) puntajeTotal);
			itemEstablec.setPrioridad(prioridad);
		}
		return establecimientos;
	}

	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		return new HashMap<Establecimiento, List<Insumo>>();
	}
}
