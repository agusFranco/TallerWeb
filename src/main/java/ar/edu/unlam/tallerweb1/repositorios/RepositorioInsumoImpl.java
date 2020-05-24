package ar.edu.unlam.tallerweb1.repositorios;

import java.util.ArrayList;
import java.util.List;

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

	//	@SuppressWarnings("deprecation")
	//	@Override
	//	public Insumo consultarInsumo(int id) {
	//		final Session session = sessionFactory.getCurrentSession();
	//		return (Insumo) session.createCriteria(Insumo.class).add(Restrictions.eq("id", id)).uniqueResult();
	//	}

//	//	Inserta insumos a la BDD - MASIVO
//	public void insertarDatosMasivos() {
//		final Session session = sessionFactory.getCurrentSession();
//		List<Insumo> listaObjetos = new ArrayList<Insumo>();
//		listaObjetos.add(new Insumo("Respiradores Artificiales", "Tipo A", 2000));
//		listaObjetos.add(new Insumo("Camas de Internación", "Tipo A", 3000));
//		listaObjetos.add(new Insumo("Barbijos", "Tipo B", 65000));
//		listaObjetos.add(new Insumo("Artículos de Desinfección", "Tipo B", 15000));
//
//		for (Insumo objeto : listaObjetos) {
//			session.save(objeto);
//		}
//	}

	//  Retorna la cantidad de insumos disponibles, realiza un SUM del campo 'cantidad'
	@Override
	public Long CantTotalInsumos() {
		final Session session = sessionFactory.getCurrentSession();
		return (Long) session.createCriteria(Insumo.class).setProjection(Projections.sum("cantidad")).uniqueResult();
	}
}
