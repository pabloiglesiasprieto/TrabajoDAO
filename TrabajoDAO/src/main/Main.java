package main;

import java.util.ArrayList;
import java.util.Scanner;

import entidades.Estudiante;
import estudiantedao.EstudianteDAO;

public class Main {

	// Creamos un Scanner.
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// Declaramos una variable que almacenará el id.
		int id;

		// Declaramos una variable que almacenará la eleccion del usuario.
		int eleccion;

		// Declaramos una variable de tipo estudiante.
		Estudiante est;

		// Declaramos una variable de tipo lista para almacenar los estudiantes.
		ArrayList<Estudiante> listaEstudiante;

		// Creamos una conexión.
		EstudianteDAO conex = new EstudianteDAO();

		// Comprobamos que la conexión sea nula.
		if (conex.getConnection() != null) {

			// Preguntamos que acción quiere realizar.
			System.out.println("Que acción desea realizar.");

			// Imprimimos el menú.
			menu();

			// Preguntamos que acción realizar.
			eleccion = preguntarEleccion();

			// Hacemos un while.
			while (eleccion != 0) {

				// Hacemos un switch de la eleccion.
				switch (eleccion) {

				// Primer case.
				case 1 -> {

					// Creamos el estudiante
					est = crearEstudiante();

					// Añadimos el estudiante a la bbdd.
					System.out.println(conex.insertar(est) ? "El estudiante se ha insertado"
							: "No se ha podido insertar el estudiante");
				}

				// Segundo case.
				case 2 -> {

					// Almacenamos en la lista los estudiantes.
					listaEstudiante = conex.consulta();

					// Imprimimos los estudiantes.
					imprimirEstudiantes(listaEstudiante);
				}
				// Tercer case.
				case 3 -> {

					// Preguntamos el ID del estudiante.
					id = pedirID();

					// Llamamos a la función.
					est = conex.consultaID(id);

					// Comprobamos que el estudiante no sea null.
					if (est != null) {

						// Imprimimos el estudiante.
						System.out.println(est);
					}
					// Si el estudiante es null.
					else {

						// Imprimimos que el estudiante no se ha encontrado.
						System.out.println("El estudiante no se ha encontrado.");
					}
				}
				// Cuarto case.
				case 4 -> {

					// Preguntamos el ID.
					id = pedirID();

					// Buscamos si existe el estudiante.
					est = conex.consultaID(id);

					// Comprobamos que exista el estudiante.
					if (est != null) {

						// Preguntamos que valor modificar.
						eleccion = preguntarModificacion();

						// Llamamos a la función que modifica al estudiante según la eleccion del
						// usuario. 1 = Telefono, 2 = Email.
						modificacionAtributo(id, eleccion, conex);

						// Si el estudiante no existe.
					} else {

						// Imprimimos que no existe.
						System.out.println("El estudiante a modificar no existe.");
					}
				}
				// Quinto case.
				case 5 -> {

					// Leemos entrada de teclado.
					id = pedirID();

					// Llamamos a la función para borrar el estudiante.
					if (conex.borrar(id)) {

						// Imprimimos que el estudiante se ha borrado correctamente.
						System.out.println("El estudiante se ha borrado.");

						// Si no se ha borrado.
					} else {

						// Imprimimos que no se ha podido borrar.
						System.out.println("El estudiante no se ha podido borrar.");
					}
				}
				// Case default.
				default -> {

					// Imprimimos que elija una opción válida.
					System.out.println("Introduce una opción válida.");
				}
				}
				// Imprimimos el menú.
				menu();

				// Preguntamos la eleccion.
				eleccion = preguntarEleccion();
			}
		}
		// Imprimimos que se ha salido del bucle.
		System.out.println("Saliendo...");

