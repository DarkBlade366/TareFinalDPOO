package clases;

import java.util.ArrayList;
import java.util.Objects;

import enumes.Alimentacion;

public class Veterinario extends Trabajador {
	private Alimentacion especialidad;
	private ArrayList <Celda> celdaAtencion;
	
	public Veterinario(String nombre, String canetId, Alimentacion especialidad) {
		super(nombre, canetId);
		setEspecialidad(especialidad);
		celdaAtencion = new ArrayList <Celda> ();
	}
	
	public Alimentacion getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Alimentacion especialidad) {
		if (especialidad == null)
            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
		this.especialidad = especialidad;
	}
	
	public void agregarCeldaAtencion(Celda celda) {
	    if (celda == null)
	        throw new IllegalArgumentException("La celda no puede ser nula.");
	    if (celdaAtencion.size() >= 5)
	        throw new IllegalArgumentException("No se pueden atender más de 5 celdas.");
	    if (celdaAtencion.contains(celda))
	        throw new IllegalArgumentException("Esta celda ya está asignada a este veterinario.");
	    if (!celdaAtencion.isEmpty()) {
	        if (celdaAtencion.get(0).getAlimentacion() != null && celda.getAlimentacion() != celdaAtencion.get(0).getAlimentacion())
	            throw new IllegalArgumentException("Un veterinario solo puede atender celdas con el mismo tipo de alimentación.");
	    }
	    celdaAtencion.add(celda);
	}

	  @Override
	    public double calcularSalarioTotal() {
	        double bono = SALARIO_BASICO * 0.05 * celdaAtencion.size();
	        return SALARIO_BASICO + bono;
	    }

	    @Override
	    public String toString() {
	        return super.toString() + String.format(", Especialidad: %s, Celdas atendidas: %d",
	                especialidad, celdaAtencion.size());
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (!(obj instanceof Veterinario)) return false;
	        Veterinario otro = (Veterinario) obj;
	        return super.equals(otro) && Objects.equals(this.especialidad, otro.especialidad);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(super.hashCode(), especialidad);
	    }
	    
	    public ArrayList<Celda> getCeldasAtendidas() {
	        return new ArrayList<Celda>(celdaAtencion);
	    }
	    public void removerCeldaAtencion(Celda celda) {
	        if (celda == null) throw new IllegalArgumentException("La celda no puede ser nula.");
	        celdaAtencion.remove(celda);
	    }
	    public void clearCeldasAtendidas() {
	        celdaAtencion.clear();
	    }
}
