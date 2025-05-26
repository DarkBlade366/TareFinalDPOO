package clases;

public class Custodio extends Trabajador {
	private int diasGuardia;
	private int edad;
	private double salario;
	
	public Custodio(String nombre, String canetId, double salarioBase,
			int diasGuardia, int edad, double salario) {
		super(nombre, canetId, salarioBase);
		setDiasGuardia(diasGuardia);
		setEdad(edad);
		setSalario(salario);
	}
	
	public int getDiasGuardia() {
		return diasGuardia;
	}
	public void setDiasGuardia(int diasGuardia) {
		this.diasGuardia = diasGuardia;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
}
