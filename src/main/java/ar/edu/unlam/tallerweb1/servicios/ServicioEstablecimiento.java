package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

public interface ServicioEstablecimiento {

	Establecimiento consultarEstablecimiento(int id);

	List<Establecimiento> obtenerTodos();

	Long cantidadItems(List<Establecimiento> listaEstablecimiento);

	/* SERVICIOS QUE CALCULAR EL ORDEN DE PRIORIDAD */

<<<<<<< HEAD
	public List<Establecimiento> CalcularPrioridad_Combinado(List<Establecimiento> establecimientos);
	
	public List<Establecimiento> CalcularPrioridad_Ocupacion(List<Establecimiento> establecimientos);
	
	public List<Establecimiento> CalcularPrioridad_Capacidad(List<Establecimiento> establecimientos);
	
	public List<Establecimiento> CalcularPrioridad_Zona(List<Establecimiento> establecimientos);
=======
	Long cantidadItems(List<Establecimiento> listaEstablecimiento);
	
>>>>>>> 48728e5054ab85fe3cac4ece8d4e982ba780df8c

}
