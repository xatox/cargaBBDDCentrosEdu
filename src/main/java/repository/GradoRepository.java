package repository;

import org.hibernate.Session;

import model.Grado;

public interface GradoRepository {

	Grado saveFetchGrado (Session session, String nombreGrado);
}
