package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.tallerweb1.modelo.Insumo;

public class RepositorioInsumoImpl extends RepositorioBaseImpl<Insumo, Integer>
									implements RepositorioInsumo{

	@Autowired
	public RepositorioInsumoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

//	Debemos crear este método de consultar un insumo por un parámetro de insumo?
	@Override
	public Insumo consultarInsumo(Insumo insumo) {
		return null;
	}

	
	
}
