package es.evg.daw.bizum;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import es.evg.daw.interfaz.ConfigInterfaz;

/**
 * Clase Front en la cual se hace todo lo visual de la APP.
 */
public class Front implements ConfigInterfaz{
	public JFrame jfrPrincipal = null;
	public JFrame jfrMenu = null;
	public JFrame jfrBizum = null;
	public JFrame jfrTransaciones = null;
	ImageIcon img1 = new ImageIcon(new ImageIcon("src/es/evg/daw/img/logo.jpg").getImage().getScaledInstance(300, 95, Image.SCALE_DEFAULT));

	
	//LOGIN.
	public JPanel jpnLogin = null;
	public JPanel jpnBlancoLogin = null;
	public JLabel jlbImgLogin = null;
	public JLabel jlbUsuario = null;
	public JLabel jlbContrasenia = null;
	public JTextField jtfUsuario = null;
	public JTextField jtfContrasenia = null;
	public JButton jbtIniciar = null;
	
	//MENU.
	public JPanel jpnMenu = null;
	public JLabel jlbTitMenu = null;
	public JButton jbtConsultar = null;
	public JButton jbtEnviarBizum = null;
	public JButton jbtTransaciones = null;
	
	//ENVIAR BIZUM
	public JPanel jpnBizum = null;
	public JLabel jlbTitBizum = null;
	public JLabel jlbNumAEnviar = null;
	public JLabel jlbCantidad = null;
	public JLabel jlbConcepto = null;
	public JTextField jtfNumAEnviar = null;
	public JTextField jtfCantidad = null;
	public JTextArea jtaConcepto = null;
	public JButton jbtEnviar = null;
	public JButton jbtVolverBizum = null;
	
	//TRANSACIONES
	public JPanel jpnTransaciones = null;
	public JLabel jlbTitTrans = null;
	public JTextArea jtaTrans = null;
	public JButton jbtVolver = null;
	public JScrollPane scrollTrans = null;


	
	/**
	 * Constructor que inicializa con el apartado del Login de la APP.
	 */
	public Front() {
		disenioLogin();
		aniadirLogin();
		visualizar();
	}


	/**
	 * Método en el que se diseña el login de la APP.
	 */
	public void disenioLogin() {
		jfrPrincipal = new JFrame("SANTANDER");
		jfrPrincipal.setSize(1000,1000);
		jfrPrincipal.setLocationRelativeTo(null);
		jfrPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//LOGIN 
		jpnLogin = new JPanel();
		jpnLogin.setSize(jfrPrincipal.getSize());
		jpnLogin.setLayout(null);
		jpnLogin.setBackground(Color.red);
		
		jpnBlancoLogin = new JPanel();
		jpnBlancoLogin.setSize(600,400);
		jpnBlancoLogin.setLayout(null);
		jpnBlancoLogin.setBackground(Color.WHITE);
		jpnBlancoLogin.setBounds(200, 300, 600, 400);
		
		
		jlbImgLogin = new JLabel();
		jlbImgLogin.setIcon(img1);
		jlbImgLogin.setBounds(350, 100, 300, 95);
		
		
		jlbUsuario = new JLabel("USUARIO");
		jlbUsuario.setFont(LetraTitutlos);
		jlbUsuario.setBounds(100, 90, 130, 30);
		jlbUsuario.setForeground(Roja);
		
		jtfUsuario = new JTextField();
		jtfUsuario.setBounds(300, 90, 190, 40);
		jtfUsuario.setBackground(ColorBotones);
		jtfUsuario.setHorizontalAlignment(JTextField.CENTER);
		jtfUsuario.setFont(LetraBoton);
		jtfUsuario.requestFocus();
		
		jlbContrasenia = new JLabel("CONTRASEÑA");
		jlbContrasenia.setFont(LetraTitutlos);
		jlbContrasenia.setBounds(100, 250, 180, 30);
		jlbContrasenia.setForeground(Roja);
		
		jtfContrasenia = new JTextField();
		jtfContrasenia.setBounds(300, 250, 190, 40);
		jtfContrasenia.setBackground(ColorBotones);
		jtfContrasenia.setHorizontalAlignment(JTextField.CENTER);
		jtfContrasenia.setFont(LetraBoton);
		jtfContrasenia.requestFocus();
		
		jbtIniciar = new JButton("ENTRAR");
		jbtIniciar.setBackground(ColorBotones);
		jbtIniciar.setBounds(420, 800, 160, 60);
		
	}
	
