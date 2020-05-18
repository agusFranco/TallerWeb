package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface ServicioInsumo {

	public Insumo obtenerInsumo(int id);

	public void insertarDatosMasivos();
}
