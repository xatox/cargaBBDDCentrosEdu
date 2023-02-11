package repository;

import java.util.Set;

import org.hibernate.Session;

import model.Centro;
import model.DenominacionCentro;
import model.Ensenanza;
import model.NaturalezaCentro;
import model.Provincia;
import model.TipoCentro;

public interface CentroRepository {

	Centro insertaCentro (Session session, String codigo, String nombre, String telefono, String fax, String email, String web,
			String municipio, String localidad, String direccion, String codigoPostal, Provincia provincia,
			NaturalezaCentro naturalezaCentro, TipoCentro tipoCentro, DenominacionCentro denominacionCentro, boolean concertado, Set<Ensenanza> ensenanzas);
	
	boolean existeCentro(Session session, String idCentro);
	
	int insertaDatosCentroCompleto (Session session, Centro centro, Set<Ensenanza> ensenanzas) throws Exception;
}