		// Cerramos el Scanner.
		sc.close();
	}

	/**
	 * Función que según una eleccion y una id específica modifica un valor u otro
	 * de un estudiante concreto.
	 * 
	 * @param id       ID del estudiante a la cual se va a realizar la modificación.
	 * @param eleccion Elección para modificar. 1 = Teléfono; 2 = Email.
	 * @param conex    Conexión con la BBDD.
	 */
	private static void modificacionAtributo(int id, int eleccion, EstudianteDAO conex) {

		// Declaramos una variable que almacenará el telefono.
		String telefono;

		// Declaramos una variable que almacenará el email.
		String email;

		// Hacemos un switch de la eleccion.
		switch (eleccion) {

		// Primer case.
		case 1 -> {

			// Pedimos el telefono.
			telefono = pedirTelefono();

			// Llamamos a la función de modificar el telefono.
			if (conex.modificarTelefono(id, telefono)) {

				// Imprimimos que el telefono se ha modificado.
				System.out.println("Se ha modificado el teléfono correctamente.");
			}
			// Si no se ha modificado ningun registro.
			else {

				// Imprimimos que no se ha podido modificar el telefono.
				System.out.println("No se ha podido modificar el teléfono.");
			}
		}
		// Segundo case.
		case 2 -> {

			// Pedimos el Email.
			email = pedirEmail();

			// Llamamos a la función de modificar el email.
			if (conex.modificarEmail(id, email)) {

				// Imprimimos que el email se ha modificado.
				System.out.println("Se ha modificado el email correctamente.");
			}
			// Si no se ha modificado ningun registro.
			else {

				// Imprimimos que no se ha podido modificar el email.
				System.out.println("No se ha podido modificar el email.");
			}
		}
		}
	}

	/**
	 * Función que pregunta al usuario que acción realizar del switch.
	 * 
	 * @return Devuelve un entero para saber que acción del menú quiere realizar el
	 *         usuario.
	 */
	private static int preguntarEleccion() {

		// Declaramos una variable entero para la elección del usuario.
		int eleccion;

		// Guardamos la eleccion.
		eleccion = sc.nextInt();

		// Limpiamos buffer de entrada.
		sc.nextLine();

		// Devolvemos la elección del usuario.
		return eleccion;
	}

	/**
	 * Función que pregunta el ID.
	 * 
	 * @return Devuelve el ID que el usuario ha introducido por consola.
	 */
	private static int pedirID() {

		// Declaramos una variable que almacenará el id.
		int id;

		// Preguntamos el id.
		System.out.println("Introduce el id del estudiante.");

		id = preguntarEleccion();

		// Devolvemos el id.
		return id;
	}

	/**
	 * Función que pregunta al usuario el nombre del estudiante.
	 * 
	 * @return Devuelve la cadena que ha introducido el usuario por consola.
	 */
	private static String pedirNombre() {

		// Declaramos una variable para almacenar la cadena que introduzca el usuario.
		String nombre;

		// Preguntamos el nombre.
		System.out.println("Introduce el nombre del estudiante.");

		// Leemos entrada de teclado.
		nombre = sc.nextLine();

		// Devolvemos el nombre que ha introducido el usuario.
		return nombre;
	}

	/**
	 * Función que pregunta al usuario el apellido del estudiante.
	 * 
	 * @return Devuelve la cadena que ha introducido el usuario por consola.
	 */
	private static String pedirApellidos() {

		// Declaramos una variable para almacenar el apellido del estudiante.
		String apellido;

		// Preguntamos el apellido.
		System.out.println("Introduce el apellido del estudiante.");

		// Leemos entrada de teclado.
		apellido = sc.nextLine();

		// Devolvemos el apellido.
		return apellido;
	}

	/**
	 * Función que pregunta el email del estudiante.
	 * 
	 * @return Devuelve una cadena que será el email del estudiante.
	 */
	private static String pedirEmail() {

		// Declaramos una variable que almacenará el email dado por consola.
		String email;

		// Preguntamos el nuevo email.
		System.out.println("Introduce el nuevo email.");

		// Leemos entrada de teclado.
		email = sc.nextLine();

		// Devolvemos el email.
		return email;
	}

	/**
	 * Función que pregunta el telefono del estudiante.
	 * 
	 * @return Devuelve una cadena que será el telefono del estudiante.
	 */
	private static String pedirTelefono() {

		// Declaramos una cadena que almacenará el telefono del estudiante.
		String telefono;

		// Preguntamos el nuevo telefono.
		System.out.println("Introduce el nuevo telefono.");

		// Leemos entrada de teclado.
		telefono = sc.nextLine();

		// Devolvemos el telefono.
		return telefono;
	}

	/**
	 * Función que pregunta al usuario la fecha de nacimiento del estudiante.
	 * 
	 * @return Devuelve la fecha que ha introducido el usuario por consola.
	 */
	private static String pedirFecha() {

		// Declaramos una cadena que almacenará la fecha introducida por el usuario.
		String fecha;

		// Preguntamos la fecha..
		System.out.println("Introduce la fecha del estudiante. (yyyy-MM-dd)");

		// Leemos entrada de teclado.
		fecha = sc.nextLine();

		// Devolvemos la fecha.
		return fecha;
	}

	/**
	 * Función que crea un estudiante.
	 * 
	 * @return Devuelve el estudiante que se ha creado.
	 */
	private static Estudiante crearEstudiante() {

		// Declaramos un estudiante.
		Estudiante est;

		// Declaramos una variable que almacenará el nombre.
		String nombre;

		// Declaramos una variable que almacenará el apellido.
		String apellido;

		// Declaramos una variable que almacenará el telefono.
		String telefono;

		// Declaramos una variable que almacenará el email.
		String email;

		// Declaramos una variable que almacenará la fecha de nacimiento.
		String fecha;

		// Preguntamos el nombre.
		nombre = pedirNombre();

		// Preguntamos el apellido.
		apellido = pedirApellidos();

		// Preguntamos el email.
		email = pedirEmail();

		// Preguntamos el telefono.
		telefono = pedirTelefono();

		// Preguntamos la fecha.
		fecha = pedirFecha();

		// Creamos el estudiante.
		est = new Estudiante(nombre, apellido, fecha, email, telefono);

		// Devolvemos el estudiante.
		return est;
	}

	/**
	 * Función para preguntar que valor se desea modificar.
	 * 
	 * @return Devuelve la eleccion del usuario sobre el valor a modificar.
	 */
	private static int preguntarModificacion() {

		// Declaramos una variable que almacenará la eleccion.
		int eleccion;

		// Preguntamos que valor quiere modificar.
		System.out.println("Que valor quieres modificar.");

		// Imprimimos las 2 opciones.
		System.out.println("1. Telefono.\n2. Email.");

		// Almacenamos la elección del usuario en una variable.
		eleccion = preguntarEleccion();

		// Devolvemos la eleccion.
		return eleccion;
	}

	/**
	 * Función que imprime una lista de estudiantes.
	 * 
	 * @param listaEstudiante Lista que contiene todos los estudiantes de la bbdd.
	 */
	private static void imprimirEstudiantes(ArrayList<Estudiante> listaEstudiante) {

		// Bucle para recorrer la lista.
		for (Estudiante estu : listaEstudiante) {

			// Imprimimos la información del estudiante.
			System.out.println(estu);

			// Imprimimos una linea para dividir los estudiantes.
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------");
		}
	}

	/**
	 * Método estático que imprimirá el menú.
	 */
	static void menu() {

		// Imprimimos el menú.
		System.out.println(
				"1. Crear estudiante.\r\n" + "2. Listar todos los estudiantes.\r\n" + "3. Buscar estudiante por id.\r\n"
						+ "4. Modificar estudiante.\r\n" + "5. Eliminar estudiante.\r\n" + "0. Salir.");
	}
}