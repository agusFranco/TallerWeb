package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface ServicioInsumo {

	List<Insumo> obtenerTodos();

	//  Retorna la cantidad de insumos disponibles, realiza un SUM del campo 'cantidad'
	Long cantTotalInsumos();

	Long insumosSobrantes();
	
	Map<Establecimiento, List<Insumo>> cambiarDeEstablecInsumosSobrantes (Establecimiento establecimiento);
}
