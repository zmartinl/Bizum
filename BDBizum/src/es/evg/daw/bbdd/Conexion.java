package es.evg.daw.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase Conexion de BD
 */
public class Conexion {
	public Connection conexion = null;
	public java.sql.Statement sentencia = null;
	public ResultSet resultado = null;

	/**
	 * Constructor para que se establezca la conexion con la base de datos
	 */
	public Conexion() {
		conectarBD();
	}
	
	/**
	 * Metodo para conectar con la base de datos
	 */
	public void conectarBD(){
		
		String url = "jdbc:mysql://localhost:3306/bdbizum";
		
		try {
			//Cargar los drivers.
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conexion = DriverManager.getConnection(url, "root", "");
			
			sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		}catch(ClassNotFoundException e){
			System.err.println("Los drivers no funcionan "+ e.getMessage());
		} catch (SQLException e) {
			System.err.println("Error con la base de datos"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para cerrar la conexion con la base de datos
	 */
	public void cerrarConexionBD() {
		try {
			if(resultado != null) resultado.close();
			if(sentencia != null) sentencia.close();
			if(conexion != null) conexion.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
