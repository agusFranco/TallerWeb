package ar.edu.unlam.tallerweb1.negocio;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

public interface PrioridadStrategy {
	List<Establecimiento> calcular(List<Establecimiento> establecimientos);
}
