package repository;

import org.hibernate.Session;

import model.Ensenanza;
import model.Familia;
import model.Grado;
import model.Modalidad;

public interface EnsenanzaRepository {

	Ensenanza saveFetchEnsenanza(Session session, String nombreEnsenanza, Grado grado, Familia familia, Modalidad modalidad);
}
