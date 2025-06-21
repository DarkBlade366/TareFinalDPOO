package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import enumes.Alimentacion;
import enumes.Disponibilidad;
import enumes.Sexo;
import enumes.TipoEntorno;

public class Zoologico {
	private String nombre;
	private ArrayList<Celda> celdas;
	private ArrayList<Trabajador> trabajadores;
	private ArrayList<Animal> animales;
	private ArrayList<Especie> especies;
	private static Zoologico zoo;
	
	private Zoologico(String nombre) {
		setNombre(nombre);
		celdas = new ArrayList<Celda> ();
		trabajadores = new ArrayList<Trabajador> ();
		animales = new ArrayList<Animal> ();
		especies = new ArrayList<Especie> ();
	}
	public static Zoologico getZoo(){
		if(zoo==null){
			zoo = new Zoologico("Zoologico Nacional");
		}
		return zoo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty())
		    throw new IllegalArgumentException("El nombre del zoológico no puede ser vacío o nulo.");
		nombre = nombre.trim();
		if (nombre.length() < 3 || nombre.length() > 30) {
		    throw new IllegalArgumentException("El nombre debe tener entre 3 y 30 caracteres");		    
		}
		if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
		    throw new IllegalArgumentException("El nombre solo puede contener letras y espacios");
		}
		this.nombre = nombre;
	}
	
	public void agregarCelda(String id, Disponibilidad disponibilidad, int capTotal,TipoEntorno tipoEntorno) {
	    for (Celda existente : celdas) {
	        if (existente.getId().equals(id)) {
	            throw new IllegalArgumentException("Ya existe una celda con el mismo ID.");
	        }
	    }
	    
	    Celda nuevaCelda = new Celda(id, disponibilidad, capTotal, tipoEntorno);
	    celdas.add(nuevaCelda);
	}
	public void agregarTrabajador(Trabajador trabajador) {
	    if (trabajador == null)
	    	throw new IllegalArgumentException("El trabajador no puede ser nulo.");
	    if (trabajadores.contains(trabajador))
	        throw new IllegalArgumentException("El trabajador ya existe en el zoológico.");
	    trabajadores.add(trabajador);
	}

	public Animal agregarAnimal(int id, LocalDate nacimiento, Sexo sexo, Especie especie) {
		for (Animal existente : animales) {
	        if (existente.getId() == id) {
	            throw new IllegalArgumentException("Ya existe un animal con el mismo ID.");
	        }
	    }	
	    
	    Animal nuevoAnimal = new Animal(id, nacimiento, sexo, especie);
	    animales.add(nuevoAnimal);
	    return nuevoAnimal;
	}

	public void agregarEspecie(String nombreComun, String nombreCientifico, int esperanzaVida, double pesoPromedio, double tamano, double cantidadComida, Alimentacion alimentacion, TipoEntorno tipoEntorno) {
		for (Especie existente : especies) {
	        if (existente.getNombreComun().equals(nombreComun)) {
	            throw new IllegalArgumentException("Ya existe una especie con ese nombre.");
	        }
	    }
	    
	    Especie nuevaEspecie = new Especie( esperanzaVida, nombreComun, nombreCientifico, alimentacion, tipoEntorno, tamano, pesoPromedio , cantidadComida);
	    especies.add(nuevaEspecie);
	}
	
	
	//BUSCAR ANIMALES POR ESPECIE
	public ArrayList<Animal> obtenerAnimalesPorEspecie(Especie especie) {
	    ArrayList<Animal> resultado = new ArrayList<Animal>();
	    for (Animal a : animales) {
	        if (a.getEspecie().equals(especie)) {
	            resultado.add(a);
	        }
	    }
	    return resultado;
	}

	//VERIFICAR SI UNA CELDA TIENE ESPACIO PARA UNA ESPECIE
	public Celda encontrarCeldaDisponiblePara(Especie especie) {
	    Celda celdaEncontrada = null;
	    for (int i = 0; i < celdas.size() && celdaEncontrada == null; i++) {
	        Celda c = celdas.get(i);
	        boolean compatible = c.esCompatibleCon(especie);
	        boolean capacidad = c.tieneCapacidad();
	        if (compatible && capacidad) {
	            celdaEncontrada = c;
	        }
	    }
	    return celdaEncontrada;
	}
	
	//REGISTRAR INGRESO DE NUEVO ANIMAL
	public boolean ingresarNuevoAnimal(Animal animal) {
		if (animal == null)
	        throw new IllegalArgumentException("El animal no puede ser nulo.");
		if (!especies.contains(animal.getEspecie())) {
	        throw new IllegalStateException("La especie del animal no está registrada en el zoológico.");
	    }
	    boolean ingresado = false;
	    Celda celdaDisponible = encontrarCeldaDisponiblePara(animal.getEspecie());
	    if (celdaDisponible != null) {
	        celdaDisponible.agregarAnimal(animal);
	        animales.add(animal);
	        ingresado = true;
	    }
	    return ingresado;
	}
	
	//ELIMINACION
		public boolean eliminarCelda(Celda celda) {
		    if (celda == null)
		        throw new IllegalArgumentException("La celda no puede ser nula.");
		    return celdas.remove(celda);
		}

		public boolean eliminarAnimal(Animal animal) {
		    if (animal == null)
		        throw new IllegalArgumentException("El animal no puede ser nulo.");
		    return animales.remove(animal);
		}

		public boolean eliminarTrabajador(Trabajador trabajador) {
		    if (trabajador == null)
		        throw new IllegalArgumentException("El trabajador no puede ser nulo.");
		    return trabajadores.remove(trabajador);
		}

		public boolean eliminarEspecie(Especie especie) {
		    if (especie == null)
		        throw new IllegalArgumentException("La especie no puede ser nula.");
		    return especies.remove(especie);
		}
		
	    public void actualizarEdadCustodio(Custodio custodio, int nuevaEdad) {
	        try {
	            custodio.setEdad(nuevaEdad);
	        } catch (IllegalArgumentException e) {
	            if (e.getMessage().contains("debe ser asignado a labores de servicio")) {
	                Servicio servicio = convertirCustodioAServicio(custodio);
	                trabajadores.remove(custodio);
	                trabajadores.add(servicio);
	            } else {
	                throw e;
	            }
	        }
	    }

	    private Servicio convertirCustodioAServicio(Custodio c) {
	        return new Servicio(c.getNombre(), c.getNumCarnet(), "Zona Indefinida");
	    }
	    
	    public ArrayList<Celda> getTodasLasCeldas() {
	        return new ArrayList<>(celdas);
	    }
	    public ArrayList<Especie> getEspecies() {
	        return new ArrayList<>(especies);
	    }
	    
	    
	    public ArrayList<String> getReporteOcupacionCeldas() {
	        ArrayList<String> reporte = new ArrayList<>();
	        if (celdas.isEmpty()) {
	            reporte.add("No hay celdas creadas.");
	            return reporte;
	        }

	        for (Celda c : celdas) {
	            int capacidad = c.getCapacidadTotal();
	            int ocupados = c.getAnimales().size();
	            double porcentaje = capacidad == 0 ? 0 : (ocupados * 100.0 / capacidad);

	            String linea = String.format("Celda %s: %d/%d animales (%.2f%% ocupado)",
	                c.getId(), ocupados, capacidad, porcentaje);

	            reporte.add(linea);
	        }

	        return reporte;
	    }
	    public double getSalarioTotal() {
	        double total = 0;
	        for (Trabajador t : trabajadores) {
	            total += t.calcularSalarioTotal();
	        }
	        return total;
	    }
	    public String getReportePorcentajeCeldas() {
	    	 if (celdas.isEmpty()) {
	    	        return "No hay celdas creadas.";
	    	    }
	        int total = celdas.size();

	        int disponibles = 0, ocupadas = 0, mantenimiento = 0;

	        for (Celda c : celdas) {
	            switch (c.getDisponibilidad()) {
	                case DISPONIBLE:
	                    disponibles++;
	                    break;
	                case OCUPADA:
	                    ocupadas++;
	                    break;
	                case MANTENIMIENTO:
	                    mantenimiento++;
	                    break;
	            }
	        }

	        double pDisponibles = (disponibles * 100.0) / total;
	        double pOcupadas = (ocupadas * 100.0) / total;
	        double pMantenimiento = (mantenimiento * 100.0) / total;

	        return String.format(
	            "Total Celdas: %d%nDisponibles: %d (%.2f%%)%nOcupadas: %d (%.2f%%)%nEn mantenimiento: %d (%.2f%%)",
	            total, disponibles, pDisponibles, ocupadas, pOcupadas, mantenimiento, pMantenimiento);
	    }
	    public ArrayList<String> getReporteSuperpoblacionEspecies() {
	        ArrayList<String> reporte = new ArrayList<>();
	        Map<Especie, Integer> conteoPorEspecie = new HashMap<>();

	        for (Animal animal : animales) {
	            Especie especie = animal.getEspecie();
	            conteoPorEspecie.put(especie, conteoPorEspecie.getOrDefault(especie, 0) + 1);
	        }

	        for (Especie especie : conteoPorEspecie.keySet()) {
	            int cantidadAnimales = conteoPorEspecie.get(especie);
	            int capacidadCompatible = 0;

	            for (Celda celda : celdas) {
	                if (celda.esCompatibleCon(especie)) {
	                	capacidadCompatible += celda.getCapacidadTotal();
	                }
	            }

	            double porcentaje = capacidadCompatible == 0 ? 0 : (cantidadAnimales * 100.0 / capacidadCompatible);
	            String estado;

	            if (porcentaje <= 60) {
	                estado = "Estable";
	            } else if (porcentaje <= 85) {
	                estado = "En riesgo";
	            } else {
	                estado = "Superpoblado";
	            }

	            reporte.add(String.format(
	                "Especie: %s - %d animales / %d capacidad (%.2f%%) → %s",
	                especie.getNombreComun(), cantidadAnimales, capacidadCompatible, porcentaje, estado
	            ));
	        }

	        if (reporte.isEmpty()) {
	            reporte.add("No hay animales registrados en el zoológico.");
	        }

	        return reporte;
	    }
	    
}
