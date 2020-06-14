package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DistribucionDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate fechaDistribucion;
	private LocalDate fechaEntrega;

	@ManyToOne
	private TipoDistribucion tipoDistribucion;

	public DistribucionDetalle() {
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

}
