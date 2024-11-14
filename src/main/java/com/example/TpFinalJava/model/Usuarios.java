package com.example.TpFinalJava.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuarios {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name ="Id")
	private int Id;
	
	@Column(name = "Username")
	private String Username;

	@Column(name = "Password")
	private String Password;

	@Column(name = "Activo")
	private boolean Activo;	

	@Column(name = "Saldo")	
	private float Saldo;
	
	@ManyToOne(optional = false ,fetch = FetchType.EAGER)
	@JoinColumn(name="Rol")
	private Roles Rol;
	
	@Column(name = "email")
	private String Email;


	
	@Column(name = "Direccion")
	private String Direccion;

	@Column(name = "codigopostal")
	private String codigopostal;

	public Usuarios() {	
	}
	
	public Usuarios(String username,String password,boolean activo,float saldo, Roles rol,String email) {
			this.Username = username;
			this.Password = password;
			this.Activo = activo;
			this.Saldo = saldo;
			this.Rol = rol;
			this.Email = email;
	}

	public float getSaldo() {
		return Saldo;
	}

	public void setSaldo(float saldo) {
		this.Saldo = saldo;
	}

	public Roles getRol() {
		return Rol;
	}

	public void setRol(Roles rol) {
		this.Rol = rol;
	}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		this.Username = username;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}

	public boolean isActivo() {
		return Activo;
	}

	public void setActivo(boolean activo) {
		this.Activo = activo;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		this.Direccion = direccion;
	}

	public String getCodigopostal() {
		return codigopostal;
	}

	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}


}
