package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
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
	
	private Integer cantidad;
	
	@ManyToOne
	private Establecimiento establecimiento;
	@ManyToOne
	private Insumo insumo;
	//This happens because you have a collection in your entity, and that collection has one or more items which are not present in the database. By specifying the above options you tell hibernate to save them to the database when saving their parent.
	@ManyToOne(cascade = CascadeType.ALL)
	private DistribucionDetalle distribucionDetalle;
	
	
	
	
	
	// Constructor vacío
	public Distribucion() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public DistribucionDetalle getDistribucionDetalle() {
		return distribucionDetalle;
	}

	public void setDistribucionDetalle(DistribucionDetalle distribucionDetalle) {
		this.distribucionDetalle = distribucionDetalle;
	}

}
