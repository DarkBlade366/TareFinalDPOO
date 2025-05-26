package clases;

public class Celda {
	private String id;
	private Arraylist <Cuidador> cuidadores;
	private String disponibilidad;
	private String capacidadTotal;
	private String especificacionesEntorno; //HAY QUE VER COMO HACERLO
	private int animales;
	
	public Celda(String id, String disponibilidad,
			String capacidadTotal, String especificacionesEntorno, int animales) {
		setId(id);
		cuidadores = new Arraylist <Cuidador> ();
		setDisponibilidad(disponibilidad);
		setCapacidadTotal(capacidadTotal);
		setEspecificacionesEntorno(especificacionesEntorno);
		setAnimales(animales);
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
	public int getAnimales() {
		return animales;
	}
	public void setAnimales(int animales) {
		this.animales = animales;
	}
}
	
	