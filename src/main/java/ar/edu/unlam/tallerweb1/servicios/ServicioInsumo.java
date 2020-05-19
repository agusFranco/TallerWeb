package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface ServicioInsumo {

	List<Insumo> obtenerTodos();
	
	void insertarDatosMasivos();
	
	Long CantTotalInsumos();
}
