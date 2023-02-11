package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Centros_Ensenanzas")
public class Centros_Ensenanzas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4179663944823919168L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "idCentro")
	private Centro centro;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "idEnsenanza")
	private Ensenanza ensenanza;
	
	public Centros_Ensenanzas() {}

	public Centros_Ensenanzas(int id, Centro centro, Ensenanza ensenanza) {
		super();
		this.id = id;
		this.centro = centro;
		this.ensenanza = ensenanza;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public Ensenanza getEnsenanza() {
		return ensenanza;
	}

	public void setEnsenanza(Ensenanza ensenanza) {
		this.ensenanza = ensenanza;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centro == null) ? 0 : centro.hashCode());
		result = prime * result + ((ensenanza == null) ? 0 : ensenanza.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Centros_Ensenanzas other = (Centros_Ensenanzas) obj;
		if (centro == null) {
			if (other.centro != null)
				return false;
		} else if (!centro.equals(other.centro))
			return false;
		if (ensenanza == null) {
			if (other.ensenanza != null)
				return false;
		} else if (!ensenanza.equals(other.ensenanza))
			return false;
		return true;
	}
	
	
}
