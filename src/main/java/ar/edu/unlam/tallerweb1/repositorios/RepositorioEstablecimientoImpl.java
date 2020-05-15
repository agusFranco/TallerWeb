package ar.edu.unlam.tallerweb1.repositorios;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

@Repository("repositorioEstablecimiento")
public class RepositorioEstablecimientoImpl implements RepositorioEstablecimiento {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioEstablecimientoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Establecimiento consultarEstablecimiento(int id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Establecimiento) session.createCriteria(Establecimiento.class).add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	@Override
	public Establecimiento getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Establecimiento> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Establecimiento save(Establecimiento entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Establecimiento entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Establecimiento entity) {
		// TODO Auto-generated method stub
		
	}

}
