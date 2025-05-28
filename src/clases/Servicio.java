package clases;

public class Servicio extends Trabajador {
	private String zona;

	public Servicio(String nombre, String canetId, double salarioBase,
			String zona) {
		super(nombre, canetId, salarioBase);
		setZona(zona);
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
}
