package repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Ensenanza;
import model.Familia;
import model.Grado;
import model.Modalidad;
import repository.EnsenanzaRepository;

public class EnsenanzaRepositoryImpl implements EnsenanzaRepository {

	@Override
	public Ensenanza saveFetchEnsenanza(Session session, String nombreEnsenanza, Grado grado, Familia familia,
			Modalidad modalidad) {
		
		boolean tieneFamilia = false;
		boolean tieneModalidad = false;
		boolean tieneGrado = false;
		
		//Query query = session.createQuery("Select E FROM Ensenanza E WHERE E.nombre=:ensenanza AND E.familia.id=:familia AND E.modalidad.id=:modalidad AND E.grado.id=:grado");
		String consulta = "Select E FROM Ensenanza E WHERE E.nombre=:ensenanza";
		//query.setParameter("ensenanza", nombreEnsenanza);
		if (null == familia) {
			consulta += " AND E.familia is null";
			//query.setParameter("familia", null);
		} else {
			consulta += " AND E.familia.id=:familia";
			tieneFamilia = true;
			//query.setParameter("familia", familia.getId());
		}
		if (null == modalidad) {
			consulta += " AND E.modalidad is null";
			//query.setParameter("modalidad", null);
		} else {
			consulta += " AND E.modalidad.id=:modalidad";
			tieneModalidad = true;
			//query.setParameter("modalidad", modalidad.getId());
		}
		if (null == grado) {
			consulta += " AND E.grado is null";
			//query.setParameter("grado", null);
		} else {
			consulta += " AND E.grado.id=:grado";
			tieneGrado = true;
			//query.setParameter("grado", grado.getId());
		}
		
		Query query = session.createQuery(consulta);
		query.setParameter("ensenanza", nombreEnsenanza);
		if (tieneFamilia) {
			query.setParameter("familia", familia.getId());
		}
		if (tieneModalidad) {
			query.setParameter("modalidad", modalidad.getId());
		}
		if(tieneGrado) {
			query.setParameter("grado", grado.getId());
		}
		List<Ensenanza> resultsEnse = query.list();
		
		Ensenanza ensenanza = null;;
		if (resultsEnse.isEmpty()) {
			ensenanza = new Ensenanza();
			ensenanza.setNombre(nombreEnsenanza);
			ensenanza.setFamilia(familia);
			ensenanza.setModalidad(modalidad);
			ensenanza.setGrado(grado);
			
			int id = (int) session.save(ensenanza);
			//ensenanza.setId(id);
			
			resultsEnse = query.list();
			ensenanza = resultsEnse.get(0);
		} else {
			ensenanza = resultsEnse.get(0);
		}
		
		return ensenanza;
	}

}
