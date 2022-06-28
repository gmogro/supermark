package Entidades;

public class Persona {
	private String nombre;
	private String apellido;
	private String domicilio;
	private String documento;
	private String provincia;
	private String codigoPostal;
	private String fechaNacimiento;
	private String telefono;
	
	public Persona() {
	}
	public Persona(String nombre, String apellido, String domicilio, String documento, String provincia,
			String codigoPostal, String fechaNacimiento, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.documento = documento;
		this.provincia = provincia;
		this.codigoPostal = codigoPostal;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
