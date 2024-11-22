package es.evg.daw.prog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import es.evg.daw.bbdd.Conexion;
import es.evg.daw.bbdd.SqlBizum;
import es.evg.daw.bizum.Bizum;
import es.evg.daw.bizum.Front;

/**
 * Clase en la que se hace todos los procesos.
 */
public class Programacion implements ActionListener{
	public Bizum objBizum = null;
	private SqlBizum objSqlBizum = null;
	private Conexion objConexion = null;
	@SuppressWarnings("unused")
	private Front objVisual = null;
	Front objFront = null;
	
	/**
	 * Constructor que ejecuta el programa completo
	 */
	public Programacion() {
		super();
		objConexion = new Conexion();
		objSqlBizum = new SqlBizum(objConexion);
		objBizum = new Bizum();
		objFront = new Front();
		objFront.jbtIniciar.addActionListener(this);	
	}
	

	/**
	 * Método para hacer login en la aplicacion.
	 * Debes introducir tu número de telefono y contraseña. El numero de telefono debe de existir en la base de datos para poder hacer el login.
	 * La función de este metodo es saber quien es la persona que enviara el bizum.
	 */
	public boolean Login() {
		
		String telefono = objFront.jtfUsuario.getText();
		String contrasenia = objFront.jtfContrasenia.getText();
		
		if(objSqlBizum.verificarLogin(telefono,contrasenia)) {
			objBizum.setIdLogin(objSqlBizum.obtenerIdentificador(telefono));
			objBizum.setContraseña(contrasenia);
			return true;
		}else
			return false;
	}
	

	/**
	 * Métodos de los escuchadores de los botones del menu.
	 */
	private void escuchadoresMenu() {
		objFront.jbtConsultar.addActionListener(this);
		objFront.jbtEnviarBizum.addActionListener(this);
		objFront.jbtTransaciones.addActionListener(this);
	}
	
	
	/**
	 * Método generado al implementar ActionListener.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == objFront.jbtIniciar) {
			if(Login()) {
				objFront.disenioMenu();
				objFront.aniadirMenu();
				escuchadoresMenu();
				objFront.jfrPrincipal.dispose();
				objFront.jfrMenu.setVisible(true);
			}
			else
				JOptionPane.showMessageDialog(null, "No ha sido posible iniciar sesión. Verifica tu usuario y contraseña.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
			
		}
		
		if(e.getSource() == objFront.jbtConsultar) {
			objSqlBizum.consultarSaldo(objBizum.getIdLogin(),objBizum);
			String mensaje = "El usuario "+objBizum.getNombre()+" con numero de telefono "+objBizum.getTelefono()+" su saldo es de "+objBizum.getSaldo()+" €";
			JOptionPane.showMessageDialog(null, mensaje, "Consulta de Saldo", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(e.getSource() == objFront.jbtEnviarBizum) {
			objFront.disenioEnviarBizum();
			objFront.aniadirBizum();
			objFront.jbtEnviar.addActionListener(this);
			objFront.jbtVolverBizum.addActionListener(this);
			objFront.jfrMenu.dispose();
			objFront.jfrBizum.setVisible(true);
		}
		
		if(e.getSource() == objFront.jbtEnviar) {
			int id = objBizum.getIdLogin();
			String numero = objFront.jtfNumAEnviar.getText();
			double cantidad = Double.parseDouble(objFront.jtfCantidad.getText());
			String concepto = objFront.jtaConcepto.getText();

			
			if(!objSqlBizum.buscarTelefono(numero))
				JOptionPane.showMessageDialog(null, "No es posible de enviar no existe el numero de telefono", "Error de envio", JOptionPane.ERROR_MESSAGE);
		
			if(!objSqlBizum.validarSaldo(objBizum.getContraseña(),cantidad))
				JOptionPane.showMessageDialog(null, "No es posible de enviar saldo insuficiente", "Error de envio", JOptionPane.ERROR_MESSAGE);
			else {
				if(objSqlBizum.consultaBizum(id,numero,cantidad,concepto)) {
					JOptionPane.showMessageDialog(null, "Enviando Dinero...", "Envio", JOptionPane.INFORMATION_MESSAGE);
					objFront.jfrBizum.dispose();
					objFront.jfrMenu.setVisible(true);
				}
			}
		}
		
		if(e.getSource() == objFront.jbtTransaciones) {
			int id = objBizum.getIdLogin();
			objFront.disenioTransaciones();
			objFront.aniadirTransaciones();
			objFront.jbtVolver.addActionListener(this);
			objFront.jfrTransaciones.setVisible(true);
			String transacciones = objSqlBizum.registroEnviadosRecibidos(id);
			objFront.jtaTrans.append(transacciones);
		}
		
		if(e.getSource() == objFront.jbtVolver) {
			objFront.jfrTransaciones.dispose();
			objFront.jfrMenu.setVisible(true);
		}
		
		if(e.getSource() == objFront.jbtVolverBizum) {
			objFront.jfrBizum.dispose();
			objFront.jfrMenu.setVisible(true);
		}
			
	}

}
