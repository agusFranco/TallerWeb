package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Distribucion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private TipoDistribucion tipoDistribucion;

	private LocalDate fechaDistribucion;

	private LocalDate fechaEntrega;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DistribucionDetalle> detalles;

	public Distribucion() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechaDistribucion() {
		return fechaDistribucion;
	}

	public void setFechaDistribucion(LocalDate fechaDistribucion) {
		this.fechaDistribucion = fechaDistribucion;
		this.fechaEntrega = fechaDistribucion.plusWeeks(2);
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public TipoDistribucion getTipoDistribucion() {
		return tipoDistribucion;
	}

	public void setTipoDistribucion(TipoDistribucion tipoDistribucion) {
		this.tipoDistribucion = tipoDistribucion;
	}

	public List<DistribucionDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DistribucionDetalle> detalles) {
		this.detalles = detalles;
	}

	public void addDetalle(DistribucionDetalle detalle) {
		if (this.detalles == null) {
			this.detalles = new ArrayList<DistribucionDetalle>();
		}

		this.detalles.add(detalle);
	}
}
