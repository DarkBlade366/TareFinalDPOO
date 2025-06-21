package clases;

import java.time.LocalDate;
import java.time.Period;

import enumes.Sexo;

public class Animal {
	private int id;
    private LocalDate nacimiento;
    private Sexo sexo;
    private Especie especie;
	
	public Animal(int id, LocalDate nacimiento, Sexo sexo, Especie especie) {
		setId(id);
		setNacimiento(nacimiento);
		setSexo(sexo);
		setEspecie(especie);
		especie.agregarAnimal(this);
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		 if (especie == null)
	            throw new IllegalArgumentException("La especie no puede ser nula.");
		this.especie = especie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {	
		if (id <= 0)
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        this.id = id;
	}

	public LocalDate getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(LocalDate nacimiento) {
		if (nacimiento == null)
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
        if (nacimiento.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de nacimiento no puede estar en el futuro.");
        this.nacimiento = nacimiento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		 if (sexo == null)
	            throw new IllegalArgumentException("El sexo no puede ser nulo.");
		this.sexo = sexo;
	}
	
	 @Override
	    public String toString() {
	        return "Animal [id=" + id + ", nacimiento=" + nacimiento + ", sexo=" + sexo + ", especie=" + especie.getNombreComun() + " (" + especie.getNombreCientifico() + ")]";
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (!(obj instanceof Animal))
	            return false;
	        Animal otro = (Animal) obj;
	        return this.id == otro.id;
	    }

	    @Override
	    public int hashCode() {
	        return Integer.hashCode(id);
	    }
	    
	    public int getEdad() {
		    return Period.between(nacimiento, LocalDate.now()).getYears();
		}
}
