package main;

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

		// Declaramos una variable que almacenará el nombre.
		String nombre;

		// Declaramos una variable que almacenará los apellidos.
		String apellido;

		// Declaramos una variable que almacenará el telefono.
		String telefono;

		// Declaramos una variable que almacenará el email.
		String email;

		// Declaramos una variable que almacenará la fecha de nacimiento.
		String fecha;
		
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

		// Hacemos un while.
		while (eleccion != 0) {

			// Hacemos un switch de la eleccion.
			switch (eleccion) {

			// Primer case.
			case 1 -> {

				// Preguntamos el nombre.
				System.out.println("Introduce el nombre del estudiante.");

				// Leemos entrada de teclado.
				nombre = sc.nextLine();

				// Preguntamos el apellido.
				System.out.println("Introduce el apellido del estudiante..");

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
				System.out.println("Introduce la fecha del estudiante.");

				// Leemos entrada de teclado.
				fecha = sc.nextLine();

				// Creamos el estudiante.
				Estudiante est = new Estudiante(nombre, apellido, fecha, email, telefono);
				
				// Añadimos el estudiante a la bbdd.
				conex

			}

			// Segundo case.
			case 2 -> {

			}

			// Tercer case.
			case 3 -> {

			}

			// Cuarto case.
			case 4 -> {

			}

			// Quinto case.
			case 5 -> {

			}

			// Case default.
			default -> {

				// Imprimimos que elija una opción válida.
				System.out.println("Introduce una opción válida.");

			}
			}
		}
		}
	}

	/**
	 * Método estático que imprimirá el menú.
	 */
	static void menu() {

		System.out.println(
				"1. Crear estudiante.\r\n" + "2. Listar todos los estudiantes.\r\n" + "3. Buscar estudiante por id.\r\n"
						+ "4. Modificar estudiante.\r\n" + "5. Eliminar estudiante.\r\n" + "0. Salir.\r\n" + "");

	}
}
