package clases;

public class Animal {
	protected String id;
	protected String nacimiento;
	protected String sexo;
	
	public Animal(String id, String nacimiento, String sexo) {
		setId(id);
		setNacimiento(nacimiento);
		setSexo(sexo);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
