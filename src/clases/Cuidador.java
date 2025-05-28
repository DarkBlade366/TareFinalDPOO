package clases;

public class Cuidador extends Trabajador {
	private double horario;
	private double salario;
	
	public Cuidador(String nombre, String canetId, double salarioBase,
			double horario, double salario) {
		super(nombre, canetId, salarioBase);
		setHorario(horario);
		setSalario(salario);
	}

	public double getHorario() {
		return horario;
	}

	public void setHorario(double horario) {
		this.horario = horario;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
}
