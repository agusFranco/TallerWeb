package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstablecimiento;

@Service("servicioEstablecimiento")
@Transactional
public class ServicioEstablecimientoImpl implements ServicioEstablecimiento {

	private RepositorioEstablecimiento servicioEstablecimientoDao;

	//	Inyección de dependencia
	@Autowired
	public ServicioEstablecimientoImpl(RepositorioEstablecimiento servicioEstablecimientoDao) {
		this.servicioEstablecimientoDao = servicioEstablecimientoDao;
	}

	@Override
	public Establecimiento consultarEstablecimiento(int id) {
		return servicioEstablecimientoDao.getById(id);
	}

	@Override
	public List<Establecimiento> obtenerTodos() {
		return servicioEstablecimientoDao.getAll();
	}
	
	@Override
	public Long cantidadItems(List<Establecimiento> listaEstablecimiento) {
		return servicioEstablecimientoDao.cantidadItems(listaEstablecimiento);
	}

	/* SERVICIOS QUE CALCULAR EL ORDEN DE PRIORIDAD */

	@Override
	public List<Establecimiento> calcularPrioridad_Combinado(List<Establecimiento> establecimientos){
		return establecimientos; //LO HACE JULI
	}
	
	@Override
	public List<Establecimiento> calcularPrioridad_Ocupacion(List<Establecimiento> establecimientos){
		
		for (Establecimiento itemEst : establecimientos) {
			Float prioridad =(((float)itemEst.getOcupacion() / (float)itemEst.getCapacidad()) * 100);
			itemEst.setPrioridad(prioridad);
		}	
		return establecimientos;
	}
	
	@Override
	public List<Establecimiento> calcularPrioridad_Capacidad(List<Establecimiento> establecimientos){
		
		Integer capTotal=0;
		for (Establecimiento itemEst : establecimientos) {
				capTotal += itemEst.getCapacidad();
		}		
		for (Establecimiento itemEst : establecimientos) {
			Float prioridad = (((float)itemEst.getCapacidad() / (float)capTotal) * 100) ;
			itemEst.setPrioridad(prioridad);
		}			
		
		return establecimientos; //LO HACE SEBA
	}
	
	@Override
	public List<Establecimiento> calcularPrioridad_Zona(List<Establecimiento> establecimientos){
		return establecimientos;
	}

}
