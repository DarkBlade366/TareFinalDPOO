package clases;

public class Administrativo extends Trabajador {
	private int anyosExp;
	private String puestoTrabajo;
	private String oficina;
	private double salario;
	
	public Administrativo(String nombre, String canetId, double salarioBase,
			int anyosExp, String puestoTrabajo, String oficina, double salario) {
		super(nombre, canetId, salarioBase);
		setAnyosExp(anyosExp);
		setPuestoTrabajo(puestoTrabajo);
		setOficina(oficina);
		setSalario(salario);
	}
	
	public int getAnyosExp() {
		return anyosExp;
	}
	public void setAnyosExp(int anyosExp) {
		this.anyosExp = anyosExp;
	}
	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}
	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
}
