package es.evg.daw.bbdd;

import java.sql.SQLException;

import es.evg.daw.bizum.Bizum;

/**
 * Esta clase es para todo lo relacionado con consultas.
 */
public class SqlBizum{
	Conexion objConexion = null;
	Bizum objBizum = new Bizum();
	String consulta;


	public SqlBizum(Conexion objConexion) {
		this.objConexion = objConexion;
	}
	
	/**
	 * Este método consulta la tabla de usuarios.
	 */
	public void consultarTabla() {
		try {
			consulta = "SELECT * FROM usuarios";
			objConexion.resultado = objConexion.sentencia.executeQuery(consulta);
			//System.out.println(consulta);
		}catch(SQLException e) { e.printStackTrace(); }
	}
	
	/**
	 * Método que hace una consulta para contar cuantas existencias hay del numero de telefono recibido por parametro.
	 * @param numeroTelefono Recibe por parametro el numero de telefono que se quiere buscar en la base de datos.
	 * @return devuelve si existe o no.
	 */
	public boolean buscarTelefono(String numeroTelefono) {
		String busquedaCel;
		try {
			busquedaCel = "SELECT COUNT(id) FROM usuarios WHERE telefono = '"+numeroTelefono+"'";
			//System.out.println(busquedaCel);
			objConexion.resultado = objConexion.sentencia.executeQuery(busquedaCel);
			return objConexion.resultado.next() && objConexion.resultado.getInt(1) > 0;
		} catch (SQLException e) { e.printStackTrace(); } 
		return false;
	}
	
	/**
	 * Método para obtener el identidicador de usuario a través de su numero de télefono.
	 * @param telefono Recibe el numero de telefono del usuario que queremos saber su identificador.
	 * @return devuelve el número del identificador del usuario.
	 */
	public int obtenerIdentificador(String telefono) {
		try {
			consulta = "SELECT id FROM usuarios WHERE telefono = '"+telefono+"'";
			objConexion.resultado = objConexion.sentencia.executeQuery(consulta);
			if(objConexion.resultado.next())
				return objConexion.resultado.getInt("id");
		} catch (SQLException e) { e.printStackTrace(); }
		return -1;
	}
	
	
	/**
	 * Método para verificar un login comparando el usuario que exista en la BD y sea igual que la contraseña.
	 * @param telefono es el usuario del login
	 * @param contraseña contraseña del login
	 * @return devuelve si existe ese usuario
	 */
	public boolean verificarLogin(String telefono, String contraseña) {
		if(buscarTelefono(telefono) && telefono.equals(contraseña))
			return true;
		return false;
	}
	
	/**
	 * Método para consultar el saldo del usuario logeado.
	 * @param id Recibe el identidicador del usuario logeado
	 * @param bizum Recibe el POJO para que al hacer setteo del nombre,telefono,saldo pueda cogerlo desde la clase programación.
	 */
	public void consultarSaldo(int id, Bizum bizum) {
		try {
			consulta = "SELECT nombre,telefono,saldo FROM usuarios WHERE id = '"+id+"'";
			objConexion.resultado = objConexion.sentencia.executeQuery(consulta);
			if(objConexion.resultado.next()) {
				bizum.setNombre(objConexion.resultado.getNString("nombre"));
				bizum.setTelefono(objConexion.resultado.getString("telefono"));
				bizum.setSaldo(objConexion.resultado.getDouble("saldo"));
			}
		}catch(SQLException e) { e.printStackTrace(); }
	}
	
	/**
	 * Método para enviar un bizum a una persona.
	 * @param idEmisor identificador el que envia el bizum (Login).
	 * @param telReceptor Número de telefono al que va a ser enviado el dinero.
	 * @param dineroEnviar Cantidad de dinero que va a ser enviada al numero de telefono.
	 * @param concepto Recibe el concepto del envio.
	 */
	public boolean consultaBizum(int idEmisor, String telReceptor, double dineroEnviar, String concepto) {
		double saldoEmisor, nuevoSaldoEmisor, saldoReceptor, nuevoSaldoReceptor;
		
		try {
			consulta = "SELECT saldo FROM usuarios WHERE id = '"+idEmisor+"'";
			objConexion.resultado = objConexion.sentencia.executeQuery(consulta);
			
			if(objConexion.resultado.next()) {
				objBizum.setSaldo(objConexion.resultado.getDouble("saldo"));
			}
			
			saldoEmisor = objBizum.getSaldo();
			nuevoSaldoEmisor = saldoEmisor - dineroEnviar;
			consulta = "UPDATE usuarios SET saldo = "+nuevoSaldoEmisor+" WHERE id = '"+idEmisor+"'";
			objConexion.sentencia.executeUpdate(consulta);
			insetarRegistro(idEmisor,telReceptor,concepto,dineroEnviar);
			
			
			saldoReceptor = obtenerSaldo(telReceptor);
			nuevoSaldoReceptor = saldoReceptor + dineroEnviar;
			consulta = "UPDATE usuarios SET saldo = "+nuevoSaldoReceptor+" WHERE telefono = '"+telReceptor+"'";
			objConexion.sentencia.executeUpdate(consulta);
			return true;
			
		} catch (SQLException e) { e.printStackTrace(); }
		return false;
	}

