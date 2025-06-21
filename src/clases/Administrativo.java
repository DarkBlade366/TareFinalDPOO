package clases;

public class Administrativo extends Trabajador {
	private int anyosExp;
	private String puestoTrabajo;
	private String oficina;
	private static final double PORCENTAJE_POR_ANO = 0.02;
	
	public Administrativo(String nombre, String canetId, int anyosExp, String puestoTrabajo, String oficina) {
		super(nombre, canetId);
		setAnyosExp(anyosExp);
		setPuestoTrabajo(puestoTrabajo);
		setOficina(oficina);
	}
	
	public int getAnyosExp() {
		return anyosExp;
	}
	public void setAnyosExp(int anyosExp) {
		if (anyosExp < 0 || anyosExp > 60)
			throw new IllegalArgumentException("Los años de experiencia deben estar entre 0 y 60.");
		this.anyosExp = anyosExp;
	}
	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}
	public void setPuestoTrabajo(String puestoTrabajo) {
		if (puestoTrabajo == null || puestoTrabajo.trim().isEmpty())
			throw new IllegalArgumentException("El puesto de trabajo no puede estar vacío.");
		this.puestoTrabajo = puestoTrabajo;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		if (oficina == null || oficina.trim().isEmpty())
			throw new IllegalArgumentException("La oficina no puede estar vacía.");
		this.oficina = oficina;
	}
	@Override
	public double calcularSalarioTotal() {
		double aumento = SALARIO_BASICO * PORCENTAJE_POR_ANO * anyosExp;
		return SALARIO_BASICO + aumento;
	}
	@Override
	public String toString() {
		return super.toString() +String.format(", Puesto: %s, Oficina: %s, Años experiencia: %d", puestoTrabajo, oficina, anyosExp);
	}
}
