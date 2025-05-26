package clases;

public class Veterinario extends Trabajador {
	private String especialidad;
	private Arraylist <Celda> celdaAtencion;//Creo que las celdaas serian un arraylist q va guardadno las celdas que atiende
	private double salario;
	
	public Veterinario(String nombre, String canetId, double salarioBase,
			String especialidad, double salario) {
		super(nombre, canetId, salarioBase);
		setEspecialidad(especialidad);
		celdaAtencion = new Arraylist <Celda> ();
		setSalario(salario);
	}
	
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
}
