package dto;

import java.util.Set;

import model.Centro;
import model.Ensenanza;;

public class CentroCompleto {

	private Centro centro;
	private Set<Ensenanza> ensenanzas;
	
	public CentroCompleto(Centro centro, Set<Ensenanza> ensenanzas) {
		super();
		this.centro = centro;
		this.ensenanzas = ensenanzas;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public Set<Ensenanza> getEnsenanzas() {
		return ensenanzas;
	}

	public void setEnsenanzas(Set<Ensenanza> ensenanzas) {
		this.ensenanzas = ensenanzas;
	}
	
}
