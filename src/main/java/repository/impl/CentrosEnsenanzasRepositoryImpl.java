package repository.impl;

import org.hibernate.Session;

import model.Centro;
import model.Centros_Ensenanzas;
import model.Ensenanza;
import repository.CentrosEnsenanzasRepository;

public class CentrosEnsenanzasRepositoryImpl implements CentrosEnsenanzasRepository{

	@Override
	public Centros_Ensenanzas saveFetchCentrosEnsenanzas(Session session, Centro centro, Ensenanza ensenanza) {
		
		/*Query query = session.createQuery("Select CE FROM Centros_Ensenanzas CE WHERE CE.centro.id=:centro AND CE.ensenanza.id=:ensenanza");
		query.setParameter("centro", centro.getId());
		query.setParameter("ensenanza", ensenanza.getId());
		List<Centros_Ensenanzas> results = query.list();*/
		
		Centros_Ensenanzas ce = null;
		//if (results.isEmpty()) {
			ce = new Centros_Ensenanzas();
			ce.setCentro(centro);
			ce.setEnsenanza(ensenanza);
			
			int id = (int) session.save(ce);
			ce.setId(id);
			//resultsProv = query.list();
			//prov = resultsProv.get(0);
		/*} else {
			ce = results.get(0);
		}*/
		
		return ce;
	}

}
