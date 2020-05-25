package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.otros.TipoDePrioridad;

public interface ServicioEstablecimiento {

	Establecimiento consultarEstablecimiento(int id);

	List<Establecimiento> obtenerTodos();

	Long cantidadItems(List<Establecimiento> listaEstablecimiento);

	/* SERVICIOS QUE CALCULAR EL ORDEN DE PRIORIDAD */
	List<Establecimiento> calcularPrioridad(TipoDePrioridad prioridad, List<Establecimiento> establecimientos);
}
