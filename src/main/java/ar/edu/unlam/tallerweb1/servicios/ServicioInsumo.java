package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface ServicioInsumo {

	public Insumo consultarInsumo(int id);
	
	public List<Insumo> obtenerTodos();

}
