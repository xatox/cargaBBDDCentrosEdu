package repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Familia;
import repository.FamiliaRepository;

public class FamiliaRepositoryImpl implements FamiliaRepository {

	@Override
	public Familia saveFetchFamilia(Session session, String nombreFamilia) {
		
		Query query = session.createQuery("Select F FROM Familia F WHERE F.nombre=:familia");
		query.setParameter("familia", nombreFamilia);
		List<Familia> resultsFami = query.list();
		
		Familia fam = null;
		if (resultsFami.isEmpty()) {
			fam = new Familia();
			fam.setNombre(nombreFamilia);
			
			int id = (int) session.save(fam);
			fam.setId(id);
			
			//resultsFami = query.list();
			//fam = resultsFami.get(0);
		} else {
			fam = resultsFami.get(0);
		}
		
		return fam;
	}

}
