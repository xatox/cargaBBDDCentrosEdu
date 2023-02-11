package repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.DenominacionCentro;
import repository.DenominacionRepository;

public class DenominacionRepositoryImpl implements DenominacionRepository {

	@Override
	public DenominacionCentro saveFetchDenominacion(Session session, String nombreDenominacion) {
		
		Query query = session.createQuery("Select DC FROM DenominacionCentro DC WHERE DC.nombre =:denominacion");
		query.setParameter("denominacion", nombreDenominacion);
		List<DenominacionCentro> resultsDenom = query.list();
		
		DenominacionCentro denom = null;
		if (resultsDenom.isEmpty()) {
			denom = new DenominacionCentro();
			denom.setNombre(nombreDenominacion);
			
			int id = (int) session.save(denom);
			denom.setId(id);
			//resultsDenom = query.list();
			//denom = resultsDenom.get(0);
		} else {
			denom = resultsDenom.get(0);
		}
		
		return denom;
	}

}
