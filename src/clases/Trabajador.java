package clases;

import java.util.Objects;

public abstract class Trabajador {
	protected String nombre;
	protected String numCarnet;
    protected static final double SALARIO_BASICO = 1500.00;
	

	
	public Trabajador(String nombre, String numCarnet) {
		setNombre(nombre);
		setNumCarnet(numCarnet);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		 if (nombre == null || nombre.trim().isEmpty())
	            throw new IllegalArgumentException("El nombre no puede estar vacío.");
	        nombre = nombre.trim();
	        if (nombre.length() < 3 || nombre.length() > 30)
	            throw new IllegalArgumentException("El nombre debe tener entre 3 y 30 caracteres.");
	        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))
	            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
		this.nombre = nombre;
	}
	public String getNumCarnet() {
		return numCarnet;
	}
	public void setNumCarnet(String numCarnet) {
		if (numCarnet == null || numCarnet.trim().isEmpty())
	        throw new IllegalArgumentException("El número de carnet no puede estar vacío.");
	    numCarnet = numCarnet.trim();
	    if (!numCarnet.matches("\\d{11}"))
	        throw new IllegalArgumentException("El número de carnet debe contener exactamente 11 dígitos numéricos.");
		this.numCarnet = numCarnet;
	}
	
	public abstract double calcularSalarioTotal();

    @Override
    public String toString() {
        return String.format("Nombre: %s, Carnet: %s, Salario: %.2f", nombre, numCarnet);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Trabajador))
            return false;
        Trabajador otro = (Trabajador) obj;
        return Objects.equals(numCarnet, otro.numCarnet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCarnet);
    }
}
