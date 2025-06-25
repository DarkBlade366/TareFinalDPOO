package clases;

public class Cuidador extends Trabajador {
	private double horasTrabajadas;
	public static double pagoPorHora = 50.0;
	private int horaInicio1 = -1;
	private int horaFin1 = -1;
	private int horaInicio2 = -1;
	private int horaFin2 = -1;
	private Celda celdaAsignada1;
	private Celda celdaAsignada2;

	public Cuidador(String nombre, String numCarnet, double horasTrabajadas, int horaInicio1, int horaFin1, int horaInicio2, int horaFin2) {
		super(nombre, numCarnet);
		setHorasTrabajadas(horasTrabajadas);
		setHoraInicio1(horaInicio1);
		setHoraFin1(horaFin1);
		setHoraInicio2(horaInicio2);
		setHoraFin2(horaFin2);
	}


	public Celda getCeldaAsignada1() {
		return celdaAsignada1;
	}

	public void setCeldaAsignada1(Celda celdaAsignada1) {
		this.celdaAsignada1 = celdaAsignada1;
	}
	public Celda getCeldaAsignada2() {
		return celdaAsignada2;
	}

	public void setCeldaAsignada2(Celda celdaAsignada2) {
		this.celdaAsignada2 = celdaAsignada2;
	}
	
	public int getHoraInicio1() {
		return horaInicio1;
	}

	public void setHoraInicio1(int horaInicio1) {
		if (horaInicio1 < 0 || horaInicio1 >= 24)
			throw new IllegalArgumentException("La hora de inicio de celda 1 debe estar entre 0 y 23.");
		if (this.horaFin1 != -1 && horaInicio1 >= this.horaFin1)
			throw new IllegalArgumentException("La hora de inicio de celda 1 debe ser menor que la hora de fin.");
		this.horaInicio1 = horaInicio1;
	}

	public int getHoraFin1() {
		return horaFin1;
	}

	public void setHoraFin1(int horaFin1) {
		if (horaFin1 <= 0 || horaFin1 > 24)
			throw new IllegalArgumentException("La hora de fin de celda 1 debe estar entre 1 y 24.");
		if (this.horaInicio1 != -1 && this.horaInicio1 >= horaFin1)
			throw new IllegalArgumentException("La hora de fin de celda 1 debe ser mayor que la hora de inicio.");
		this.horaFin1 = horaFin1;
	}

	public int getHoraInicio2() {
		return horaInicio2;
	}

	public void setHoraInicio2(int horaInicio2) {
		if (celdaAsignada2 != null) {
			if (horaInicio2 < 0 || horaInicio2 >= 24)
				throw new IllegalArgumentException("La hora de inicio de celda 2 debe estar entre 0 y 23.");
			if (this.horaFin2 != -1 && horaInicio2 >= this.horaFin2)
				throw new IllegalArgumentException("La hora de inicio de celda 2 debe ser menor que la hora de fin.");
		}
		this.horaInicio2 = horaInicio2;
	}
	public int getHoraFin2() {
		return horaFin2;
	}

	public void setHoraFin2(int horaFin2) {
		if (celdaAsignada2 != null) {
			if (horaFin2 <= 0 || horaFin2 > 24)
				throw new IllegalArgumentException("La hora de fin de celda 2 debe estar entre 1 y 24.");
			if (this.horaInicio2 != -1 && this.horaInicio2 >= horaFin2)
				throw new IllegalArgumentException("La hora de fin de celda 2 debe ser mayor que la hora de inicio.");
		}
		this.horaFin2 = horaFin2;
	}

	public double getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public void setHorasTrabajadas(double horasTrabajadas) {
		if (horasTrabajadas <= 0 || horasTrabajadas > 60)
			throw new IllegalArgumentException("Las horas trabajadas deben estar entre 1 y 60.");
		this.horasTrabajadas = horasTrabajadas;
	}

	@Override
	public double calcularSalarioTotal() {
		return SALARIO_BASICO + (horasTrabajadas * pagoPorHora);
	}

	@Override
	public String toString() {
		return super.toString() + String.format(", Horas trabajadas: %.2f", horasTrabajadas);
	}

}
