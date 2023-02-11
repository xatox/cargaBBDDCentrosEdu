package repository;

import org.hibernate.Session;

import model.Familia;

public interface FamiliaRepository {

	Familia saveFetchFamilia (Session session, String nombreFamilia);
}
