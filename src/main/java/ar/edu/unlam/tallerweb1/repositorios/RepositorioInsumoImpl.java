package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

@Repository("repositorioInsumo")
public class RepositorioInsumoImpl extends RepositorioBaseImpl<Insumo, Integer>
									implements RepositorioInsumo{

	@Autowired
	public RepositorioInsumoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Insumo consultarInsumo(int id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Insumo) session.createCriteria(Insumo.class).add(Restrictions.eq("id", id))
				.uniqueResult();
	}
}
