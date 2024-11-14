package com.example.TpFinalJava.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pedidos")
public class Pedidos {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id_Pedido")
	private int Id_Pedido;
	

	@Column(name = "Detalle")
	private String Detalle;

	@Column(name = "Usuarioid")
	private int UsuarioId;	

	@Column(name = "MontoTotal")
	private float MontoTotal;

	@Column(name = "FechaPedido")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private Date FechaPedido;
	
	public Pedidos() {
	}


	public Pedidos(String detalle, int usuarioId, float montoTotal, Date fechaPedido) {
		Detalle = detalle;
		UsuarioId = usuarioId;
		MontoTotal = montoTotal;
		FechaPedido = fechaPedido;
	}


	public float getMontoTotal() {
		return MontoTotal;
	}


	public void setMontoTotal(float montoTotal) {
		MontoTotal = montoTotal;
	}


	public Date getFechaPedido() {
		return FechaPedido;
	}


	public void setFechaPedido(Date fechaPedido) {
		FechaPedido = fechaPedido;
	}


	public int getId_Pedido() {
		return Id_Pedido;
	}

	public void setId_Pedido(int id_Pedido) {
		Id_Pedido = id_Pedido;
	}

	public String getDetalle() {
		return Detalle;
	}

	public void setDetalle(String detalle) {
		Detalle = detalle;
	}

	public int getUsuarioId() {
		return UsuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		UsuarioId = usuarioId;
	}
}