	/**
	 * Método de añanir los objetos a respectivos objetos del login.
	 */
	public void aniadirLogin() {
		//Login
		jfrPrincipal.add(jpnLogin);
		jpnLogin.add(jpnBlancoLogin);
		jpnBlancoLogin.add(jlbUsuario);
		jpnBlancoLogin.add(jtfUsuario);
		jpnBlancoLogin.add(jlbContrasenia);
		jpnBlancoLogin.add(jtfContrasenia);
		jpnLogin.add(jbtIniciar);
		jpnLogin.add(jlbImgLogin);
	}
	
	/**
	 * Método para diseñar el menu de la APP.
	 */
	public void disenioMenu(){
		
		jfrMenu = new JFrame("MENU");
		jfrMenu.setSize(1000,300);
		jfrMenu.setLocationRelativeTo(null);
		jfrMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jpnMenu = new JPanel();
		jpnMenu.setSize(jfrMenu.getSize());
		jpnMenu.setLayout(null);
		jpnMenu.setBackground(Color.red);
		
		
		jlbTitMenu = new JLabel();
		jlbTitMenu.setIcon(img1);
		jlbTitMenu.setBounds(350, 20, 300, 95);
		
		
		jbtConsultar = new JButton("CONSULTAR SALDO");
		jbtConsultar.setBackground(ColorBotones);
		jbtConsultar.setFocusPainted(false);
		jbtConsultar.setBounds(50, 130, 200, 70);
		
		jbtEnviarBizum = new JButton("ENVIAR BIZUM");
		jbtEnviarBizum.setBackground(ColorBotones);
		jbtEnviarBizum.setFocusPainted(false);
		jbtEnviarBizum.setBounds(400, 130, 200, 70);
		
		jbtTransaciones = new JButton("VER TRANSACIONES");
		jbtTransaciones.setBackground(ColorBotones);
		jbtTransaciones.setFocusPainted(false);
		jbtTransaciones.setBounds(730, 130, 200, 70);
		
	}
	
	/**
	 * Métodos para añadir los objetos a respetivos objetos del menu.
	 */
	public void aniadirMenu() {
		jfrMenu.add(jpnMenu);
		jpnMenu.add(jlbTitMenu);
		
		jpnMenu.add(jbtConsultar);
		jpnMenu.add(jbtEnviarBizum);
		jpnMenu.add(jbtTransaciones);
	}

