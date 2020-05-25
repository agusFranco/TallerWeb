package ar.edu.unlam.tallerweb1.comun.enums;

public enum TipoDePrioridad {
	COMBINADO("combinado"), OCUPACION("ocupacion"), CAPACIDAD("capacidad"), ZONA("zona");

	private String tipo;

	// Constructor privado en los enum	
	private TipoDePrioridad(String tipo) {
		this.tipo = tipo;
	}

	//Los enum solo tienen getters
	
	public String getTipo() {
		return this.tipo;
	}
}
