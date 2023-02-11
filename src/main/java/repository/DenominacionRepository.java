package repository;

import org.hibernate.Session;

import model.DenominacionCentro;

public interface DenominacionRepository {

	DenominacionCentro saveFetchDenominacion (Session session, String nombreDenominacion);
}
