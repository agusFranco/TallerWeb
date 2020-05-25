package ar.edu.unlam.tallerweb1.comun.enums;

public enum TipoDePrioridad {
	COMBINADO("combinado"), OCUPACION("ocupacion"), CAPACIDAD("capacidad"), ZONA("zona");

	private String tipo;

	TipoDePrioridad(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return this.tipo;
	}
}
