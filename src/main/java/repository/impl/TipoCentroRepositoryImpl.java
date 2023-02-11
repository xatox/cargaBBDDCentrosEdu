package repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.TipoCentro;
import repository.TipoCentroRepository;

public class TipoCentroRepositoryImpl implements TipoCentroRepository {

	@Override
	public TipoCentro saveFetchTipoCentro(Session session, String nombreTipo) {
		
		Query query = session.createQuery("Select TC FROM TipoCentro TC WHERE TC.nombre=:tipo");
		query.setParameter("tipo", nombreTipo);
		List<TipoCentro> resultsTip = query.list();
		
		TipoCentro tip = null;
		if (resultsTip.isEmpty()) {
			tip = new TipoCentro();
			tip.setNombre(nombreTipo);
			
			int id = (int) session.save(tip);
			tip.setId(id);
			//resultsTip = query.list();
			//tip = resultsTip.get(0);
		} else {
			tip = resultsTip.get(0);
		}
		
		return tip;
	}

}
