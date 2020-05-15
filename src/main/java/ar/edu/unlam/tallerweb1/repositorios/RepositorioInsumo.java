package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface RepositorioInsumo extends RepositorioBase<Insumo, Integer>{
	
//	¿Podemos consultar un insumo y devolver un insumo, o este método no hace falta??
	public Insumo consultarInsumo(Insumo insumo);

}
