package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInsumo;

@Service("servicioInsumo")
@Transactional
public class ServicioInsumoImpl implements ServicioInsumo {

	private RepositorioInsumo servicioInsumoDao;

	@Autowired
	public ServicioInsumoImpl(RepositorioInsumo servicioInsumoDao) {
		this.servicioInsumoDao = servicioInsumoDao;
	}
	
	@Override
	public Insumo consultarInsumo(int id) {
		return servicioInsumoDao.getById(id);
	}

	@Override
	public void insertarDatosMasivos() {
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	public void insertarDatosMasivos() {
//		List<Insumo> insumos = null;
//		servicioInsumoDao.insertBigData(insumos);
//	}
}
