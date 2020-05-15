package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;


public interface RepositorioEstablecimiento extends RepositorioBase<Establecimiento, Integer> {
	
//	¿Este metodo deberia tener un establecimiento o un id como argumento?
//	Ver si podemos borrar este método por gener el getById de RepositorioBase
	public Establecimiento consultarEstablecimiento(int id);
	
}
