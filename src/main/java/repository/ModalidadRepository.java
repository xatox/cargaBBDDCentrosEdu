package repository;

import org.hibernate.Session;

import model.Modalidad;

public interface ModalidadRepository {

	Modalidad saveFetchModalidad(Session session, String nombreModalidad);
}
