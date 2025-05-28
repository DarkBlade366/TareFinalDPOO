package clases;

public class Trabajador {
	protected String nombre;
	protected String numCarnet;
	protected double salarioBasico;
	
	public Trabajador(String nombre, String canetId, double salarioBase) {
		setNombre(nombre);
		setNumCarnet(numCarnet);
		setSalarioBase(salarioBase);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumCarnet() {
		return numCarnet;
	}
	public void setNumCarnet(String numCarnet) {
		this.numCarnet = numCarnet;
	}
	public double getSalarioBasico() {
		return salarioBasico;
	}
	public void setSalarioBase(double salarioBasico) {
		this.salarioBasico = salarioBasico;
	}
	
	
}
