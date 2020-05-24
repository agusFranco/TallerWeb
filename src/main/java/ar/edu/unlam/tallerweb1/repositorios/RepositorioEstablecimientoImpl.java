package ar.edu.unlam.tallerweb1.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;

@Repository("repositorioEstablecimiento")
public class RepositorioEstablecimientoImpl extends RepositorioBaseImpl<Establecimiento, Integer>
		implements RepositorioEstablecimiento {

	@Autowired
	public RepositorioEstablecimientoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	
//	public void insertarDatosMasivos() {
//		final Session session = sessionFactory.getCurrentSession();
//
//		List<Establecimiento> listaObjetos = new ArrayList<Establecimiento>();
//		listaObjetos.add(
//				new Establecimiento("Establecimiento Buenos Aires", "Ubicacion 01", 10000, "Zona A", "Sebastian"));
//		listaObjetos
//				.add(new Establecimiento("Establecimiento Entre Rios", "Ubicacion 02", 20000, "Zona B", "Agustin"));
//		listaObjetos.add(new Establecimiento("Establecimiento Cordoba", "Ubicacion 03", 45000,"Zona A", "Julian"));
//		listaObjetos.add(new Establecimiento("Establecimiento San Juan", "Ubicacion 04", 20000,"Zona A", "Oscar"));
//		listaObjetos
//				.add(new Establecimiento("Establecimiento Neuquén", "Ubicacion 05", 50000, "Zona C", "Armando"));
//		listaObjetos
//				.add(new Establecimiento("Establecimiento Mendoza", "Ubicacion 06", 10000,"Zona B", "Nicolas"));
//		listaObjetos.add(new Establecimiento("Establecimiento Jujuy", "Ubicacion 07", 1000, "Zona C", "Javier"));
//		listaObjetos
//				.add(new Establecimiento("Establecimiento San Luis", "Ubicacion 08", 60000,"Zona A", "Adrian"));
//		listaObjetos
//				.add(new Establecimiento("Establecimiento Río Negro", "Ubicacion 09", 10500,"Zona A", "Federico"));
//		listaObjetos
//				.add(new Establecimiento("Establecimiento La Pampa", "Ubicacion 10", 50000,"Zona B", "Agustin"));
//		for (Establecimiento objeto : listaObjetos) {
//			session.save(objeto);
//		}
//	}


}
