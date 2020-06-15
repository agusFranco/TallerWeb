package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.DistribucionDetalle;

@Repository
public class RepositorioDistribucionDetalleImpl extends RepositorioBaseImpl<DistribucionDetalle, Integer>
		implements RepositorioDistribucionDetalle {

	@Autowired
	public RepositorioDistribucionDetalleImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void guardarFechaDeDistribucion(DistribucionDetalle distribucionDetalle) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(distribucionDetalle);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<DistribucionDetalle> totalDistribucionesPorTipo() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(DistribucionDetalle.class, "dd").createAlias("dd.tipoDistribucion", "td")
				.setProjection(Projections.projectionList()
					.add(Projections.groupProperty("td.id"))
					.add(Projections.count("td.id")))
						.list();
	}
}
