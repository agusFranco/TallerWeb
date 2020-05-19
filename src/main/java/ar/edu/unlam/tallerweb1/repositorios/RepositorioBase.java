package ar.edu.unlam.tallerweb1.repositorios;

import java.io.Serializable;
import java.util.List;

//Interface Gen�rica
public interface RepositorioBase<TEntity extends Object, TId extends Serializable> {

//	Devuelve un objeto de una entidad por id
	TEntity getById(TId id);

//	Devuelve lista de todos los objetos de la entidad
	List<TEntity> getAll();

//	Guarda una entidad y devuelve su id
	TId save(TEntity entity);

//	Borra un objeto de una entidad
	void delete(TEntity entity);

//	Actualiza un objeto de una entidad
	void update(TEntity entity);

//	void insertBigData(List<TEntity> items);
	
//	Obliga a que cada uno de los repositorio inserte datos masivos, no es gen�rico.
	void insertarDatosMasivos();

}
