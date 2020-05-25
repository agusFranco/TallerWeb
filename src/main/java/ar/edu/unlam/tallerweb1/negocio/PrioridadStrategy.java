package ar.edu.unlam.tallerweb1.negocio;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
// Patr�n de dise�o "Strategy" para calcular: Prioridad de Riesgo
public interface PrioridadStrategy {
	
	//Tendr� diferentes implementaciones de acuerdo a como se calcule la prioridad de riesgo.	
	List<Establecimiento> calcular(List<Establecimiento> establecimientos);
	
}
