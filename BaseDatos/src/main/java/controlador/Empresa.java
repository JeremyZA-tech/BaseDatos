package controlador;

public class Empresa {
  private Connection conn = null;
	
	/**
	 * Constructor
	 */

	public Empresa() {
		conn = BaseDatos.getConnection();
		createTables();
	}

	
	/**
	 * Crea el esquema de la base de datos
	 * 
	 * @throws SQLException
	 */
	private void createTables() {
		String sql = null;
		if (BaseDatos.dbType.equalsIgnoreCase("sqlite")) {
			sql = """
						CREATE TABLE IF NOT EXISTS departamento (
							id INTEGER PRIMARY KEY AUTOINCREMENT,
							nombre TEXT NOT NULL,
							jefe INTEGER
						)
					""";
		}
		if (BaseDatos.dbType.equalsIgnoreCase("sqlite")) {
			sql = """
						CREATE TABLE IF NOT EXISTS empleado (
							id INTEGER PRIMARY KEY AUTOINCREMENT,
							nombre TEXT NOT NULL,
							salario REAL DEFAULT 0.0,
							departamento INTEGER
						)
					""";
		}
		if (BaseDatos.dbType.equalsIgnoreCase("mariadb")) {
			sql = """
						CREATE TABLE IF NOT EXISTS departamento (
						  id INT PRIMARY KEY AUTOINCREMENT,
						  nombre VARCHAR(255) NOT NULL,
						  jefe INT
						)
					""";
		}
		if (BaseDatos.dbType.equalsIgnoreCase("mariadb")) {
			sql = """
							CREATE TABLE IF NOT EXISTS empleado (
							  id INT PRIMARY KEY AUTOINCREMENT,
							  nombre VARCHAR(255) NOT NULL,
							  salario DECIMAL(10,2) DEFAULT 0.0,
							  departamento INT
							)
						""";
		}
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
		}
	}

}
