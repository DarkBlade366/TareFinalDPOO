package clases;

import java.util.ArrayList;

public class Celda {
	private String id;
	private ArrayList<Cuidador> cuidadores;
	private String disponibilidad;
	private String capacidadTotal;
	private String especificacionesEntorno; //HAY QUE VER COMO HACERLO
	private int animalesAsignados;
	
	public Celda(String id, String disponibilidad,
			String capacidadTotal, String especificacionesEntorno, int animales) {
		setId(id);
		cuidadores = new ArrayList <Cuidador> ();
		setDisponibilidad(disponibilidad);
		setCapacidadTotal(capacidadTotal);
		setEspecificacionesEntorno(especificacionesEntorno);
		setAnimalesAsignados(animalesAsignados);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public String getCapacidadTotal() {
		return capacidadTotal;
	}
	public void setCapacidadTotal(String capacidadTotal) {
		this.capacidadTotal = capacidadTotal;
	}
	public String getEspecificacionesEntorno() {
		return especificacionesEntorno;
	}
	public void setEspecificacionesEntorno(String especificacionesEntorno) {
		this.especificacionesEntorno = especificacionesEntorno;
	}
	public int getAnimalesAsignados() {
		return animalesAsignados;
	}
	public void setAnimalesAsignados(int animalesAsignados) {
		this.animalesAsignados = animalesAsignados;
	}
}
	
	