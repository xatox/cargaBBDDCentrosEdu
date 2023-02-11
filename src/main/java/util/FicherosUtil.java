package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FicherosUtil {

	/*private static void makeFileCodigosKO () {
		
		initFileWriter();
		
		for (String codigo : listaCentrosKO) {
			printWriter.println(codigo);
		}
		
		closeFileWriter();
		
	}
	
	private static void initFileWriter() {
		try {
			fileWriter = new FileWriter(ficheroCodigosKO,true);
			printWriter = new PrintWriter(fileWriter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void closeFileWriter() {
		try {
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public static List<String> obtenerCodigosCentrosDesdeFichero (String rutaArchivo) {
		
		List<String> listaCodigos = new ArrayList<String>();	
		File archivoRegistroCentros = new File (rutaArchivo);
		
		try {
			
			FileReader fr = new FileReader (archivoRegistroCentros);
			BufferedReader br = new BufferedReader(fr);
			
			// Lectura del fichero
			String linea, codigo, ultimoCodigoEscrito = "";
			while((linea=br.readLine())!=null) {
				//System.out.println(linea);
				
				if (linea.contains("javascript:pas")) {
					codigo = linea.substring(linea.indexOf(";") + 1, linea.lastIndexOf("&"));
					
					if (!codigo.equals(ultimoCodigoEscrito)) {
						listaCodigos.add(codigo);
						ultimoCodigoEscrito = codigo;
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("*** ERROR AL OBTENER CODIGOS DE CENTROS DESDE EL ARCHIVO - FileNotFoundException");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("*** ERROR AL OBTENER CODIGOS DE CENTROS DESDE EL ARCHIVO - IOException");
		} finally {
			/*try {
				System.out.println("- NUEVO FICHERO DE CODIGOS DE CENTROS CREADO");
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		
		return listaCodigos;
	}
	
	public static List<String> obtenerCodigosCentrosDesdeHtml (String html) {
		List<String> listaCodigos = new ArrayList<String>();
		
		Scanner scanner = new Scanner(html);
		String codigo, ultimoCodigoEscrito = "";
		while (scanner.hasNextLine()) {
		  String linea = scanner.nextLine();
		  
		  if (linea.contains("javascript:pas")) {
			  //System.out.println(linea);
			  codigo = linea.substring(linea.indexOf("'") + 1, linea.lastIndexOf("'"));
			  //System.out.println(codigo);
			  if (!codigo.equals(ultimoCodigoEscrito)) {
					listaCodigos.add(codigo);
					ultimoCodigoEscrito = codigo;
			  }
		  }
		}
		scanner.close();
		
		return listaCodigos;
	}
}
