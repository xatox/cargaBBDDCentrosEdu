package repository;

import org.hibernate.Session;

import model.Centro;
import model.Centros_Ensenanzas;
import model.Ensenanza;

public interface CentrosEnsenanzasRepository {

	Centros_Ensenanzas saveFetchCentrosEnsenanzas(Session session, Centro centro, Ensenanza ensenanza);
}
