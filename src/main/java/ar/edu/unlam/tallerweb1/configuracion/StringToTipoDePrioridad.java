package ar.edu.unlam.tallerweb1.configuracion;

import java.beans.PropertyEditorSupport;

import ar.edu.unlam.tallerweb1.comun.enums.TipoDePrioridad;

public class StringToTipoDePrioridad extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String capitalized = text.toUpperCase();

		try {
			TipoDePrioridad prioridad = TipoDePrioridad.valueOf(capitalized);
			setValue(prioridad);
		} catch (Exception ex) {
			setValue(TipoDePrioridad.COMBINADO);
		}
	}
}