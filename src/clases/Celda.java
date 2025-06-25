package clases;

import java.util.ArrayList;

import enumes.Alimentacion;
import enumes.Disponibilidad;
import enumes.TipoEntorno;

public class Celda {
	private String id;
	private ArrayList<Cuidador> cuidadores;
	private Disponibilidad disponibilidad;
	private int capacidadTotal;
	private TipoEntorno entorno;
	private ArrayList<Animal> animales;

	public Celda(String id, Disponibilidad disponibilidad, int capacidadTotal, TipoEntorno entorno) {
		setId(id);
		cuidadores = new ArrayList<Cuidador>();
		animales = new ArrayList<Animal>();
		setDisponibilidad(disponibilidad);
		setCapacidadTotal(capacidadTotal);
		setEntorno(entorno);
	}

	public Alimentacion getAlimentacion() {
		return animales.isEmpty() ? null : animales.get(0).getEspecie().getAlimentacion();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id == null || id.trim().isEmpty())
			throw new IllegalArgumentException("El ID no puede estar vacío.");
		if (!id.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
			throw new IllegalArgumentException("El ID solo puede contener letras");
		}
		this.id = id.trim();
	}

	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		if (disponibilidad == null)
			throw new IllegalArgumentException("La disponibilidad no puede ser nula.");

		if (disponibilidad == Disponibilidad.OCUPADA && tieneCapacidad()) {
			throw new IllegalArgumentException("La celda aún tiene capacidad disponible. No puede marcarse como OCUPADO.");
		}

		this.disponibilidad = disponibilidad;
	}

	public int getCapacidadTotal() {
		return capacidadTotal;
	}

	public void setCapacidadTotal(int capacidadTotal) {
		if (capacidadTotal <= 0)
			throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
		this.capacidadTotal = capacidadTotal;
	}

	public TipoEntorno getEntorno() {
		return entorno;
	}

	public void setEntorno(TipoEntorno entorno) {
		if (entorno == null)
			throw new IllegalArgumentException("El entorno no puede ser nulo.");
		this.entorno = entorno;
	}

	public void agregarCuidador(Cuidador nuevoCuidador) {
	    if (nuevoCuidador == null) 
	    	throw new IllegalArgumentException("El cuidador no puede ser nulo.");
	    if (cuidadores.contains(nuevoCuidador))
	        throw new IllegalArgumentException("El cuidador ya está asignado a esta celda.");

	    int inicioNuevo = -1;
	    int finNuevo = -1;

	    if (this.equals(nuevoCuidador.getCeldaAsignada1())) {
	        inicioNuevo = nuevoCuidador.getHoraInicio1();
	        finNuevo = nuevoCuidador.getHoraFin1();
	    } else if (this.equals(nuevoCuidador.getCeldaAsignada2())) {
	        inicioNuevo = nuevoCuidador.getHoraInicio2();
	        finNuevo = nuevoCuidador.getHoraFin2();
	    } else {
	        throw new IllegalArgumentException("El cuidador no tiene esta celda asignada.");
	    }

	    int cantidadSolapados = 0;
	    for (Cuidador c : cuidadores) {
	        int inicioExistente = 0, finExistente = 0;
	        if (this.equals(c.getCeldaAsignada1())) {
	            inicioExistente = c.getHoraInicio1();
	            finExistente = c.getHoraFin1();
	        } else if (this.equals(c.getCeldaAsignada2())) {
	            inicioExistente = c.getHoraInicio2();
	            finExistente = c.getHoraFin2();
	        } else {
	            continue;
	        }

	        if (seSuperponen(inicioExistente, finExistente, inicioNuevo, finNuevo)) {
	            cantidadSolapados++;
	            if (cantidadSolapados >= 2) {
	                throw new IllegalArgumentException("No se pueden asignar más de 2 cuidadores con horarios solapados.");
	            }
	        }
	    }

	    cuidadores.add(nuevoCuidador);
	}

	private boolean seSuperponen(int inicio1, int fin1, int inicio2, int fin2) {
	    return inicio1 < fin2 && inicio2 < fin1;
	}

	public boolean tieneCapacidad() {
		return animales.size() < capacidadTotal;
	}

	public boolean esCompatibleCon(Especie especie) {
		boolean compatible = animales.isEmpty();
		if (!compatible) {
			Especie especieEnCelda = animales.get(0).getEspecie();
			compatible = especieEnCelda.equals(especie);
		}	
		return compatible;
	}

	public void agregarAnimal(Animal animal) {
		if (disponibilidad != Disponibilidad.DISPONIBLE) {
			throw new IllegalStateException("La celda no está disponible para agregar animales.");
		}
		if (!tieneCapacidad()) {
			throw new IllegalStateException("La celda ya está llena.");
		}
		if (!esCompatibleCon(animal.getEspecie())) {
			throw new IllegalArgumentException("La especie del animal no es compatible con esta celda.");
		}
		Alimentacion tipoAlimentacion = getAlimentacion();
		if (tipoAlimentacion != null && animal.getEspecie().getAlimentacion() != tipoAlimentacion)
			throw new IllegalArgumentException("No se pueden mezclar animales con diferentes tipos de alimentación en la misma celda.");

		animales.add(animal);

		if (!tieneCapacidad()) {
			this.disponibilidad = Disponibilidad.OCUPADA;
		}
	}

	public int getCapacidadDisponible() {
		return capacidadTotal - animales.size();
	}

	public ArrayList<Cuidador> getCuidadores() {
		return cuidadores;
	}

	public boolean tieneAnimales() {
		return !animales.isEmpty();
	}

	public ArrayList<Animal> getAnimales() {
		return animales;
	}

	@Override
	public String toString() {
		return id + " (Cap disponible: " + getCapacidadDisponible() + "/" + capacidadTotal + ")";
	}

	public boolean puedeAgregarCuidador(Cuidador nuevo, int horaInicio, int horaFin) {
	    for (Cuidador existente : cuidadores) {
	        int inicioExistente, finExistente;

	        if (existente.getCeldaAsignada1() != null && existente.getCeldaAsignada1().equals(this)) {
	            inicioExistente = existente.getHoraInicio1();
	            finExistente = existente.getHoraFin1();
	        }
	        else if (existente.getCeldaAsignada2() != null && existente.getCeldaAsignada2().equals(this)) {
	            inicioExistente = existente.getHoraInicio2();
	            finExistente = existente.getHoraFin2();
	        } else {
	            continue; 
	        }

	        if (seSuperponen(horaInicio, horaFin, inicioExistente, finExistente)) {
	            return false;
	        }
	    }
	    return true;
	}

	public boolean puedeAgregarCuidadorExcluyendo(Cuidador cuidadorExcluido, int horaInicio, int horaFin) {
	    for (Cuidador c : cuidadores) {
	        if (c.equals(cuidadorExcluido)) continue;
	        int inicioExistente, finExistente;

	        if (c.getCeldaAsignada1() != null && c.getCeldaAsignada1().equals(this)) {
	            inicioExistente = c.getHoraInicio1();
	            finExistente = c.getHoraFin1();
	        } else if (c.getCeldaAsignada2() != null && c.getCeldaAsignada2().equals(this)) {
	            inicioExistente = c.getHoraInicio2();
	            finExistente = c.getHoraFin2();
	        } else {
	            continue;
	        }

	        if (seSuperponen(horaInicio, horaFin, inicioExistente, finExistente)) {
	            return false;
	        }
	    }
	    return true;
	}

	public boolean puedeAgregarHorario(int horaInicio, int horaFin) {
	    for (Cuidador c : cuidadores) {
	        int inicioExistente = 0, finExistente = 0;
	        if (c.getCeldaAsignada1() != null && c.getCeldaAsignada1().equals(this)) {
	            inicioExistente = c.getHoraInicio1();
	            finExistente = c.getHoraFin1();
	        } else if (c.getCeldaAsignada2() != null && c.getCeldaAsignada2().equals(this)) {
	            inicioExistente = c.getHoraInicio2();
	            finExistente = c.getHoraFin2();
	        } else {
	            continue;
	        }
	        if (seSuperponen(horaInicio, horaFin, inicioExistente, finExistente)) {
	            return false;
	        }
	    }
	    return true;
	}

	public boolean eliminarAnimal(Animal animal) {
		if (animal == null)
			throw new IllegalArgumentException("El animal no puede ser nulo.");
		boolean eliminado = animales.remove(animal);

		if (eliminado && disponibilidad != Disponibilidad.MANTENIMIENTO && tieneCapacidad()) {
			this.disponibilidad = Disponibilidad.DISPONIBLE;
		}
		return eliminado;
	}

	public void removerCuidador(Cuidador cuidador) {
		if (cuidador == null)
			throw new IllegalArgumentException("El cuidador no puede ser nulo.");
		cuidadores.remove(cuidador);
	}
}

