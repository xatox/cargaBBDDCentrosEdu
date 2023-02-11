package util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import java.util.Map.Entry;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import dto.CentroCompleto;
import model.ComunidadAutonoma;
import model.DenominacionCentro;
import model.NaturalezaCentro;
import model.Provincia;
import model.TipoCentro;
import repository.CentroRepository;
import repository.impl.CentroRepositoryImpl;
import model.Centro;
import model.Ensenanza;

public class SimuladorBuscador {

	private File buscadorForm = new File("file:src//main//resources//buscadorCentros.html");
	private File buscadorPorCodigoForm = new File("file:src//main//resources//buscadorCentrosPorCodigo.html");
	
	private HibernateUtil hibernateUtil;
	private CentroRepositoryImpl repoCentro;
	
	private Cronometro crono;
	
	public void start() {
		
		comprobacionesIniciales();
		
		MyHtmlUnitUtil mhuuBuscadorForm = new MyHtmlUnitUtil(buscadorForm.getPath());
		MyHtmlUnitUtil mhuuPorCodigoForm = new MyHtmlUnitUtil(buscadorPorCodigoForm.getPath());

		try {
			System.out.println("- Obteniendo listado de centros");
			HtmlPage listadoPage = mhuuBuscadorForm.obtenerListadoCentros();
			HtmlPage listadoPage2 = mhuuBuscadorForm.aceptarRestriccion(listadoPage);
			System.out.println("- Listado obtenido satisfactoriamente, obteniendo codigos de centros");
			List<String> listaCodigos = FicherosUtil.obtenerCodigosCentrosDesdeHtml(listadoPage2.asXml());
			System.out.println(listaCodigos.size() + " codigos obtenidos");
			
			int monitor = 0;
			HashMap<Integer, CentroCompleto> mapaCentrosCompleto = new HashMap<Integer, CentroCompleto>();
			for (int x = 0; x < listaCodigos.size(); x++) {
				try {
					
					HtmlPage paginaCentro = mhuuPorCodigoForm.obtenerPaginaCentro(listaCodigos.get(x));
					Centro centro = convierteMapaACentro(mhuuPorCodigoForm.obtenerCamposDesdeTexto(paginaCentro));
					Set<Ensenanza> ensenanzas =  mhuuPorCodigoForm.obtenerCamposEnsenanzas(paginaCentro);
					mapaCentrosCompleto.put(x, new CentroCompleto(centro, ensenanzas));
					
					monitor = x + 1;
					System.out.println("Centro " + monitor + " de " + listaCodigos.size() + " Nombre Centro: " + centro.getNombre() + 
							"\t\t\t Provincia: " + centro.getProvincia().getNombre());
					
				} catch (Exception e) {
					e.printStackTrace();
					//listaCentrosKO.add(listaCodigos.get(x));
				}
			}
			
			repoCentro = new CentroRepositoryImpl();
			//Volcado de datos
			Set<Entry<Integer, CentroCompleto>> set = mapaCentrosCompleto.entrySet();
		    Iterator<Entry<Integer, CentroCompleto>> iterator = set.iterator();
		    int contadorSession = 0;
		    Session session = hibernateUtil.getSession();
	    	while(iterator.hasNext()) {
	    		try {
	    			Map.Entry<Integer, CentroCompleto> mentry = (Map.Entry<Integer, CentroCompleto>)iterator.next();
	    			int orden = mentry.getKey();
	    			CentroCompleto centroCompleto = mentry.getValue();
				    repoCentro.insertaDatosCentroCompleto(session, centroCompleto.getCentro(), centroCompleto.getEnsenanzas());
				    if (contadorSession == 500) {
						session = hibernateUtil.reiniciarSession();
						contadorSession = 0;
					} else {
						contadorSession++;
					}
				    System.out.println(orden + " de " + mapaCentrosCompleto.size() );
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
		    }

	    	hibernateUtil.cerrarSession();
			
	    	System.out.println("- Volcado de datos finalizado");
	    	
		    /*if (listaCentrosKO.isEmpty()) {
				System.out.println("- PROCESO DE CARGA DE DATOS DE CENTROS FINALIZADO SATISFACTORIAMENTE");
			} else {
				System.out.println("- PROCESO DE CARGA DE DATOS DE CENTROS FINALIZADO PERO CON ERRORES");
				System.out.println("- CENTROS CON ERRORES:");
				
				for (String centroKO : listaCentrosKO) {
					System.out.println("centroKO: " + centroKO);
				}
			}*/
		    
			System.out.println("- Tiempo total de ejecucion: " + crono.getTiempoRestante(crono.finish()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void comprobacionesIniciales() {
		System.out.println("- Cargando session hibernate");
		hibernateUtil = new HibernateUtil();
		hibernateUtil.getSession();
		
		crono = new Cronometro();
	}
	
	private static Centro convierteMapaACentro(HashMap<String, String> mapa) {
		
		Centro centro = null;
		
		String codigoCentro = null, nombreCentro = null, telefono = null, fax = null, email = null, web = null, nombreComunidad = null, nombreProvincia = null, 
				nombreMunicipio = null, nombreLocalidad = null, domicilio = null, codigoPostal = null, nombreNaturaleza = null, nombreTipoCentro = null, nombreDenominacion = null, 
				concertado = null;
		
		boolean concert = false;
		
		Set set = mapa.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry)iterator.next();
			
			if (mentry.getKey().equals("codigoCentro")) {
				codigoCentro = mentry.getValue().toString();
			} else if (mentry.getKey().equals("nombreCentro")) {
				nombreCentro = mentry.getValue().toString();
			} else if (mentry.getKey().equals("telefono")) {
				telefono = mentry.getValue().toString();
			} else if (mentry.getKey().equals("fax")) {
				fax = mentry.getValue().toString();
			} else if (mentry.getKey().equals("email")) {
				email = mentry.getValue().toString();
			} else if (mentry.getKey().equals("web")) {
				web = mentry.getValue().toString();
			} else if (mentry.getKey().equals("nombreComunidad")) {
				nombreComunidad = mentry.getValue().toString();
			} else if (mentry.getKey().equals("nombreProvincia")) {
				nombreProvincia = mentry.getValue().toString();
			} else if (mentry.getKey().equals("nombreMunicipio")) {
				nombreMunicipio = mentry.getValue().toString();
			} else if (mentry.getKey().equals("nombreLocalidad")) {
				nombreLocalidad = mentry.getValue().toString();
			} else if (mentry.getKey().equals("domicilio")) {
				domicilio = mentry.getValue().toString();
			} else if (mentry.getKey().equals("codigoPostal")) {
				codigoPostal = mentry.getValue().toString();
			} else if (mentry.getKey().equals("nombreNaturaleza")) {
				nombreNaturaleza = mentry.getValue().toString();
			} else if (mentry.getKey().equals("nombreTipoCentro")) {
				nombreTipoCentro = mentry.getValue().toString();
			} else if (mentry.getKey().equals("nombreDenominacion")) {
				nombreDenominacion = mentry.getValue().toString();
			} else if (mentry.getKey().equals("concertado")) {
				concertado = mentry.getValue().toString();
			}
		}
		
		if (null != concertado) {
			if ("si".equals(concertado)) {
				concert = true;
			} else if ("no".equals(concertado)) {
				concert = false;
			}
		} else {
			concert = false;
		}
		
		//Comunidad Autonoma
		ComunidadAutonoma comunidadAutonoma = new ComunidadAutonoma();
		comunidadAutonoma.setNombre(nombreComunidad);
		
		//Provincia
		Provincia provincia = new Provincia();
		provincia.setComunidadAutonoma(comunidadAutonoma);
		provincia.setNombre(nombreProvincia);
		
		//Naturaleza Centro
		NaturalezaCentro naturalezaCentro = new NaturalezaCentro();
		naturalezaCentro.setNombre(nombreNaturaleza);
		
		//TipoCentro
		TipoCentro tipoCentro = new TipoCentro();
		tipoCentro.setNombre(nombreTipoCentro);
		
		//Denominacion
		DenominacionCentro denominacionCentro = new DenominacionCentro();
		denominacionCentro.setNombre(nombreDenominacion);
		
		//Centro
		centro = new Centro();
		centro.setCodigo(codigoCentro);
		centro.setCodigoPostal(codigoPostal);
		centro.setConcertado(concert);
		centro.setDenominacionCentro(denominacionCentro);
		centro.setDireccion(domicilio);
		centro.setEmail(email);
		centro.setFax(fax);
		centro.setLocalidad(nombreLocalidad);
		centro.setMunicipio(nombreMunicipio);
		centro.setNaturalezaCentro(naturalezaCentro);
		centro.setNombre(nombreCentro);
		centro.setProvincia(provincia);
		centro.setTelefono(telefono);
		centro.setTipoCentro(tipoCentro);
		centro.setWeb(web);
		
		return centro;
	}
}
