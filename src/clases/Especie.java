package clases;

import java.util.ArrayList;

public class Especie{
	private int esperanza;
	private String nombreComun;
	private String nombreCientifico;
	private String alimnentacion;
	private String especificaciones;
	private double tamaño;
	private double pesoProm;
	private double cantComida;
	private ArrayList<Animal> animales;


	public Especie(int esperanza,String nombreComun, String nombreCientifico, String alimnentacion,
			String especificaciones, double tamaño, double pesoProm,double cantComida) 
	{
		setEsperanza(esperanza);
		setNombreComun(nombreComun);
		setNombreCientifico(nombreCientifico);
		setAlimnentacion(alimnentacion);
		setEspecificaciones(especificaciones);
		setTamaño(tamaño);
		setPesoProm(pesoProm);
		setCantComida(cantComida);
		animales = new ArrayList<Animal> ();
	}

	public int getEsperanza() {
		return esperanza;
	}

	public void setEsperanza(int esperanza) {
		this.esperanza = esperanza;
	}

	public String getNombreComun() {
		return nombreComun;
	}

	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}

	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	public String getAlimnentacion() {
		return alimnentacion;
	}

	public void setAlimnentacion(String alimnentacion) {
		this.alimnentacion = alimnentacion;
	}

	public String getEspecificaciones() {
		return especificaciones;
	}

	public void setEspecificaciones(String especificaciones) {
		this.especificaciones = especificaciones;
	}

	public double getTamaño() {
		return tamaño;
	}

	public void setTamaño(double tamaño) {
		this.tamaño = tamaño;
	}

	public double getPesoProm() {
		return pesoProm;
	}

	public void setPesoProm(double pesoProm) {
		this.pesoProm = pesoProm;
	}

	public double getCantComida() {
		return cantComida;
	}

	public void setCantComida(double cantComida) {
		this.cantComida = cantComida;
	}
}
