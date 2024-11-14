package com.example.TpFinalJava.dto;


import org.springframework.stereotype.Component;

import com.example.TpFinalJava.model.Productos;

@Component
public class ProductosDtoMapper{
	
	public ProductosDTO toProductosDto(Productos Productos, int cantidad) {
		
        return new ProductosDTO(
				  Productos.getId()
				 ,Productos.getNombre()
				 ,Productos.getPrecio()
				 ,cantidad
				);
    }

    public Productos toProductos(ProductosDTO ProductosDTO) {
        return new Productos();
    }

}
