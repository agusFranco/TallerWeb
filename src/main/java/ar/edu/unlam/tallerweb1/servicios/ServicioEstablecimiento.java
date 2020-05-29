package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.comun.enums.TipoDePrioridad;

public interface ServicioEstablecimiento {

	Establecimiento consultarEstablecimiento(int id);

	List<Establecimiento> obtenerTodos();

	Long cantidadItems(List<Establecimiento> listaEstablecimiento);
}
