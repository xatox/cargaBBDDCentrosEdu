package repository;

import org.hibernate.Session;

import model.ComunidadAutonoma;

public interface ComunidadRepository {

	ComunidadAutonoma saveFetchComunidad(Session session, String nombreComunidad);
}
