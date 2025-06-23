package clases;

import java.util.ArrayList;

import enumes.Alimentacion;
import enumes.TipoEntorno;

public class Especie{
	private int esperanza;
	private String nombreComun;
	private String nombreCientifico;
	private Alimentacion alimentacion;
	private TipoEntorno entorno;
	private double tamaño;
	private double pesoProm;
	private double cantComida;
	private ArrayList<Animal> animales;


	public Especie(int esperanza,String nombreComun, String nombreCientifico, Alimentacion alimentacion,
			TipoEntorno entorno, double tamaño, double pesoProm,double cantComida) 
	{
		setEsperanza(esperanza);
		setNombreComun(nombreComun);
		setNombreCientifico(nombreCientifico);
		setAlimentacion(alimentacion);
		setEntorno(entorno);
		setTamaño(tamaño);
		setPesoProm(pesoProm);
		setCantComida(cantComida);
		animales = new ArrayList<Animal> ();
	}

	public int getEsperanza() {
		return esperanza;
	}

	public void setEsperanza(int esperanza) {
		if (esperanza <= 0)
		    throw new IllegalArgumentException("La esperanza de vida debe ser mayor a 0.");
		this.esperanza = esperanza;
	}

	public String getNombreComun() {
		return nombreComun;
	}

	public void setNombreComun(String nombreComun) {
		if (nombreComun == null || nombreComun.trim().isEmpty())
		    throw new IllegalArgumentException("El nombre común no puede estar vacío.");
		if (!nombreComun.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))
		    throw new IllegalArgumentException("El nombre común solo puede contener letras y espacios.");
		this.nombreComun = nombreComun.trim();
	}

	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		if (nombreCientifico == null || nombreCientifico.trim().isEmpty())
		    throw new IllegalArgumentException("El nombre cientifico no puede estar vacío.");
		if (!nombreCientifico.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))
		    throw new IllegalArgumentException("El nombre cientifico solo puede contener letras y espacios.");
		this.nombreCientifico = nombreCientifico.trim();
	}

	public Alimentacion getAlimentacion() {
		return alimentacion;
	}

	public void setAlimentacion(Alimentacion alimentacion) {
		if (alimentacion == null) {
	        throw new IllegalArgumentException("La alimentación no puede ser nula.");
	    }
		this.alimentacion = alimentacion;
	}

	public TipoEntorno getEntorno() {
		return entorno;
	}

	public void setEntorno(TipoEntorno entorno) {
		if (entorno == null) {
	        throw new IllegalArgumentException("El entorno no puede ser nulo.");
	    }
		this.entorno = entorno;
	}

	public double getTamaño() {
		return tamaño;
	}

	public void setTamaño(double tamaño) {
		if (tamaño <= 0) {
            throw new IllegalArgumentException("El tamaño debe ser mayor que cero.");
        }
		this.tamaño = tamaño;
	}

	public double getPesoProm() {
		return pesoProm;
	}

	public void setPesoProm(double pesoProm) {
		if (pesoProm <= 0) {
            throw new IllegalArgumentException("El peso promedio debe ser mayor que cero.");
        }
		this.pesoProm = pesoProm;
	}

	public double getCantComida() {
		return cantComida;
	}

	public void setCantComida(double cantComida) {
		if (cantComida <= 0) {
            throw new IllegalArgumentException("La cantidad de comida debe ser mayor que cero.");
        }
		this.cantComida = cantComida;
	}
	
    public void agregarAnimal(Animal animal) {
        if (animal == null)
            throw new IllegalArgumentException("El animal no puede ser nulo.");
        if (!animal.getEspecie().equals(this))
            throw new IllegalArgumentException("El animal no pertenece a esta especie.");
        if (animales.contains(animal))
            throw new IllegalArgumentException("El animal ya está registrado en esta especie.");
        animales.add(animal);
    }
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	    	return true;
	    if (obj == null || getClass() != obj.getClass()) 
	    	return false;
	    Especie especie = (Especie) obj;
	    return nombreCientifico.equalsIgnoreCase(especie.nombreCientifico);
	}

	@Override
	public int hashCode() {
	    return nombreCientifico.toLowerCase().hashCode();
	}
	
	@Override
	public String toString() {
	    return nombreComun + " (" + nombreCientifico + ")";
	}
	
	public ArrayList<Animal> getAnimales() {
		
	    return new ArrayList<Animal>(animales); 
	} 
	public void eliminarAnimal(Animal a) {
	    animales.remove(a);
	}
}

