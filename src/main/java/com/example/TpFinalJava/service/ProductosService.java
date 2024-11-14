package com.example.TpFinalJava.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TpFinalJava.data.ProductosRepository;
import com.example.TpFinalJava.dto.ProductosDTO;
import com.example.TpFinalJava.model.Productos;
import com.example.TpFinalJava.dto.ProductosDtoMapper;
@Service
public class ProductosService {
	
	private final ProductosRepository productosRepository;
	public ProductosService(ProductosRepository productosRepository, ProductosDtoMapper ProductosDtoMapper) {
		this.productosRepository = productosRepository;
	}
	

	@Transactional(readOnly = true)
	public List<Productos> getProductos(){
		return productosRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Productos getProductoById(int productoID){
		return productosRepository.findById(productoID).orElse(null);
	}

	public float ObtenerTotal(ProductosDTO[] listaProductos){
		
		float montoFinal = 0;
		
		for (ProductosDTO producto : listaProductos) {
			montoFinal =  montoFinal + (producto.precio() * producto.cantidad());
		}
		return montoFinal;	
	}
	
	@Transactional
	public Productos guardarProducto(Productos producto){
		return productosRepository.saveAndFlush(producto);
	}
	
	public void actualizarProducto(Productos producto){
		producto.setNombre(producto.getNombre());
		producto.setDescripcion(producto.getDescripcion());
		producto.setPrecio(producto.getPrecio());
		producto.setStock(producto.getStock());
		productosRepository.saveAndFlush(producto);
	}
	public void activarProducto(int id, boolean activo){
		Productos producto = productosRepository.findById(id).orElse(null);
		
		producto.setActivo(activo);
		
		productosRepository.save(producto);
	}
	
	@Transactional
	public void ActualizarStock(Productos producto, long stock){
		
		producto.setStock(stock);
		
		productosRepository.save(producto);
	}
	
	public void ActualizarStockPostCompra(ProductosDTO[] ListadoProductos){
		
		for (ProductosDTO productoPedido : ListadoProductos) {
			Productos productoListado =  productosRepository.findById(productoPedido.id())
			  .orElse(null);
			if(productoPedido.cantidad() >= 0 && productoListado != null) {
				long stock = productoListado.getStock();
				
				productoListado.setStock(stock-productoPedido.cantidad());
				
			}
		}		
	}


	@Transactional
	public void eliminarProductos(int productoID) {
		productosRepository.deleteById(productoID);
	}
}
