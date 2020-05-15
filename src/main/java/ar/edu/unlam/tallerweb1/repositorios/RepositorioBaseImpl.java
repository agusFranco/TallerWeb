package ar.edu.unlam.tallerweb1.repositorios;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioBase")
public abstract class RepositorioBaseImpl<TEntity extends Object, TId extends Serializable>
		implements RepositorioBase<TEntity, TId> {

	protected Class<TEntity> type;
	protected SessionFactory sessionFactory;

	@Autowired
	public RepositorioBaseImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		Type type = getClass().getGenericSuperclass();
		this.type = (Class<TEntity>) ((ParameterizedType) type).getActualTypeArguments()[0];
	}

	public TEntity getById(final TId id) {
		final Session session = sessionFactory.getCurrentSession();
		return (TEntity) session.get(this.type, id);
	}

	public List<TEntity> getAll() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(this.type).list();
	}

	public TEntity save(TEntity entity) {
		final Session session = sessionFactory.getCurrentSession();
		return (TEntity) session.save(entity);
	}

	public void delete(TEntity entity) {
		final Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
	}

	public void update(TEntity entity) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(entity);
	}
}
