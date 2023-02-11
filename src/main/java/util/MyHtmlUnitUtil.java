package util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import model.Ensenanza;
import model.Familia;
import model.Grado;
import model.Modalidad;

public class MyHtmlUnitUtil {

	//private String urlRegistroEstatalCentrosDocentes = "https://www.educacion.gob.es/centros/home.do";
	
	private WebClient webClient;
	private HtmlPage htmlPage;
	
	public MyHtmlUnitUtil (String rutaFormulario) {
		initWebClient();
		cargaFormulario(rutaFormulario);
	}
	
	private void initWebClient () {
		webClient = new WebClient();
		webClient.getOptions().setUseInsecureSSL(true);
	}
	
	private void cargaFormulario (String rutaFormulario) {
		try {
			htmlPage  = webClient.getPage(rutaFormulario);
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			htmlPage = null;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			htmlPage = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			htmlPage = null;
		}
	}
	
	public HtmlPage obtenerPaginaCentro (String codigoCentro) throws IOException {
		
		HtmlForm form = htmlPage.getFormByName("Saccen");
		
	    final HtmlTextInput textField = form.getInputByName("codcen");
	    final HtmlTextInput textField2 = form.getInputByName("codcen2");
	    
	    final HtmlSubmitInput button = form.getInputByName("enviar");
	    
	    textField.setValueAttribute(codigoCentro);
	    textField2.setValueAttribute(codigoCentro);
	    
	    // Now submit the form by clicking the button and get back the second page.
	    HtmlPage paginaCentro = button.click();

	    return paginaCentro;
	}
	
	/*public HtmlPage obtenerBuscadorCentros() throws IOException {
		
		HtmlForm form = htmlPage.getFormByName("Entrada");
	    
	    final HtmlSubmitInput button = form.getInputByName("enviar");
	    
	    // Now submit the form by clicking the button and get back the second page.
	    HtmlPage paginaBuscador = button.click();

	    return paginaBuscador;
	}*/
	
	public HtmlPage obtenerListadoCentros() throws IOException {
		
		HtmlForm form = htmlPage.getFormByName("Buscar");
	    
		final HtmlSubmitInput button = form.getInputByName("enviar");
	    
	    // Now submit the form by clicking the button and get back the second page.
	    HtmlPage paginaListado = button.click();

	    return paginaListado;
	}
	
	public HtmlPage aceptarRestriccion(HtmlPage listadoPage) throws IOException {
		
		HtmlForm form = listadoPage.getFormByName("Saccen");
	    
	    //final HtmlSubmitInput button = form.getInputByName("enviar");
	    final HtmlInput button = form.getInputByValue("Mostrar los centros recuperados");
	    
	    // Now submit the form by clicking the button and get back the second page.
	    HtmlPage paginaBuscador = button.click();

	    return paginaBuscador;
	}
	
	public HashMap<String, String> obtenerCamposDesdeTexto (HtmlPage paginaCentro) {
		
		HashMap<String, String> mapaCampos = new HashMap<String, String>();
		
		HtmlForm form = paginaCentro.getFormByName("Saccen");
		//System.out.println(form.asText().toString());
		Scanner scanner = new Scanner(form.asText().toString());
		while (scanner.hasNextLine()) {
		  String line = scanner.nextLine();
		  
		  if (line.contains("Código de centro")) {
			  mapaCampos.put("codigoCentro", obtenerDato(line));
			  //codigoCentro = obtenerDato(line);
		  } else if (line.contains("Denominación específica")) {
			  mapaCampos.put("nombreCentro", obtenerDato(line));
			  //nombreCentro = obtenerDato(line);
		  } else if (line.contains("Teléfono")) {
			  mapaCampos.put("telefono", obtenerDato(line));
			  //telefono = obtenerDato(line);
		  } else if (line.contains("Fax")) {
			  mapaCampos.put("fax", obtenerDato(line));
			  //fax = obtenerDato(line);
		  } else if (line.contains("Dirección de correo electrónico")) {
			  mapaCampos.put("email", obtenerDato(line));
			  //email = obtenerDato(line);
		  } else if (line.contains("Página Web del centro")) {
			  mapaCampos.put("web", obtenerDato(line));
			  //web = obtenerDato(line);
		  } else if (line.contains("Autonomía")) {
			  mapaCampos.put("nombreComunidad", obtenerDato(line));
			  //nombreComunidad = obtenerDato(line);
		  } else if (line.contains("Provincia:")) {
			  mapaCampos.put("nombreProvincia", obtenerDato(line));
			  //nombreProvincia = obtenerDato(line);
		  } else if (line.contains("Municipio")) {
			  mapaCampos.put("nombreMunicipio", obtenerDato(line));
			  //nombreMunicipio = obtenerDato(line);
		  } else if (line.contains("Localidad")) {
			  mapaCampos.put("nombreLocalidad", obtenerDato(line));
			  //nombreLocalidad = obtenerDato(line);
		  } else if (line.contains("Domicilio")) {
			  mapaCampos.put("domicilio", obtenerDato(line));
			  //direccion = obtenerDato(line);
		  }else if (line.contains("Código postal")) {
			  mapaCampos.put("codigoPostal", obtenerDato(line));
			  //codigoPostal = obtenerDato(line);
		  } else if (line.contains("Naturaleza")) {
			  mapaCampos.put("nombreNaturaleza", obtenerDato(line));
			  //nombreNaturaleza = obtenerDato(line);
		  } else if (line.contains("Tipo de Centro")) {
			  mapaCampos.put("nombreTipoCentro", obtenerDato(line));
			  //nombreTipoCentro = obtenerDato(line);
		  } else if (line.contains("Denominación Genérica")) {
			  mapaCampos.put("nombreDenominacion", obtenerDato(line));
			  //nombreDenominacion = obtenerDato(line);
		  } else if (line.contains("Concertado")) {
			  String concer = obtenerDato(line);
			  if (concer.equals("Sí") || concer.equals("Si") ||concer.equals("SI")||concer.equals("si")) {
				  mapaCampos.put("concertado", "si");
				  //concertado = true;
			  } else {
				  mapaCampos.put("concertado", "no");
			  }
		  }
		}
		scanner.close();
		
		return mapaCampos;
	}

