package clases;

public class Servicio extends Trabajador {
	private String zona;

	public Servicio(String nombre, String numCarnet, String zona) {
		super(nombre, numCarnet);
		setZona(zona);
	}
	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		if (zona == null || zona.trim().isEmpty()) {
			throw new IllegalArgumentException("La zona no puede estar vacía.");
		}
		this.zona = zona;
	}

	@Override
	public double calcularSalarioTotal() {
		return SALARIO_BASICO;
	}

	@Override
	public String toString() {
		return super.toString() + String.format(", Zona: %s", zona);
	}
	
	public static Servicio convertirDesdeCustodio(Custodio custodio, String zona) {
	    if (custodio == null)
	        throw new IllegalArgumentException("El custodio no puede ser nulo.");
	    if (zona == null || zona.trim().isEmpty())
	        throw new IllegalArgumentException("La zona no puede estar vacía.");

	    return new Servicio(
	        custodio.getNombre(),
	        custodio.getNumCarnet(),
	        zona
	    );
	}
}
