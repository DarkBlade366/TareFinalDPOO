package clases;

import java.util.ArrayList;

public class Custodio extends Trabajador {
	private ArrayList<Integer> diasGuardia;;
	private int edad;
	public static double pagoPorDiaGuardia = 200.0;



	public Custodio(String nombre, String numCarnet, int edad) {
		super(nombre, numCarnet);
		setEdad(edad);
		diasGuardia = new ArrayList<Integer>();
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
	    if (edad <= 0 || edad > 100)
	        throw new IllegalArgumentException("Edad inválida.");
	     this.edad = edad;
	}

		public void setDiasGuardia(ArrayList<Integer> diasGuardia) {
		    if (diasGuardia == null)
		        throw new IllegalArgumentException("La lista de días no puede ser nula.");
		    if (diasGuardia.size() > 3)
		        throw new IllegalArgumentException("No se pueden asignar más de 3 días de guardia.");
	
		    boolean[] dias = new boolean[7];
	
		    for (int dia : diasGuardia) {
		        if (dia < 0 || dia > 6)
		            throw new IllegalArgumentException("Los días deben estar entre 0 (lunes) y 6 (domingo).");
		        if (dias[dia])
		            throw new IllegalArgumentException("No se pueden repetir días.");
		        dias[dia] = true;
		    }
		    
		    for (int i = 0; i < 6; i++) {
		        if (dias[i] && dias[i + 1])
		            throw new IllegalArgumentException("Los días de guardia deben estar intercalados con al menos un día de por medio.");
		    }
		    this.diasGuardia = new ArrayList<Integer>(diasGuardia);
		}

	@Override
	public double calcularSalarioTotal() {
	    return SALARIO_BASICO + (diasGuardia.size() * pagoPorDiaGuardia);
	}

	@Override
	public String toString() {
		return super.toString() + String.format(", Edad: %d, Días Guardia: %s", edad, diasGuardia.toString());
	}
		
	public ArrayList<Integer> getDiasGuardia() {
	    return new ArrayList<Integer>(diasGuardia);
	}
}
