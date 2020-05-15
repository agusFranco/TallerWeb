package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;


@Service("servicioDistribucion")
@Transactional
public class ServicioDistribucionImpl implements ServicioDistribucion {

	@Override
	public Map<Establecimiento, Insumo[]> AsignarInsumos(List<Establecimiento> establecimientos, List<Insumo> insumos) {
		// TODO Auto-generated method stub
		return null;
	}

}
