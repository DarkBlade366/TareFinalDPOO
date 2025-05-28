package clases;

import java.util.ArrayList;

public class Veterinario extends Trabajador {
	private String especialidad;
	private ArrayList <Celda> celdaAtencion;
	private double salario;
	
	public Veterinario(String nombre, String canetId, double salarioBase,
			String especialidad, double salario) {
		super(nombre, canetId, salarioBase);
		setEspecialidad(especialidad);
		celdaAtencion = new ArrayList <Celda> ();
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
