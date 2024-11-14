package com.example.TpFinalJava.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "Envios")
public class Envios {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id_Envio")
	private int Id_Envio;
	
	@Column(name = "fecha_envio_inicio")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private Date fecha_envio_inicio;
	
	@Column(name = "fecha_envio_enviado")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private Date fecha_envio_enviado;
	
	@Column(name = "fecha_envio_recibido")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private Date fecha_envio_recibido;


	@ManyToOne(optional = false ,fetch = FetchType.EAGER)
	@JoinColumn(name="Nro_Pedido")
	private Pedidos Nro_Pedido;
	
	@Column(name = "Lugar_Envio")
	private String Lugar_Envio;
	
	@Column(name = "Enviado")
	private boolean Enviado;
	
	@Column(name = "Recibido")
	private boolean Recibido;
	
	@ManyToOne(optional = false ,fetch = FetchType.EAGER)
	@JoinColumn(name="Id_usuario")
	private Usuarios Id_usuario;
	
	public Envios() {		
	}
	
	public Envios(int id_Envio, Date fechaEnvio, Pedidos nroPedido, String lugarEnvio, boolean enviado, boolean recibido, Usuarios Usuarios) {
		this.Id_Envio = id_Envio;
		this.fecha_envio_inicio = fechaEnvio;
		this.Nro_Pedido = nroPedido;
		this.Lugar_Envio = lugarEnvio;
		this.Enviado = enviado;
		this.Recibido = recibido;
		this.Id_usuario = Usuarios;
	}
	
	public Date getFecha_envio_enviado() {
		return fecha_envio_enviado;
	}

	public void setFecha_envio_enviado(Date fecha_envio_enviado) {
		this.fecha_envio_enviado = fecha_envio_enviado;
	}

	public Date getFecha_envio_recibido() {
		return fecha_envio_recibido;
	}

	public void setFecha_envio_recibido(Date fecha_envio_recibido) {
		this.fecha_envio_recibido = fecha_envio_recibido;
	}

	public int getId_Envio() {
		return Id_Envio;
	}

	public void setId_Envio(int id_Envio) {
		
		this.Id_Envio = id_Envio;
	}

	public Date getFechaEnvio() {
		return fecha_envio_inicio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fecha_envio_inicio = fechaEnvio;
	}

	public Pedidos getNroPedido() {
		return Nro_Pedido;
	}

	public void setId_usuario(Usuarios Usuarios) {
		this.Id_usuario = Usuarios;
	}
	public Usuarios getId_usuario() {
		return Id_usuario;
	}

	public void setNroPedido(Pedidos nroPedido) {
		this.Nro_Pedido = nroPedido;
	}

	public String getLugarEnvio() {
		return Lugar_Envio;
	}

	public void setLugarEnvio(String lugarEnvio) {
		this.Lugar_Envio = lugarEnvio;
	}

	public boolean isEnviado() {
		return Enviado;
	}

	public void setEnviado(boolean enviado) {
		this.Enviado = enviado;
	}

	public boolean isRecibido() {
		return Recibido;
	}

	public void setRecibido(boolean recibido) {
		this.Recibido = recibido;
	}
}
