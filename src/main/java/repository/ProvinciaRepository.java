package repository;

import org.hibernate.Session;

import model.ComunidadAutonoma;
import model.Provincia;

public interface ProvinciaRepository {

	Provincia saveFetchProvincia(Session session, String nombreProvincia, ComunidadAutonoma ca);
}
