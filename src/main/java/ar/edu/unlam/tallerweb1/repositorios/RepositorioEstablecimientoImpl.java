package ar.edu.unlam.tallerweb1.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

	
	public void insertarDatosMasivos() {
		final Session session = sessionFactory.getCurrentSession();
		// AGREGAR LOS INSERT MASIVOS DEL OBJETO
		List<Establecimiento> listaObjetos = new ArrayList<Establecimiento>();
		listaObjetos.add(new Establecimiento("Hospital 1", "Ubicacion 01",10000, 10, "Zona A", "Sebastian"));
		listaObjetos.add(new Establecimiento("Hospital 2", "Ubicacion 02",20000, 10, "Zona B", "Agustin"));
		listaObjetos.add(new Establecimiento("Hospital 3", "Ubicacion 03",45000, 10, "Zona A", "Julian"));
		listaObjetos.add(new Establecimiento("Hospital 4", "Ubicacion 04",20000, 10, "Zona A", "Oscar"));
		listaObjetos.add(new Establecimiento("Hospital 5", "Ubicacion 05",50000, 10, "Zona C", "Armando"));
		listaObjetos.add(new Establecimiento("Hospital 6", "Ubicacion 06",10000, 10, "Zona B", "Nicolas"));
		listaObjetos.add(new Establecimiento("Hospital 7", "Ubicacion 07",1000, 10, "Zona C", "Javier"));
		listaObjetos.add(new Establecimiento("Hospital 8", "Ubicacion 08",60000, 10, "Zona A", "Adrian"));
		listaObjetos.add(new Establecimiento("Hospital 8", "Ubicacion 09",10500, 10, "Zona A", "Federico"));
		listaObjetos.add(new Establecimiento("Hospital 8", "Ubicacion 10",50000, 10, "Zona B", "Agustin"));
		for(Establecimiento objeto : listaObjetos) {
			session.save(objeto);
		}
	}
}
