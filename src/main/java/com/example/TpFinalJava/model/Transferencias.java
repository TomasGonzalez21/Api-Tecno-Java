package com.example.TpFinalJava.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transferencias")
public class Transferencias {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="Id")
	private int Id;
	

	@Column(name = "Monto")	
	private float Monto;
	
	@ManyToOne(optional = false ,fetch = FetchType.EAGER)
	@JoinColumn(name="UsuarioEmisor")
	private Usuarios UsuarioEmisor;
	
	@ManyToOne(optional = false ,fetch = FetchType.EAGER)
	@JoinColumn(name="UsuarioReceptor")
	private Usuarios UsuarioReceptor;
	
	@Column(name ="FechaTransferencia")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private Date FechaTransferencia;

	public Transferencias(int id,float monto, Usuarios usuarioEmisor, Usuarios usuarioReceptor, Date FechaTransferencia) {
		this.Id = id;
		this.Monto = monto;
		this.UsuarioEmisor = usuarioEmisor;
		this.UsuarioReceptor = usuarioReceptor;
		this.FechaTransferencia = FechaTransferencia;
	}
	public Transferencias() {
	}


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public float getMonto() {
		return Monto;
	}


	public void setMonto(float monto) {
		Monto = monto;
	}


	public Usuarios getUsuarioEmisor() {
		return UsuarioEmisor;
	}


	public void setUsuarioEmisor(Usuarios usuarioEmisor) {
		UsuarioEmisor = usuarioEmisor;
	}


	public Usuarios getUsuarioReceptor() {
		return UsuarioReceptor;
	}


	public void setUsuarioReceptor(Usuarios usuarioReceptor) {
		UsuarioReceptor = usuarioReceptor;
	}
	
	public Date getFechaTransferencia() {
		return FechaTransferencia;
	}


	public void setFechaTransferencia(Date fechaTransferencia) {
		FechaTransferencia = fechaTransferencia;
	}
	
}
