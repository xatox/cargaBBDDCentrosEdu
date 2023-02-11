package repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Grado;
import repository.GradoRepository;

public class GradoRepositoryImpl implements GradoRepository {

	@Override
	public Grado saveFetchGrado(Session session, String nombreGrado) {
		
		Query query = session.createQuery("Select G FROM Grado G WHERE G.nombre=:grado");
		query.setParameter("grado", nombreGrado);
		List<Grado> resultsGrado = query.list();
		
		Grado gra = null;
		if (resultsGrado.isEmpty()) {
			gra = new Grado();
			gra.setNombre(nombreGrado);
			
			int id = (int) session.save(gra);
			gra.setId(id);
			//resultsGrado = query.list();
			//gra = resultsGrado.get(0);
		} else {
			gra = resultsGrado.get(0);
		}
		
		return gra;
	}

}
