package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

public interface ServicioEstablecimiento {

	public Establecimiento consultarEstablecimiento(int id);
	
	public List<Establecimiento> obtenerTodos();
	
	public Establecimiento calcularRiesgo(Establecimiento establecimiento);

	void insertarDatosMasivos();
	
}
