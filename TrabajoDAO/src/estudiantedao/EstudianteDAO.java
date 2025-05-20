package estudiantedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import credenciales.Credenciales;
import entidades.Estudiante;

/**
 * Clase pública que manejará todo lo relacionado a la manipulación de datos de
 * la tabla Estudiante.
 */
public class EstudianteDAO {

	/**
	 * Atributo privado que almacenará la conexión.
	 */
	private Connection conex;

	/**
	 * Constructor de la clase de manipulación de datos que inicializará la conexión
	 * con la BBDD.
	 */
	public EstudianteDAO() {

		// Creamos la conexión.
		try {
			this.conex = DriverManager.getConnection(Credenciales.URL, Credenciales.USUARIO, Credenciales.CONTRASEÑA);

			// Cogemos la excepción.
		} catch (SQLException e) {

			// Imprimimos el error.
			System.out.println("Se ha producido un error a la hora de conectarse a la BBDD: " + e.getMessage());

		}
	}

	/**
	 * Método que devuelve la conexión.
	 * 
	 * @return Devuelve la conexión a la bbdd, si es null es que la conexión ha
	 *         fallado.
	 */
	public Connection getConnection() {

		return this.conex;
	}

	/**
	 * Método que inserta en la tabla un estudiante.
	 * 
	 * @param est Estudiante a insertar en la bbdd.
	 * @return Devuelve el número de filas insertadas.
	 */
	public int insertar(Estudiante est) {

		// Declaramos la variable que almacenará la cantidad de filas añadidas.
		int insertedRows = 0;

		// Creamos el statement.
		try {
			PreparedStatement ps = conex.prepareStatement(
					"INSERT INTO ESTUDIANTES (nombre,apellidos,fecha_nacimiento,email,telefono) values (?,?,?,?,?)");

			// Añadimos el nombre al query.
			ps.setString(1, est.getNombre());

			// Añadimos el apellido al query.
			ps.setString(2, est.getApellido());

			// Añadimos la fecha al query.
			ps.setString(3, est.getFecha());

			// Añadimos el email al query.
			ps.setString(4, est.getEmail());

			// Añadimos el telefono al query.
			ps.setString(5, est.getTelefono());

			// Ejecutamos la sentencia.
			insertedRows = ps.executeUpdate();

			// Recogemos la excepción.
		} catch (SQLException e) {

			// Imprimimos la excepción.
			System.out.println("Error a la hora de ejecutar la consulta: " + e.getMessage());
		}
		// Devolvemos la filas añadidas.
		return insertedRows;
	}

	/**
	 * Método que borra un estudiante de la tabla.
	 * 
	 * @param id ID del estudiante a borrar.
	 * @return Devuelve un booleano indicando si se ha podido borar
	 */
	public boolean borrar(int id) {

		// Declaramos la variable que almacenará la cantidad de filas añadidas.
		int modRows = 0;

		// Creamos el statement.
		try {
			PreparedStatement ps = conex.prepareStatement("DELETE FROM ESTUDIANTES WHERE ID_ESTUDIANTE = ?");

			// Añadimos el id al query.
			ps.setInt(1, id);

			// Ejecutamos la sentencia.
			modRows = ps.executeUpdate();

			// Recogemos la excepción.
		} catch (SQLException e) {

			// Imprimimos la excepción.
			System.out.println("Error a la hora de ejecutar la consulta: " + e.getMessage());
		}
		// Devolvemos la filas añadidas.
		return modRows == 1;
	}

	/**
	 * Método que devuelve todos los estudiantes de la tabla.
	 * 
	 * @return Devuelve una lista con todos los estudiantes.
	 */
	public List<Estudiante> consulta() {

		// Declaramos el resultset.
		ResultSet rs;

		// Creamos la lista.
		ArrayList<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();

		// Creamos un Statement.
		try {
			Statement st = conex.createStatement();

			// Ejecutamos la consulta.
			rs = st.executeQuery("SELECT * FROM ESTUDIANTES");

			// Recorremos el resultado.
			while (rs.next()) {

				// Creamos un estudiante.
				Estudiante est = new Estudiante(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));

				// Lo añadimos a la lista.
				listaEstudiantes.add(est);
			}

			// Cogemos la excepción.
		} catch (SQLException e) {

			// Imprimimos el error.
			System.out.println("Error: " + e.getMessage());

		}
		// Devolvemos la lista.
		return listaEstudiantes;
	}

	/**
	 * Método que busca un estudiante por ID y lo guarda en un Estudiante.
	 * 
	 * @param id Parámetro de entrada que será el ID del estudiante a buscar en la
	 *           BBDD.
	 * @return Devuelve el estudiante a buscar, si es null es que no se ha
	 *         encontrado al estudiante.
	 */
	public Estudiante consultaID(int id) {

		// Declaramos un ResultSet.
		ResultSet rs;

		// Declaramos el estudiante.
		Estudiante est = null;

		// Creamos un PreparedStatement.
		try {
			PreparedStatement ps = conex.prepareStatement("SELECT * FROM ESTUDIANTES WHERE ID_ESTUDIANTE = ?");

			// Introducimos el ID en el query.
			ps.setInt(1, id);

			// Guardamos el resultado en el ResultSet.
			rs = ps.executeQuery();

			// Creamos el estudiante.
			est = new Estudiante(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));

			// Recogemos la excepción.
		} catch (SQLException e) {

			// Imprimimos el error.
			System.out.println("Error: " + e.getMessage());
		}
		// Devolvemos el estudiante.
		return est;
	}

	/**
	 * Método que modifica el telefono de un estudiante.
	 * 
	 * @param id       ID del Estudiante a modificar.
	 * @param telefono Telefono nuevo a modificar.
	 * @return Devuelve un booleano indicando si se ha modificado un registro o no.
	 */
	public boolean modificarTelefono(int id, String telefono) {

		// Declaramos la variable que almacenará las filas modificadas.
		int modRows = 0;

		// Creamos un PreparedStatement.
		try {
			PreparedStatement ps = conex
					.prepareStatement("UPDATE FROM ESTUDIANTES SET TELEFONO = ? WHERE ID_ESTUDIANTE = ?");

			// Rellenamos el UPDATE.
			ps.setString(1, telefono);
			ps.setInt(2, id);

			// Ejecutamos la consulta.
			modRows = ps.executeUpdate();

			// Recogemos la excepción.
		} catch (SQLException e) {

			// Imprimimos el error.
			System.out.println("Error: " + e.getMessage());
		}
		// Devolvemos un booleano indicando si se han modificado registros o no.
		return modRows == 1;

	}

	/**
	 * Método que modifica el email de un estudiante.
	 * 
	 * @param id    ID del Estudiante a modificar.
	 * @param email email nuevo a modificar.
	 * @return Devuelve un booleano indicando si se ha modificado un registro o no.
	 */
	public boolean modificarEmail(int id, String email) {

		// Declaramos la variable que almacenará las filas modificadas.
		int modRows = 0;

		// Creamos un PreparedStatement.
		try {
			PreparedStatement ps = conex
					.prepareStatement("UPDATE FROM ESTUDIANTES SET TELEFONO = ? WHERE ID_ESTUDIANTE = ?");

			// Rellenamos el UPDATE.
			ps.setString(1, email);
			ps.setInt(2, id);

			// Ejecutamos la consulta.
			modRows = ps.executeUpdate();

			// Recogemos la excepción.
		} catch (SQLException e) {

			// Imprimimos el error.
			System.out.println("Error: " + e.getMessage());
		}
		// Devolvemos un booleano indicando si se han modificado registros o no.
		return modRows == 1;

	}

}
