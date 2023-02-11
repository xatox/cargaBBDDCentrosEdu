package repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.ComunidadAutonoma;
import model.Provincia;
import repository.ProvinciaRepository;

public class ProvinciaRepositoryImpl implements ProvinciaRepository {

	@Override
	public Provincia saveFetchProvincia(Session session, String nombreProvincia, ComunidadAutonoma ca) {
		
		Query query = session.createQuery("Select P FROM Provincia P WHERE P.nombre=:provincia");
		query.setParameter("provincia", nombreProvincia);
		List<Provincia> resultsProv = query.list();
		
		Provincia prov = null;
		if (resultsProv.isEmpty()) {
			prov = new Provincia();
			prov.setNombre(nombreProvincia);
			prov.setComunidadAutonoma(ca);
			
			int id = (int) session.save(prov);
			prov.setId(id);
			//resultsProv = query.list();
			//prov = resultsProv.get(0);
		} else {
			prov = resultsProv.get(0);
		}
		
		return prov;
	}

}