	private String obtenerDato (String linea) {
		String dato = "";
		if (linea.contains(":")) {
			dato = linea.substring(linea.indexOf(":") + 1);
		}
		return dato.trim();
	}
	
	public Set<Ensenanza> obtenerCamposEnsenanzas (HtmlPage paginaCentro) {
		
		Set<Ensenanza> listaEnsenanzas = new HashSet();
		
		if (paginaCentro.asXml().contains("maten")) {
			final HtmlTable table = paginaCentro.getHtmlElementById("maten");
			for (int x = 0; x < table.getRows().size(); x++) {
				if (x != 0) {
					
					String nombreGrado = null, nombreFamilia = null, nombreEnsenanza = null, nombreModalidad = null;
					
					HtmlTableRow row = table.getRows().get(x);
				    for (int y = 0; y < row.getCells().size(); y++) {
				    	
				    	HtmlTableCell cell = row.getCells().get(y);
				    	
				    	switch (y) {
						case 0:
							//Grado
							nombreGrado = cell.asText();
							break;
						case 1:
							//Familia
							nombreFamilia = cell.asText();
							break;
						case 2:
							//Ensenanza
							nombreEnsenanza = cell.asText();
							break;
						case 3:
							//Modalidad
							nombreModalidad = cell.asText();
							break;
						default:
							break;
						}
				    }
				    
				    Ensenanza ensenanza = makeEnsenanza(nombreGrado, nombreFamilia, nombreModalidad, nombreEnsenanza);
				    if (null != ensenanza) {
				    	listaEnsenanzas.add(ensenanza);
				    }
				    
				}
			}
		}
		
		return listaEnsenanzas;
	}
	
	private Ensenanza makeEnsenanza(String nombreGrado, String nombreFamilia, String nombreModalidad, String nombreEnsenanza) {
		
		Ensenanza ensenanza = null;
		
		Grado grado = null;
	    if (!"".equals(nombreGrado)) {
	    	grado = new Grado();
	    	grado.setNombre(nombreGrado);
	    }
	    
	    Familia familia = null;
	    if (!"".equals(nombreFamilia)) {
	    	familia = new Familia();
	    	familia.setNombre(nombreFamilia);
	    }
	    
	    Modalidad modalidad = null;
	    if (!"".equals(nombreModalidad)) {
	    	modalidad = new Modalidad();
	    	modalidad.setNombre(nombreModalidad);
	    }
	    
	    if (!"".equals(nombreEnsenanza)) {
	    	ensenanza = new Ensenanza();
	    	ensenanza.setNombre(nombreEnsenanza);
	    	ensenanza.setFamilia(familia);
	    	ensenanza.setModalidad(modalidad);
	    	ensenanza.setGrado(grado);
	    }
	    
	    return ensenanza;
	}
	
	/*private void obtenerPaginaCentros() {
		
		try {
			HtmlPage pagHome = webClient.getPage(urlRegistroEstatalCentrosDocentes);
			ScriptResult scriptResult = pagHome.executeJavaScript("javascript:selprov('00','00');");
	        Object jsResult = scriptResult.getJavaScriptResult();
	        //HtmlPage afterExecution = (HtmlPage) ;

	        System.out.println(afterExecution.asXml());
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}*/
}
