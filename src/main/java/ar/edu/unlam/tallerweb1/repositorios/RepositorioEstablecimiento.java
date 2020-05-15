package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioEstablecimiento extends RepositorioBase<Establecimiento, Integer> {
	public Establecimiento consultarEstablecimiento(int id);
}
