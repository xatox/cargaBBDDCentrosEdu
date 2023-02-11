package util;
import java.util.ArrayList;
/**
 * Clase dedicada a medir los tiempos de ejecucion de un bucle muy largo.
 * El funcionamiento sera el siguiente;
 * 1.- Se iniciara el cronometro (start)
 * 2.- Desde este momento se podran pedir los intervalos parciales con el metodo getIntervalo()
 */
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Cronometro {

	private Date fechaInicio;
	private List<Date> listaParciales;
	
	public Cronometro() {
		start();
		this.listaParciales = new ArrayList<Date>();
	}
	
	public void start() {
		this.fechaInicio = new Date();
	}
	
	public Date finish() {
		
		Date fechaFin = new Date();
		
		int segundosDif = (int) ((fechaFin.getTime()-fechaInicio.getTime()));
		
		Date fechaDif = new Date(segundosDif);
		
		return fechaDif;
	}
	
	public Date getTiempoParcial(int elementosHechos, int elementosTotales) {
		
		Date fechaParcial = new Date();
		int segundosDif = (int) ((fechaParcial.getTime()-fechaInicio.getTime()));
		
		Long reglaDeTres = (long) ((elementosTotales * segundosDif) / elementosHechos);
		
		Date parcial = new Date(reglaDeTres);
		return parcial;
	}
	
	public Date getTiempoParcialPonderado(int elementosHechos, int elementosTotales) {
		
		listaParciales.add(getTiempoParcial(elementosHechos, elementosTotales));
		
		Long segundosTotales = (long) 0;
		for (Date parcial : listaParciales) {
			segundosTotales += parcial.getTime();
		}
		
		Long segundosDif = segundosTotales / listaParciales.size();
		
		Date ponderada = new Date(segundosDif);
		
		return ponderada;
	}
	
	public String getTiempoRestante (Date parcial) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(parcial);
		return cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
	}
	
}
