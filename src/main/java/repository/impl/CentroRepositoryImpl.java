package repository.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Centro;
import model.ComunidadAutonoma;
import model.DenominacionCentro;
import model.Ensenanza;
import model.Familia;
import model.Grado;
import model.Modalidad;
import model.NaturalezaCentro;
import model.Provincia;
import model.TipoCentro;
import repository.CentroRepository;

public class CentroRepositoryImpl implements CentroRepository {

	//Repositorios
	private ComunidadRepositoryImpl repoComunidad;
	private ProvinciaRepositoryImpl repoProvincia;
	private NaturalezaRepositoryImpl repoNaturaleza;
	private TipoCentroRepositoryImpl repoTipoCentro;
	private DenominacionRepositoryImpl repoDenominacion;
	private GradoRepositoryImpl repoGrado;
	private FamiliaRepositoryImpl repoFamilia;
	private ModalidadRepositoryImpl repoModalidad;
	private EnsenanzaRepositoryImpl repoEnsenanza;
	private CentrosEnsenanzasRepositoryImpl repoCentrosEnsenanzas;
	
	//Evitar consultas innecesarias
	private ComunidadAutonoma comunidadAutonoma;
	private Provincia provincia;
	private List<NaturalezaCentro> listaNaturalezas;
	private List<DenominacionCentro> listaDenominaciones;
	private List<TipoCentro> listaTipos;
	private List<Grado> listaGrados;
	private List<Familia> listaFamilias;
	private List<Modalidad> listaModalidades;
	private List<Ensenanza> listaEnsenanzas;
	
	public CentroRepositoryImpl() {
		this.repoComunidad = new ComunidadRepositoryImpl();
		this.repoDenominacion = new DenominacionRepositoryImpl();
		this.repoEnsenanza = new EnsenanzaRepositoryImpl();
		this.repoFamilia = new FamiliaRepositoryImpl();
		this.repoGrado = new GradoRepositoryImpl();
		this.repoModalidad = new ModalidadRepositoryImpl();
		this.repoNaturaleza = new NaturalezaRepositoryImpl();
		this.repoProvincia = new ProvinciaRepositoryImpl();
		this.repoTipoCentro = new TipoCentroRepositoryImpl();
		this.repoCentrosEnsenanzas = new CentrosEnsenanzasRepositoryImpl();
		
		this.listaNaturalezas = new ArrayList<NaturalezaCentro>();
		this.listaDenominaciones = new ArrayList<DenominacionCentro>();
		this.listaTipos = new ArrayList<TipoCentro>();
		this.listaGrados = new ArrayList<Grado>();
		this.listaFamilias = new ArrayList<Familia>();
		this.listaModalidades = new ArrayList<Modalidad>();
		this.listaEnsenanzas = new ArrayList<Ensenanza>();
	}
	
	@Override
	public Centro insertaCentro(Session session, String codigo, String nombre, String telefono, String fax,
			String email, String web, String municipio, String localidad, String direccion, String codigoPostal,
			Provincia provincia, NaturalezaCentro naturalezaCentro, TipoCentro tipoCentro,
			DenominacionCentro denominacionCentro, boolean concertado, Set<Ensenanza> ensenanzas) {
		
		
		Centro centro = new Centro();
		centro.setCodigo(codigo);
		centro.setCodigoPostal(codigoPostal);
		centro.setDenominacionCentro(denominacionCentro);
		centro.setDireccion(direccion);
		centro.setEmail(email);
		centro.setFax(fax);
		centro.setLocalidad(localidad);
		centro.setMunicipio(municipio);
		centro.setNaturalezaCentro(naturalezaCentro);
		centro.setNombre(nombre);
		centro.setProvincia(provincia);
		centro.setTelefono(telefono);
		centro.setTipoCentro(tipoCentro);
		centro.setWeb(web);
		centro.setConcertado(concertado);
		//centro.setEnsenanzas(ensenanzas);
			
		int id = (int) session.save(centro);
		centro.setId(id);
		
		return centro;
	}

