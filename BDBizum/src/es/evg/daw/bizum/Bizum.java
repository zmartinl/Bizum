package es.evg.daw.bizum;

public class Bizum {
	private int id;
	private int idReceptor;
	private String email;
	
	//LOGIN
	private String contraseña;
	private String telefono;
	private int idLogin;
	
	//CONSULTAR SALDO
	private double saldo;
	private String nombre;
	// telefono tmb se usa aqui.
	
	//Enviar Bizum
	private double dineroEnviar;
	private String concepto;
	
	
	public Bizum() {
		id = 0;
		nombre = null;
		telefono = null;
		email = null;
		saldo = 0;
		contraseña = null;
		idLogin = 0;
		dineroEnviar = 0;
		idReceptor = 0;
		concepto = null;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String string) {
		this.telefono = string;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public int getIdLogin() {
		return idLogin;
	}


	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}


	public double getDineroEnviar() {
		return dineroEnviar;
	}


	public void setDineroEnviar(double dineroEnviar) {
		this.dineroEnviar = dineroEnviar;
	}


	public int getIdReceptor() {
		return idReceptor;
	}


	public void setIdReceptor(int idReceptor) {
		this.idReceptor = idReceptor;
	}


	public String getConcepto() {
		return concepto;
	}


	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
}
