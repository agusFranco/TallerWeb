package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Insumo;

@Repository("repositorioInsumo")
public class RepositorioInsumoImpl extends RepositorioBaseImpl<Insumo, Integer> implements RepositorioInsumo {

	@Autowired
	public RepositorioInsumoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	//  Retorna la cantidad de insumos disponibles, realiza un SUM del campo 'cantidad'
	@SuppressWarnings("deprecation")
	@Override
	public Long CantTotalInsumos() {
		final Session session = sessionFactory.getCurrentSession();
		return (Long) session.createCriteria(Insumo.class).setProjection(Projections.sum("cantidad")).uniqueResult();
	}
}
