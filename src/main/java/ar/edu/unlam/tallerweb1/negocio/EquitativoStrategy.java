package ar.edu.unlam.tallerweb1.negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;

public class EquitativoStrategy implements Strategy {

	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {
		for (Establecimiento itemEstablec : establecimientos) {
			itemEstablec.setPrioridad(1F);
		}
		return establecimientos;
	}

	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {

		Map<Establecimiento, List<Insumo>> asignacion = new HashMap<Establecimiento, List<Insumo>>();

		establecimientos = this.calcular(establecimientos);

		// Cantidad de insumos totales
		Integer totalInsumos = 0;
		for (Insumo item : insumos) {
			totalInsumos = totalInsumos + item.getCantidad();
		}

		// Cantidad de establecimientos totales
		Integer cantidadEstablec = establecimientos.size();

		// Asignación de Insumos a Establecimientos
		for (Establecimiento itemEstablec : establecimientos) {
			// Lista que será asignada al establecimiento	
			List<Insumo> insumosAsignados = new ArrayList<Insumo>();

			for (Insumo itemInsumo : insumos) {
				Insumo insumoAsignado = new Insumo();
				insumoAsignado.setNombre(itemInsumo.getNombre());
				insumoAsignado.setTipo(itemInsumo.getTipo());
				insumoAsignado.setId(itemInsumo.getId());

				insumoAsignado.setCantidad((int) itemInsumo.getCantidad() / cantidadEstablec);

				//Asignación de insumos restantes al establecimiento con maxima cantidad de infectados
				if(itemEstablec.getId()== establecimientos.stream().max(Comparator.comparing(Establecimiento::getOcupacion)).get().getId()) {
					Integer InsumoASumar = insumoAsignado.getCantidad();
					int InsumoRestante = itemInsumo.getCantidad() % cantidadEstablec;		
					insumoAsignado.setCantidad(InsumoASumar+InsumoRestante);	
				}

				// Agrego insumo a la lista de insumos
				insumosAsignados.add(insumoAsignado);
			}

			// Agrego la lista de Insumos al Establecimiento
			asignacion.put(itemEstablec, insumosAsignados);
		}

		return asignacion;
	}
	
}
