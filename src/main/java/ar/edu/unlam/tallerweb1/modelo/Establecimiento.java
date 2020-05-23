package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.edu.unlam.tallerweb1.modelo.otros.Zona;

@Entity
public class Establecimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String ubicacion;
<<<<<<< HEAD
	private Integer capacidad;
	private Integer indice;
=======
	private String zona;
	private Integer capacidad;
	private Integer ocupacion;
	private Integer prioridad;
>>>>>>> 48728e5054ab85fe3cac4ece8d4e982ba780df8c
	private String responsable;
	private String zona;
	
	public Establecimiento() {
	}

	public Establecimiento(String nombre, String ubicacion, Integer capacidad, Integer prioridad, String zona,
			String responsable) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
<<<<<<< HEAD
		this.indice = indice;
=======
		this.prioridad = prioridad;
		this.zona = zona;
>>>>>>> 48728e5054ab85fe3cac4ece8d4e982ba780df8c
		this.responsable = responsable;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public String getZona() {
		return zona;
	}

	public Integer getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Integer ocupacion) {
		this.ocupacion = ocupacion;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	
	public Integer calcularPrioridadOcupacion(Integer capacidad, Integer ocupacion) {
		return capacidad/ocupacion;
	}
}
