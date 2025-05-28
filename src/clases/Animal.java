package clases;

public class Animal {
	private int id;
	private String nacimiento;
	private String sexo;
	
	public Animal(int id, String nacimiento, String sexo) {
		setId(id);
		setNacimiento(nacimiento);
		setSexo(sexo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {	
		this.id = id;
	}

	public String getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	

}