	/**
	 * Método para diseñar el apartado de enviar bizums de la APP.
	 */
	public void disenioEnviarBizum() {
		jfrBizum = new JFrame("ENVIO DE BIZUMS");
		jfrBizum.setSize(1000,1000);
		jfrBizum.setLocationRelativeTo(null);
		jfrBizum.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jpnBizum = new JPanel();
		jpnBizum.setSize(jfrBizum.getSize());
		jpnBizum.setLayout(null);
		jpnBizum.setBackground(Color.red);
			
		jlbTitBizum = new JLabel();
		jlbTitBizum.setIcon(img1);
		jlbTitBizum.setBounds(350, 60, 300, 95);
		
		jlbNumAEnviar = new JLabel("Introduce Número:");
		jlbNumAEnviar.setFont(LetraTitutlos);
		jlbNumAEnviar.setForeground(ColorBotones);
		jlbNumAEnviar.setBounds(150, 220, 250, 30);
		
		jtfNumAEnviar = new JTextField();
		jtfNumAEnviar.setBounds(600, 220, 190, 40);
		jtfNumAEnviar.setBackground(ColorBotones);
		jtfNumAEnviar.setHorizontalAlignment(JTextField.CENTER);
		jtfNumAEnviar.setFont(LetraBoton);
		jtfNumAEnviar.requestFocus();
		
		jlbCantidad = new JLabel("Introduce Cantidad:");
		jlbCantidad.setFont(LetraTitutlos);
		jlbCantidad.setForeground(ColorBotones);
		jlbCantidad.setBounds(150, 390, 300, 30);
		
		jtfCantidad = new JTextField();
		jtfCantidad.setBounds(600, 390, 190, 40);
		jtfCantidad.setBackground(ColorBotones);
		jtfCantidad.setHorizontalAlignment(JTextField.CENTER);
		jtfCantidad.setFont(LetraBoton);
		
		jlbConcepto = new JLabel("Concepto:");
		jlbConcepto.setFont(LetraTitutlos);
		jlbConcepto.setForeground(ColorBotones);
		jlbConcepto.setBounds(150, 570, 300, 30);
		
		jtaConcepto = new JTextArea();
		jtaConcepto.setBounds(320, 570, 470, 200);
		
		jbtEnviar = new JButton("ENVIAR");
		jbtEnviar.setBackground(ColorBotones);
		jbtEnviar.setFocusPainted(false);
		jbtEnviar.setBounds(550, 850, 200, 70);
		
		jbtVolverBizum = new JButton("VOLVER");
		jbtVolverBizum.setBackground(ColorBotones);
		jbtVolverBizum.setFocusPainted(false);
		jbtVolverBizum.setBounds(230, 850, 200, 70);
		
	}
	
	/**
	 * Métodos para añadir los objetos a respetivos objetos del apartado enviar bizums.
	 */
	public void aniadirBizum() {
		jfrBizum.add(jpnBizum);
		jpnBizum.add(jlbTitBizum);
		jpnBizum.add(jlbNumAEnviar);
		jpnBizum.add(jtfNumAEnviar);
		jpnBizum.add(jlbCantidad);
		jpnBizum.add(jtfCantidad);
		jpnBizum.add(jlbConcepto);
		jpnBizum.add(jtaConcepto);
		jpnBizum.add(jbtEnviar);
		jpnBizum.add(jbtVolverBizum);
	}

	/**
	 * Método para diseñar el apartado de transaciones de la APP.
	 */
	public void disenioTransaciones() {
		jfrTransaciones = new JFrame("TRANSACIONES");
		jfrTransaciones.setSize(1000,600);
		jfrTransaciones.setLocationRelativeTo(null);
		jfrTransaciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jpnTransaciones = new JPanel();
		jpnTransaciones.setSize(jfrTransaciones.getSize());
		jpnTransaciones.setLayout(null);
		jpnTransaciones.setBackground(Color.red);
		
		jlbTitTrans = new JLabel();
		jlbTitTrans.setIcon(img1);
		jlbTitTrans.setBounds(350, 0, 300, 95);
		
		jtaTrans = new JTextArea();
		jtaTrans.setEditable(false);
		
		scrollTrans = new JScrollPane(jtaTrans);
        scrollTrans.setBounds(350, 100, 300, 400);
		
		
		jbtVolver = new JButton("VOLVER");
		jbtVolver.setBackground(ColorBotones);
		jbtVolver.setFocusPainted(false);
		jbtVolver.setBounds(60, 430, 200, 70);
		
	}
	
	/**
	 * Métodos para añadir los objetos a respetivos objetos del apartado transaciones.
	 */
	public void aniadirTransaciones() {
		jfrTransaciones.add(jpnTransaciones);
		jpnTransaciones.add(jlbTitTrans);
		jpnTransaciones.add(scrollTrans);
		jpnTransaciones.add(jbtVolver);
	}
	
	/**
	 * Método para visualizar el Login.
	 */
	public void visualizar() {
		jfrPrincipal.setVisible(true);
	}	
	
}
