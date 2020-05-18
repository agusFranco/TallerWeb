package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface RepositorioInsumo extends RepositorioBase<Insumo, Integer> {

	public Insumo consultarInsumo(int id);

}
