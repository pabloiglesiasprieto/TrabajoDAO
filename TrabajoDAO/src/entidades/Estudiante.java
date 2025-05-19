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
	 * Atributo privado que será el email del estudiante.
	 */
	private String email;

	/**
	 * Atributo privado que será el telefono del estudiante.
	 */
	private String telefono;

	/**
	 * Atributo privado que será la fecha de nacimiento del estudiante.
	 */
	private String fecha;

	/**
	 * Constructor de Estudiante.
	 * 
	 * @param nombre   Nombre del estudiante.
	 * @param apellido Apellido del estudiante.
	 * @param email    Email del estudiante.
	 * @param telefono Telefono del estudiante.
	 * @param fecha    Fecha de nacimiento del estudiante.
	 */
	public Estudiante(String nombre, String apellido, String email, String telefono, String fecha) {

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
	

}