	@Override
	public boolean existeCentro(Session session, String idCentro) {
		Query query = session.createQuery("Select C FROM Centro C WHERE C.codigo = '" + idCentro + "'");
		List<Centro> results = query.list();
		if (results.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int insertaDatosCentroCompleto(Session session, Centro centro, Set<Ensenanza> ensenanzas) throws Exception{
		
		session.beginTransaction();
		
		if (esNecesarioConsultarProvincia(centro.getProvincia().getNombre())) {
			//Si es necesario consultar pues consultamos la provincia, pero primero deberemos insertar la comunidad si es nula
			if (comunidadAutonoma == null) {
				comunidadAutonoma = repoComunidad.saveFetchComunidad(session, centro.getProvincia().getComunidadAutonoma().getNombre().toString());
			} 
			provincia = repoProvincia.saveFetchProvincia(session, centro.getProvincia().getNombre().toString(), comunidadAutonoma);
			
			// y miramos si es necesario consultar la provincia
			if (esNecesarioConsultarComunidad(centro.getProvincia().getComunidadAutonoma().getNombre())) {
				comunidadAutonoma = repoComunidad.saveFetchComunidad(session, centro.getProvincia().getComunidadAutonoma().getNombre().toString());
			}
		}
		//Sino es necesario consultar provincia, por ende, no es necesario consultar comunidad (no hay 2 provincias iguales)

		//para ahorrar consultas, y por tanto tiempo, guardaremos los objetos en listas y preguntaremos sobre las listas en lugar de hacer consultas
		DenominacionCentro denominacionCentro = null;
		if (listaDenominaciones.contains(centro.getDenominacionCentro())) {
			denominacionCentro = listaDenominaciones.get(listaDenominaciones.indexOf(centro.getDenominacionCentro())); 
		} else {
			denominacionCentro = repoDenominacion.saveFetchDenominacion(session, centro.getDenominacionCentro().getNombre().toString());
			listaDenominaciones.add(denominacionCentro);
		}
		
		NaturalezaCentro naturalezaCentro = null;
		if (listaNaturalezas.contains(centro.getNaturalezaCentro())) {
			naturalezaCentro = listaNaturalezas.get(listaNaturalezas.indexOf(centro.getNaturalezaCentro())); 
		} else {
			naturalezaCentro = repoNaturaleza.saveFecthNaturaleza(session, centro.getNaturalezaCentro().getNombre().toString());
			listaNaturalezas.add(naturalezaCentro);
		}
		TipoCentro tipoCentro = null;
		if (listaTipos.contains(centro.getTipoCentro())) {
			tipoCentro = listaTipos.get(listaTipos.indexOf(centro.getTipoCentro())); 
		} else {
			tipoCentro = repoTipoCentro.saveFetchTipoCentro(session, centro.getTipoCentro().getNombre().toString());
			listaTipos.add(tipoCentro);
		}
		//comunidadAutonoma = repoComunidad.saveFetchComunidad(session, centro.getProvincia().getComunidadAutonoma().getNombre().toString());
		//provincia = repoProvincia.saveFetchProvincia(session, centro.getProvincia().getNombre().toString(), comunidadAutonoma);
		//DenominacionCentro denominacionCentro = repoDenominacion.saveFetchDenominacion(session, centro.getDenominacionCentro().getNombre().toString());
		//NaturalezaCentro naturalezaCentro = repoNaturaleza.saveFecthNaturaleza(session, centro.getNaturalezaCentro().getNombre().toString());
		//TipoCentro tipoCentro = repoTipoCentro.saveFetchTipoCentro(session, centro.getTipoCentro().getNombre().toString());
		
		Set<Ensenanza> ensenanzasTratado = new HashSet<Ensenanza>();
		
		for (Ensenanza ense : ensenanzas) {
			Grado grado = null;
			if (null != ense.getGrado()) {
				if (listaGrados.contains(ense.getGrado())) {
					grado = listaGrados.get(listaGrados.indexOf(ense.getGrado())); 
				} else {
					grado = repoGrado.saveFetchGrado(session, ense.getGrado().getNombre());
					listaGrados.add(grado);
				}
			}
			
			Familia	familia = null;
			if (null != ense.getFamilia()) {
				if (listaFamilias.contains(ense.getFamilia())) {
					familia = listaFamilias.get(listaFamilias.indexOf(ense.getFamilia())); 
				} else {
					familia = repoFamilia.saveFetchFamilia(session, ense.getFamilia().getNombre());
					listaFamilias.add(familia);
				}
			}
			
			Modalidad modalidad = null;
			if (null != ense.getModalidad()) {
				if (listaModalidades.contains(ense.getModalidad())) {
					modalidad = listaModalidades.get(listaModalidades.indexOf(ense.getModalidad())); 
				} else {
					modalidad = repoModalidad.saveFetchModalidad(session, ense.getModalidad().getNombre());
					listaModalidades.add(modalidad);
				}
			}
			
			Ensenanza ensenanza = null;
			if (null != ense) {
				if (listaEnsenanzas.contains(ense)) {
					ensenanza = listaEnsenanzas.get(listaEnsenanzas.indexOf(ense)); 
				} else {
					ensenanza = repoEnsenanza.saveFetchEnsenanza(session, ense.getNombre(), grado, familia, modalidad);
					listaEnsenanzas.add(ensenanza);
				}
			}
			
			ensenanzasTratado.add(ensenanza);
		}
		
		centro = insertaCentro(session, centro.getCodigo(), centro.getNombre(), centro.getTelefono(), centro.getFax(), centro.getEmail(), centro.getWeb(), 
				centro.getMunicipio(), centro.getLocalidad(), centro.getDireccion(), centro.getCodigoPostal(), provincia, naturalezaCentro, tipoCentro, denominacionCentro, 
				centro.isConcertado(), ensenanzasTratado);
		
		for (Ensenanza ense : ensenanzasTratado) {
			
			repoCentrosEnsenanzas.saveFetchCentrosEnsenanzas(session, centro, ense);
			
		}
		
		session.getTransaction().commit();
		//session.flush();
		
		return centro.getId();
	}
	
	private boolean esNecesarioConsultarProvincia (String nombreProvincia) {
		if (provincia != null) {
			if (provincia.getNombre().equals(nombreProvincia)) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
		
	}

	private boolean esNecesarioConsultarComunidad (String nombreComunidad) {
		if (comunidadAutonoma != null && provincia != null) {
			if (provincia.getComunidadAutonoma().getNombre().equals(nombreComunidad)) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}
}
