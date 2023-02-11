package repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.NaturalezaCentro;
import repository.NaturalezaRepository;

public class NaturalezaRepositoryImpl implements NaturalezaRepository {

	@Override
	public NaturalezaCentro saveFecthNaturaleza(Session session, String nombreNaturaleza) {
		
		Query query = session.createQuery("Select NC FROM NaturalezaCentro NC WHERE NC.nombre=:naturaleza");
		query.setParameter("naturaleza", nombreNaturaleza);
		List<NaturalezaCentro> resultsNatur = query.list();
		
		NaturalezaCentro natur = null;
		if (resultsNatur.isEmpty()) {
			natur = new NaturalezaCentro();
			natur.setNombre(nombreNaturaleza);
			
			int id = (int) session.save(natur);
			natur.setId(id);
			//resultsNatur = query.list();
			//natur = resultsNatur.get(0);
		} else {
			natur = resultsNatur.get(0);
		}
		
		return natur;
	}

}
