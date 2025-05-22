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

		// Declaramos una variable que almacenará el telefono.
		String telefono;

		// Declaramos una variable que almacenará el email.
		String email;

		// Creamos una conexión.
		EstudianteDAO conex = new EstudianteDAO();

		// Comprobamos que la conexión sea nula.
		if (conex.getConnection() != null) {

			// Preguntamos que acción quiere realizar.
			System.out.println("Que acción desea realizar.");

			// Imprimimos el menú.
			menu();

			// Guardamos la eleccion.
			eleccion = sc.nextInt();

			// Limpiamos buffer de entrada.
			sc.nextLine();

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

					// Preguntamos que valor modificar.
					eleccion = preguntarModificacion();

					// Hacemos un switch de la eleccion.
					switch (eleccion) {

					// Primer case.
					case 1 -> {

						// Preguntamos el nuevo telefono.
						System.out.println("Introduce el nuevo telefono.");

						// Leemos entrada de teclado.
						telefono = sc.nextLine();

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

						// Preguntamos el nuevo email.
						System.out.println("Introduce el nuevo email.");

						// Leemos entrada de teclado.
						email = sc.nextLine();

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

				// Quinto case.
				case 5 -> {

					// Preguntamos el id.
					System.out.println("Introduce el id del estudiante a borrar.");

					// Leemos entrada de teclado.
					id = sc.nextInt();

					// Limpiamos buffer de entrada.
					sc.nextLine();

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

				// Guardamos la eleccion.
				eleccion = sc.nextInt();

				// Limpiamos buffer de entrada.
				sc.nextLine();
			}
		}
		// Imprimimos que se ha salido del bucle.
		System.out.println("Saliendo...");
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

		// Leemos entrada de teclado.
		eleccion = sc.nextInt();

		// Limpiamos buffer de entrada.
		sc.nextLine();

		// Devolvemos la eleccion.
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

		// Leemos entrada de teclado.
		id = sc.nextInt();

		// Limpiamos buffer de entrada.
		sc.nextLine();
		return id;
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
		System.out.println("Introduce el nombre del estudiante.");

		// Leemos entrada de teclado.
		nombre = sc.nextLine();

		// Preguntamos el apellido.
		System.out.println("Introduce el apellido del estudiante.");

		// Leemos entrada de teclado.
		apellido = sc.nextLine();

		// Preguntamos el email.
		System.out.println("Introduce el email del estudiante.");

		// Leemos entrada de teclado.
		email = sc.nextLine();

		// Preguntamos el telefono.
		System.out.println("Introduce el telefono del estudiante.");

		// Leemos entrada de teclado.
		telefono = sc.nextLine();

		// Preguntamos la fecha..
		System.out.println("Introduce la fecha del estudiante. (yyyy-MM-dd)");

		// Leemos entrada de teclado.
		fecha = sc.nextLine();

		// Creamos el estudiante.
		est = new Estudiante(nombre, apellido, fecha, email, telefono);

		// Devolvemos el estudiante.
		return est;
	}

	/**
	 * Método estático que imprimirá el menú.
	 */
	static void menu() {

		System.out.println(
				"1. Crear estudiante.\r\n" + "2. Listar todos los estudiantes.\r\n" + "3. Buscar estudiante por id.\r\n"
						+ "4. Modificar estudiante.\r\n" + "5. Eliminar estudiante.\r\n" + "0. Salir.");

	}
}
