package ar.edu.unlam.tallerweb1.repositorios;

import java.io.Serializable;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioBase<TEntity extends Object, TId extends Serializable> {
	
	TEntity getById(TId id);
	
	List<TEntity> getAll();
	
	TEntity save(TEntity entity);
	
	void delete(TEntity entity);
	
	void update(TEntity entity);
}
