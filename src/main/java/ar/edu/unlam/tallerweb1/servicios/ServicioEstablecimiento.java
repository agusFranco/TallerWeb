package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

public interface ServicioEstablecimiento {

	Establecimiento consultarEstablecimiento(int id);

	List<Establecimiento> obtenerTodos();

	Long cantidadItems(List<Establecimiento> listaEstablecimiento);

	/* SERVICIOS QUE CALCULAR EL ORDEN DE PRIORIDAD */

	List<Establecimiento> calcularPrioridad_Combinado(List<Establecimiento> establecimientos);
	
	List<Establecimiento> calcularPrioridad_Ocupacion(List<Establecimiento> establecimientos);
	
	List<Establecimiento> calcularPrioridad_Capacidad(List<Establecimiento> establecimientos);
	
	List<Establecimiento> calcularPrioridad_Zona(List<Establecimiento> establecimientos);

}
