package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Departamento;
import modelo.Empleado;

public class Empresa {
  private Connection conn = null;
	
	/**
	 * Constructor
	 */

	public Empresa() {
		conn = BaseDatos.getConnection();
		createTables();
	}

	public boolean addEmpleado(Empleado em) {
		
		String sql = """
				INSERT INTO empleado (id, nombre, salario, departamento)
				VALUES (?, ?, ?, ?)
				""";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, em.getId().toString());
			ps.setString(2, em.getNombre());
			ps.setString(3, em.getSalario().toString());
			ps.setString(4, em.getDepartamento().toString());
			return ps.executeUpdate() > 0;
		}catch(SQLException e) {
			}
		
		return false;
	}
	
	/**
	 * Añade un nuevo departamento a la tabla Departamento
	 * 
	 * @param c
	 * @return true si ha sido añadido, false en caso contrario
	 */
	
	public boolean addDepartamento(Departamento d) {
		
		String sql = """
				INSERT INTO departamento (id, nombre,jefe) 
				VALUES (?, ?, ?)
				""";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, d.getId().toString());
			ps.setString(2, d.getNombre());
			ps.setString(3, d.getJefe().toString());
			return ps.executeUpdate() > 0;
		}catch(SQLException e) {
			}
		return false;
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
