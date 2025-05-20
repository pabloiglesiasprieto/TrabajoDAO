package entidades;

/**
 * Clase que representará un estudiante con una identificacion(id), un nombre,
 * apellido, email, telefono, fechanacimiento.
 */
public class Estudiante {

	/**
	 * Atributo privado que identificará al estudiante.
	 */
	private int id;

	/**
	 * Atributo privado que será el nombre del estudiante.
	 */
	private String nombre;

	/**
	 * Atributo privado que será el apellido del estudiante.
	 */
	private String apellido;

	/**
	 * Atributo privado que será la fecha de nacimiento del estudiante.
	 */
	private String fecha;

	/**
	 * Atributo privado que será el email del estudiante.
	 */
	private String email;

	/**
	 * Atributo privado que será el telefono del estudiante.
	 */
	private String telefono;

	/**
	 * Constructor de Estudiante.
	 * 
	 * @param nombre   Nombre del estudiante.
	 * @param apellido Apellido del estudiante.
	 * @param email    Email del estudiante.
	 * @param telefono Telefono del estudiante.
	 * @param fecha    Fecha de nacimiento del estudiante.
	 */
	public Estudiante(String nombre, String apellido, String fecha, String email, String telefono) {

		if (nombre != null && !nombre.isBlank()) {
			this.nombre = nombre;
		}

		if (apellido != null && !apellido.isBlank()) {
			this.apellido = apellido;
		}

		if (email != null && !email.isBlank()) {
			this.email = email;
		}

		if (telefono != null && !telefono.isBlank()) {
			this.telefono = telefono;
		}

		if (fecha != null && !fecha.isBlank()) {
			this.fecha = fecha;
		}
	}

	/**
	 * Método que obtiene el nombre del estudiante.
	 * 
	 * @return Devuelve el nombre del estudiante.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que obtiene el apellido del estudiante.
	 * 
	 * @return Devuelve el apellido del estudiante.
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Método que obtiene el email del estudiante.
	 * 
	 * @return Devuelve el email del estudiante.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Método que modifica el email del estudiante.
	 * 
	 * @param email Email nuevo del estudiante.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Método que obtiene el telefono del estudiante.
	 * 
	 * @return Teléfono del estudiante.
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Método que modifica el teléfono del estudiante.
	 * 
	 * @param telefono Nuevo teléfono del estudiante.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Método que obtiene el id del estudiante.
	 * 
	 * @return Obtiene el id del estudiante.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Método que obtiene la fecha de nacimiento del estudiante.
	 * 
	 * @return Fecha de nacimiento del estudiante.
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @Override Método que indica como se imprimirá el estudiante.
	 */
	public String toString() {

		String cadena = "";
		cadena += this.nombre + " " + this.apellido + " " + this.email + " " + this.telefono + this.fecha;
		return cadena;

	}

}
