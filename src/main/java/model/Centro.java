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
@Table(name="Centros")
public class Centro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5134404688159542545L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="fax")
	private String fax;
	
	@Column(name="email")
	private String email;
	
	@Column(name="web")
	private String web;
	
	@Column(name="municipio")
	private String municipio;
	
	@Column(name="localidad")
	private String localidad;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="codigoPostal")
	private String codigoPostal;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "idProvincia")
	private Provincia provincia;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "idNaturaleza")
	private NaturalezaCentro naturalezaCentro;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "idTipoCentro")
	private TipoCentro tipoCentro;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "idDenominacion")
	private DenominacionCentro denominacionCentro;
	
	@Column(name="concertado", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean concertado;
	
	public Centro() {}

	public Centro(int id, String codigo, String nombre, String telefono, String fax, String email, String web,
			String municipio, String localidad, String direccion, String codigoPostal, Provincia provincia,
			NaturalezaCentro naturalezaCentro, TipoCentro tipoCentro, DenominacionCentro denominacionCentro, boolean concertado) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.telefono = telefono;
		this.fax = fax;
		this.email = email;
		this.web = web;
		this.municipio = municipio;
		this.localidad = localidad;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
		this.naturalezaCentro = naturalezaCentro;
		this.tipoCentro = tipoCentro;
		this.denominacionCentro = denominacionCentro;
		this.concertado = concertado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public NaturalezaCentro getNaturalezaCentro() {
		return naturalezaCentro;
	}

	public void setNaturalezaCentro(NaturalezaCentro naturalezaCentro) {
		this.naturalezaCentro = naturalezaCentro;
	}

	public TipoCentro getTipoCentro() {
		return tipoCentro;
	}

	public void setTipoCentro(TipoCentro tipoCentro) {
		this.tipoCentro = tipoCentro;
	}

	public DenominacionCentro getDenominacionCentro() {
		return denominacionCentro;
	}

	public void setDenominacionCentro(DenominacionCentro denominacionCentro) {
		this.denominacionCentro = denominacionCentro;
	}

	public boolean isConcertado() {
		return concertado;
	}

	public void setConcertado(boolean concertado) {
		this.concertado = concertado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
		result = prime * result + (concertado ? 1231 : 1237);
		result = prime * result + ((denominacionCentro == null) ? 0 : denominacionCentro.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + id;
		result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result + ((municipio == null) ? 0 : municipio.hashCode());
		result = prime * result + ((naturalezaCentro == null) ? 0 : naturalezaCentro.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((tipoCentro == null) ? 0 : tipoCentro.hashCode());
		result = prime * result + ((web == null) ? 0 : web.hashCode());
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
		Centro other = (Centro) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoPostal == null) {
			if (other.codigoPostal != null)
				return false;
		} else if (!codigoPostal.equals(other.codigoPostal))
			return false;
		if (concertado != other.concertado)
			return false;
		if (denominacionCentro == null) {
			if (other.denominacionCentro != null)
				return false;
		} else if (!denominacionCentro.equals(other.denominacionCentro))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (id != other.id)
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (municipio == null) {
			if (other.municipio != null)
				return false;
		} else if (!municipio.equals(other.municipio))
			return false;
		if (naturalezaCentro == null) {
			if (other.naturalezaCentro != null)
				return false;
		} else if (!naturalezaCentro.equals(other.naturalezaCentro))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (tipoCentro == null) {
			if (other.tipoCentro != null)
				return false;
		} else if (!tipoCentro.equals(other.tipoCentro))
			return false;
		if (web == null) {
			if (other.web != null)
				return false;
		} else if (!web.equals(other.web))
			return false;
		return true;
	}

	
}
