package repository;

import org.hibernate.Session;

import model.NaturalezaCentro;

public interface NaturalezaRepository {

	NaturalezaCentro saveFecthNaturaleza(Session session, String nombreNaturaleza);
}
