package ar.edu.unlam.tallerweb1.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unlam.tallerweb1.comun.modelos.negocio.GrupoDeDistribucion;
import ar.edu.unlam.tallerweb1.modelo.Establecimiento;
import ar.edu.unlam.tallerweb1.modelo.Insumo;
import ar.edu.unlam.tallerweb1.modelo.Zona;

//Estrategia: Calcular prioridad por "ZONA"
public class ZonaStrategy implements Strategy {

	/// Grupos (PuntajeMinimo, PuntajeMaximo, Porcion de Distribucion, Cantidad
	/// Inicial)
	private GrupoDeDistribucion grupo1;
	private GrupoDeDistribucion grupo2;
	private GrupoDeDistribucion grupo3;
	private GrupoDeDistribucion grupo4;

	private List<GrupoDeDistribucion> grupos;

	public ZonaStrategy() {
		this.grupo1 = new GrupoDeDistribucion(80, 100, (float) 0.4);
		this.grupo2 = new GrupoDeDistribucion(50, 79, (float) 0.3);
		this.grupo3 = new GrupoDeDistribucion(20, 49, (float) 0.2);
		this.grupo4 = new GrupoDeDistribucion(0, 19, (float) 0.1);

		this.grupos = new ArrayList<GrupoDeDistribucion>();

		this.grupos.add(this.grupo1);
		this.grupos.add(this.grupo2);
		this.grupos.add(this.grupo3);
		this.grupos.add(this.grupo4);
	}

	@Override
	public List<Establecimiento> calcular(List<Establecimiento> establecimientos) {

		this.establecerPrioridad(establecimientos);

		Integer prioridad = 1;

		for (Establecimiento item : establecimientos) {
			item.setPrioridad((float) prioridad);
			prioridad++;
		}

		return establecimientos;
	}

	@Override
	public Map<Establecimiento, List<Insumo>> distribuir(List<Establecimiento> establecimientos, List<Insumo> insumos) {

		Map<Establecimiento, List<Insumo>> distribucion = new HashMap<Establecimiento, List<Insumo>>();

		this.establecerPrioridad(establecimientos);

		this.establecerCantidadesDeGrupo(establecimientos);

		for (Establecimiento establecimiento : establecimientos) {
			List<Insumo> insumosAsignados = new ArrayList<Insumo>();

			for (Insumo itemInsumo : insumos) {
				Insumo insumoTemporal = new Insumo();
				insumoTemporal.setNombre(itemInsumo.getNombre());
				insumoTemporal.setTipo(itemInsumo.getTipo());
				insumoTemporal.setId(itemInsumo.getId());

				// Obtengo Grupo
				GrupoDeDistribucion grupo = this.obtenerGrupo(establecimiento);

				// Seteo la cantidad de insumos en base al grupo
				insumoTemporal.setCantidad(
						(int) (itemInsumo.getCantidad() * grupo.getPorcentajeADistribuir()) / grupo.getCantidad());

				// Agrego insumo a la lista de insumos
				insumosAsignados.add(insumoTemporal);
			}

			// Agrego la lista de insumos correspondiente distribuidos al establecimiento
			distribucion.put(establecimiento, insumosAsignados);
		}

		this.calcular(establecimientos);

		return distribucion;
	}

	/// Establece la prioridad a traves de la puntuacion de la zona, actualiza la
	/// lista y la ordena por prioridad.
	private void establecerPrioridad(List<Establecimiento> establecimientos) {

		// Seteo el puntaje como prioridad
		for (Establecimiento establecimiento : establecimientos) {
			Zona zona = establecimiento.getZona();
			establecimiento.setPrioridad((float) zona.getPuntaje());
		}

		// Creo un comparador.
		Comparator comparator = Comparator.comparing(Establecimiento::getPrioridad);

		// Orderno la lista.
		Collections.sort(establecimientos, comparator.reversed());
	}

	// Trabajo con los Puntajes Minimos y maximos de la Tupla para poder ver si el
	// establecimiento pertenece al grupo
	private boolean perteneceAlGrupo(Establecimiento establecimiento, GrupoDeDistribucion grupo) {
		return establecimiento.getPrioridad() >= grupo.getPuntajeMinimo()
				&& establecimiento.getPrioridad() <= grupo.getPuntajeMaximo();
	}

	// Metodo para obtener en que grupo esta el establecimiento
	private GrupoDeDistribucion obtenerGrupo(Establecimiento establecimiento) {
		GrupoDeDistribucion grupoEncontrado = new GrupoDeDistribucion();

		for (GrupoDeDistribucion grupo : this.grupos) {
			if (this.perteneceAlGrupo(establecimiento, grupo)) {
				grupoEncontrado = grupo;
				break;
			}
		}

		return grupoEncontrado;
	}

	// Metodo para llenar las cantidades en los grupos
	private void establecerCantidadesDeGrupo(List<Establecimiento> establecimientos) {
		for (Establecimiento establecimiento : establecimientos) {
			GrupoDeDistribucion grupo = this.obtenerGrupo(establecimiento);
			grupo.setCantidad(grupo.getCantidad() + 1);
		}
	}
}
