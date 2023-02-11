package repository;

import org.hibernate.Session;

import model.TipoCentro;

public interface TipoCentroRepository {

	TipoCentro saveFetchTipoCentro(Session session, String nombreTipo);
}
