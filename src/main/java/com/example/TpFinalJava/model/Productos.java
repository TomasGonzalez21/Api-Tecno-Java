package com.example.TpFinalJava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Productos")
public class Productos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	
	@Column(name = "Nombre")
	private String Nombre;

	@Column(name = "Descripcion")
	private String Descripcion;
	
	@Column(name = "Precio")
	private long Precio;
	
	@Column(name = "Stock")
	private long Stock;

	@Column(name = "activo")
	private boolean Activo;
	
	@Lob
	@Column(name = "Imagen")
	private String ImagenURL;
	
	public Productos() {	
	}
	
	public Productos(String nombre,String descripcion,long precio,int stock,boolean activo,String Imagen){
			this.Nombre = nombre;
			this.Descripcion = descripcion;
			this.Precio = precio;
			this.Stock = stock;
			this.Activo = activo;
			this.ImagenURL = Imagen;
	}
	
	public Productos(int id,long stock  ) {
		this.Id = id;
		this.Stock = stock;
}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}

	public long getPrecio() {
		return Precio;
	}

	public void setPrecio(long precio) {
		this.Precio = precio;
	}

	public long getStock() {
		return Stock;
	}

	public void setStock(long stock) {
		this.Stock = stock;
	}

	public boolean isActivo() {
		return Activo;
	}

	public void setActivo(boolean activo) {
		this.Activo = activo;
	}

	public String getImagen() {
		return ImagenURL;
	}

	public void setImagen(String imagen) {
		this.ImagenURL = imagen;
	}
	
	

}
