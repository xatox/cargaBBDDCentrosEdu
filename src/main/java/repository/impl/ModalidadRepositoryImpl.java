package repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Modalidad;
import repository.ModalidadRepository;

public class ModalidadRepositoryImpl implements ModalidadRepository {

	@Override
	public Modalidad saveFetchModalidad(Session session, String nombreModalidad) {
		
		Query query = session.createQuery("Select M FROM Modalidad M WHERE M.nombre=:modalidad");
		query.setParameter("modalidad", nombreModalidad);
		List<Modalidad> resultsMod = query.list();
		
		Modalidad mod = null;
		if (resultsMod.isEmpty()) {
			mod = new Modalidad();
			mod.setNombre(nombreModalidad);
			
			int id = (int) session.save(mod);
			mod.setId(id);
			//resultsMod = query.list();
			//mod = resultsMod.get(0);
		} else {
			mod = resultsMod.get(0);
		}
		
		return mod;
	}

}
