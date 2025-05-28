package clases;

import java.util.ArrayList;

public class Zoologico {
	private String nombre;
	private ArrayList<Celda> celdas;
	private ArrayList<Trabajador> trabajadores;
	private ArrayList<Animal> animales;
	private ArrayList<Especie> especies;
	
	public Zoologico(String nombre) {
		setNombre(nombre);
		celdas = new ArrayList<Celda> ();
		trabajadores = new ArrayList<Trabajador> ();
		animales = new ArrayList<Animal> ();
		especies = new ArrayList<Especie> ();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("");
		this.nombre = nombre;
	}
	
	

}
