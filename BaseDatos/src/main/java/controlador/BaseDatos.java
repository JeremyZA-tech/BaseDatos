package controlador;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDatos {

	private static Connection conn = null;
	
	public static String dbType = null;
	
	private BaseDatos() {
        try {
            Properties prop = new Properties();
            prop.load(new FileReader("properties.database.prop"));

            dbType = prop.getProperty("dbType"); // Modificar la propiedad en el archivo properties

            if ("SQLite".equalsIgnoreCase(dbType)) {
            	
                String driver = prop.getProperty("sqlite.driver");
                String dsn = prop.getProperty("sqlite.dsn");
                

                Class.forName(driver);
                conn = DriverManager.getConnection(dsn);
                
            } else if ("MariaDB".equalsIgnoreCase(dbType)) {
            	
                String driver = prop.getProperty("mariadb.driver");
                String dsn = prop.getProperty("mariadb.dsn");
                String user = prop.getProperty("mariadb.user", "");
                String pass = prop.getProperty("mariadb.pass", "");

                Class.forName(driver);
                conn = DriverManager.getConnection(dsn, user, pass);   
            }
            
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
	
	public static Connection getConnection() {
		if (conn == null) {
			new BaseDatos();
		}
		return conn;
	}

	public static void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}