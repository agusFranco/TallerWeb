package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Distribucion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer numeroDistribucion;
	private Integer cantidad;
	
	
	@ManyToOne
	private Establecimiento establecimiento;
	@ManyToOne
	private Insumo insumo;
	
	// Constructor vacío
	public Distribucion() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroDistribucion() {
		return numeroDistribucion;
	}

	public void setNumeroDistribucion(Integer numeroDistribucion) {
		this.numeroDistribucion = numeroDistribucion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}



}
