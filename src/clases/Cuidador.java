package clases;

public class Cuidador extends Trabajador {
	private double horasTrabajadas;
	public static double pagoPorHora = 50.0;
	private int horaInicio;
	private int horaFin;

	public Cuidador(String nombre, String numCarnet, double horasTrabajadas, int horaInicio, int horaFin) {
		super(nombre, numCarnet);
		setHorasTrabajadas(horasTrabajadas);
		setHoraInicio(horaInicio);
		setHoraFin(horaFin);
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		if (horaInicio < 0 || horaInicio >= 24)
			throw new IllegalArgumentException("La hora de inicio debe estar entre 0 y 23.");
		if (this.horaFin != 0 && horaInicio >= this.horaFin)
			throw new IllegalArgumentException("La hora de inicio debe ser menor que la hora de fin.");
		this.horaInicio = horaInicio;
	}

	public int getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(int horaFin) {
		if (horaFin <= 0 || horaFin > 24)
			throw new IllegalArgumentException("La hora de fin debe estar entre 1 y 24.");
		if (this.horaInicio != 0 && this.horaInicio >= horaFin)
			throw new IllegalArgumentException("La hora de fin debe ser mayor que la hora de inicio.");
		this.horaFin = horaFin;
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
