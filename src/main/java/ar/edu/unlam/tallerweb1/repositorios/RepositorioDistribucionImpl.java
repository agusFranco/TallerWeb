package ar.edu.unlam.tallerweb1.repositorios;



import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Distribucion;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

@Repository
public class RepositorioDistribucionImpl extends RepositorioBaseImpl<Distribucion, Integer> implements RepositorioDistribucion {

	@Autowired
	public RepositorioDistribucionImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void guardarDistribucion(Distribucion distribucion) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(distribucion);
	}

	@Override
	public Map<Establecimiento, List<Insumo>> obtenerDistribucion(Integer nroDistribucion) {
		// TODO Auto-generated method stub
		return null;
	}



}
