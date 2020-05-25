package ar.edu.unlam.tallerweb1.negocio;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
// Patrón de diseño "Strategy" para calcular: Prioridad de Riesgo
public interface PrioridadStrategy {
	
	//Tendrá diferentes implementaciones de acuerdo a como se calcule la prioridad de riesgo.	
	List<Establecimiento> calcular(List<Establecimiento> establecimientos);
	
}
