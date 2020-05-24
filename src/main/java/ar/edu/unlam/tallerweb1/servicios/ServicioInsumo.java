package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Insumo;

public interface ServicioInsumo {

	List<Insumo> obtenerTodos();

//	void insertarDatosMasivos();

	//  Retorna la cantidad de insumos disponibles, realiza un SUM del campo 'cantidad'
	Long CantTotalInsumos();
}
