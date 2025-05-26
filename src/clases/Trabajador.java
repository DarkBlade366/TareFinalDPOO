package clases;

public class Trabajador {
	protected String nombre;
	protected String canetId;
	protected double salarioBase;+
	
	public Trabajador(String nombre, String canetId, double salarioBase) {
		setNombre(nombre);
		setCanetId(canetId);
		setSalarioBase(salarioBase);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCanetId() {
		return canetId;
	}
	public void setCanetId(String canetId) {
		this.canetId = canetId;
	}
	public double getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}
	
	
}
