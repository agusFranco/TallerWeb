package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Establecimientos {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO , generator="native")
	
	private Long id;
	private String nombreDelEstablecimiento;
	private String ubicacionEstablecimiento;
	private Integer capacidadDelEstablecimiento;
	private Integer indiceDeRiesgo;
	private String zonaDeRiesgo;
	private String responsableAdministrativo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreDelEstablecimiento() {
		return nombreDelEstablecimiento;
	}
	public void setNombreDelEstablecimiento(String nombreDelEstablecimiento) {
		this.nombreDelEstablecimiento = nombreDelEstablecimiento;
	}
	public String getUbicacionEstablecimiento() {
		return ubicacionEstablecimiento;
	}
	public void setUbicacionEstablecimiento(String ubicacionEstablecimiento) {
		this.ubicacionEstablecimiento = ubicacionEstablecimiento;
	}
	public Integer getCapacidadDelEstablecimiento() {
		return capacidadDelEstablecimiento;
	}
	public void setCapacidadDelEstablecimiento(Integer capacidadDelEstablecimiento) {
		this.capacidadDelEstablecimiento = capacidadDelEstablecimiento;
	}
	public Integer getIndiceDeRiesgo() {
		return indiceDeRiesgo;
	}
	public void setIndiceDeRiesgo(Integer indiceDeRiesgo) {
		this.indiceDeRiesgo = indiceDeRiesgo;
	}
	public String getZonaDeRiesgo() {
		return zonaDeRiesgo;
	}
	public void setZonaDeRiesgo(String zonaDeRiesgo) {
		this.zonaDeRiesgo = zonaDeRiesgo;
	}
	public String getResponsableAdministrativo() {
		return responsableAdministrativo;
	}
	public void setResponsableAdministrativo(String responsableAdministrativo) {
		this.responsableAdministrativo = responsableAdministrativo;
	}
	
	

}
