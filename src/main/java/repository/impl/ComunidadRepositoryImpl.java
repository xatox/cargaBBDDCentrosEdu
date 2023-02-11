package repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.ComunidadAutonoma;
import repository.ComunidadRepository;

public class ComunidadRepositoryImpl implements ComunidadRepository{

	@Override
	public ComunidadAutonoma saveFetchComunidad(Session session, String nombreComunidad) {
		
		Query query = session.createQuery("Select CA FROM ComunidadAutonoma CA WHERE CA.nombre=:comunidad ");
		query.setParameter("comunidad", nombreComunidad);
		List<ComunidadAutonoma> resultsComs = query.list();
		
		ComunidadAutonoma com = null;
		if (resultsComs.isEmpty()) {
			com = new ComunidadAutonoma();
			com.setNombre(nombreComunidad);
			
			int id = (int) session.save(com);
			com.setId(id);
			
			//resultsComs = query.list();
			//com = resultsComs.get(0);
		} else {
			com = resultsComs.get(0);
		}
		
		return com;
	}

}
