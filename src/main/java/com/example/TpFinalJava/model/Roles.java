package com.example.TpFinalJava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "RolesUsuario")
public class Roles {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name ="Id")
	private int Id;

	@Column(name = "Rol")	
	private String Rol;
	

	public Roles() {	
	}


	public Roles(int id, String rol) {
		Id = id;
		Rol = rol;
	}

	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public String getRol() {
		return Rol;
	}


	public void setRol(String rol) {
		Rol = rol;
	}

	

}