	/**
	 * 	Método para hacer la inserccion en la tabla de registros de bizums.
	 * 
	 * @param idEmisor Recibe el identificador del usuario que envia el bizum.
	 * @param telreceptor Numero de telefono del usuario que recibe el bizum.
	 * @param concepto Concepto del bizum.
	 * @param importe Importe del bizum.
	 */
	private void insetarRegistro(int idEmisor,String telreceptor,String concepto, double importe) {
		int receptor = obtenerId(telreceptor);
		
		consulta = "INSERT INTO registrobizum (idEmisor,idReceptor,concepto,importe) VALUES ("+ idEmisor + ", " + receptor + ", '" + concepto + "' , " + importe + ")";
		
		try {
			objConexion.sentencia.executeUpdate(consulta);
		} catch (SQLException e) { e.printStackTrace(); }
		
	}

	/**
	 * Método para obtener el identificador de un usuario.
	 * @param telefono Número de telefono.
	 * @return Devuelve el identificador del numero de telefono que le pases.
	 */
	private int obtenerId(String telefono) {
		consulta = "SELECT id FROM usuarios WHERE telefono = '"+telefono+"'";
		try {
			objConexion.resultado = objConexion.sentencia.executeQuery(consulta);
			if(objConexion.resultado.next())
				return objConexion.resultado.getInt("id");
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return 0;
	}

	/**
	 * Método para obtener el saldo de un usuario por su numero de telefono.
	 * @param telefono Recibe el telefono por le cual quiere hacer la consulta
	 * @return Devulve el saldo.
	 */
	private double obtenerSaldo(String telefono) {
		consulta = "SELECT saldo FROM usuarios WHERE telefono = '"+telefono+"'";
		
		try {
			objConexion.resultado = objConexion.sentencia.executeQuery(consulta);
			if(objConexion.resultado.next()) 
				return objConexion.resultado.getDouble("saldo");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Método para validar que el dinero que quieres enviar no supera la cantidad que tienes en el banco.
	 * @param telefono Recibe el numero de telefono para consultar el saldo de ese numero de telefono.
	 * @param dineroEnviar Recibe el dinero que quiere ser enviado para compararlo con el saldo que tiene
	 * @return Devuelve si puede enviar esa cantidad o no.
	 */
	public boolean validarSaldo(String telefono, double dineroEnviar) {
		consulta = "SELECT saldo FROM usuarios WHERE telefono = '"+telefono+"'";
		double saldo = 0;
		
		try {
			objConexion.resultado = objConexion.sentencia.executeQuery(consulta);
			if(objConexion.resultado.next())
				saldo = objConexion.resultado.getDouble("saldo");
			
			if(dineroEnviar <= saldo)
				return true;
			else
				return false;
		} catch (SQLException e) { e.printStackTrace(); }
		return false;
	}
	
	/**
	 * @param id Recibe el id del usuario logeado
	 * @return Devuelve el metodo toString del StringBuilder modificado para visualizar en el textArea las transaciones.
	 */
	public String registroEnviadosRecibidos(int id) {
	    String consultaEnvios = "SELECT importe FROM registrobizum WHERE idEmisor = '"+ id + "'";
	    String consultaRecibido = "SELECT importe FROM registrobizum WHERE idReceptor = '" + id + "'";
	    StringBuilder transacciones = new StringBuilder(500);
	    
	    transacciones.append("\n                                 TRANSACCIONES\n\n");
	    
	    try {
	        objConexion.resultado = objConexion.sentencia.executeQuery(consultaEnvios);
	        while (objConexion.resultado.next()) {
	            double importe = objConexion.resultado.getDouble("importe");
	            transacciones.append("\t          [ - ] "+importe+" €\n\n");
	        }
	        
	        objConexion.resultado = objConexion.sentencia.executeQuery(consultaRecibido);
	        while (objConexion.resultado.next()) {
	            double importe = objConexion.resultado.getDouble("importe");
	            transacciones.append("\t          [ + ] "+importe+" €\n\n");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return transacciones.toString();
	}
	
}
