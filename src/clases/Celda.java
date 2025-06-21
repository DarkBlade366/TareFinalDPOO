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
	
		public void agregarCuidador(Cuidador cuidador) {
			if (cuidador == null)
				throw new IllegalArgumentException("El cuidador no puede ser nulo.");
			if (cuidadores.contains(cuidador))
				throw new IllegalArgumentException("El cuidador ya está asignado a esta celda.");
	
			int cantidadSolapados = 0;
			for (Cuidador c : cuidadores) {
				if (seSuperponen(c, cuidador)) {
					cantidadSolapados++;
					if (cantidadSolapados >= 2)
						throw new IllegalArgumentException("No se pueden asignar más de 2 cuidadores en el mismo horario.");
				}
			}
			cuidadores.add(cuidador);
		}
		private boolean seSuperponen(Cuidador c1, Cuidador c2) {
			return c1.getHoraInicio() < c2.getHoraFin() && c2.getHoraInicio() < c1.getHoraFin();
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
	
		public boolean puedeAgregarCuidador(int nuevoInicio, int nuevoFin) {
			if (cuidadores.size() >= 2) {
		        return false;
		    }
	
		    for (Cuidador c : cuidadores) {
		        int inicioExistente = c.getHoraInicio();
		        int finExistente = c.getHoraFin();
	
		        boolean solapan = !(nuevoFin <= inicioExistente || nuevoInicio >= finExistente);
		        if (solapan) {
		            return false;
		        }
		    }
	
		    return true;
		}
	}
	
