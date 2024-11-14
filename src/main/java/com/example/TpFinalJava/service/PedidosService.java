package com.example.TpFinalJava.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TpFinalJava.data.PedidosRepository;
import com.example.TpFinalJava.data.ProductosRepository;
import com.example.TpFinalJava.dto.ProductosDTO;
import com.example.TpFinalJava.model.Pedidos;
import com.example.TpFinalJava.model.Productos;
import com.google.gson.Gson;

@Service
public class PedidosService {
	
	private final PedidosRepository pedidosRepository;
	private final ProductosRepository ProductosRepository;
	private final Gson gson = new Gson();
	
	public PedidosService(PedidosRepository pedidosRepository, ProductosRepository ProductosRepository) {
		this.pedidosRepository = pedidosRepository;
		this.ProductosRepository = ProductosRepository;
	}
	

	//Obtiene todos los Pedidos
	@Transactional(readOnly = true)
	public List<Pedidos> getPedidos(){
		return pedidosRepository.findAll();
	}

	//Obtiene todos los Pedidos de un usuario
	@Transactional(readOnly = true)
	public List<Pedidos> getPedidosByUser(int idUsuario){
		return pedidosRepository.findPedidobyUsername(idUsuario);
	}
	

	//Obtiene todos los Pedidos/ventas de un producto en particular
	@Transactional
	public Map<String, Map<String, Object>> getVentasByProduct(int idProducto,Date Datetime){
		
		//Traigo un listado de todos los pedidos para luego filtrarlo
		List<Pedidos> listaPedidos= pedidosRepository.findAll();
		
		//Creo un producto para luego obtener su nombre
		Productos producto = ProductosRepository.findById(idProducto).orElse(null);
		
		// Se definen variables
		Map<String, Map<String, Object>> VentasMap = new HashMap<String, Map<String, Object>>();

		Map<String, Object> ProductoReporte = new HashMap<String, Object>();
		
		float ventaTotalxProducto = 0;
		
		int cantidadTotalxProducto = 0;

		// Se guardo el dato del id en la variable

		//Itero en la lista de pedidos
		for (Pedidos pedidos : listaPedidos) {
			//Verifico que la fecha del pedido no sea menor a la del filtro
			if(pedidos.getFechaPedido().after(Datetime)) {
				
				//Creo un listado de productos con cada JSON guardado en la tabla pedidos
				ProductosDTO[] productosDTOList = gson.fromJson(pedidos.getDetalle(), ProductosDTO[].class);
				
				//Itero en cada elemento del listado y sumo 
				for (ProductosDTO Productos : productosDTOList) {
					
					if(Productos.id() == idProducto) {
						ventaTotalxProducto = ventaTotalxProducto + (Productos.precio() * Productos.cantidad());
						
						cantidadTotalxProducto = cantidadTotalxProducto + Productos.cantidad();
						
					}
				}
			}
			
		}
		ProductoReporte.put("Codigo Producto",idProducto);
		ProductoReporte.put("Producto",producto.getNombre());
		ProductoReporte.put("Precio unitario",producto.getPrecio());
		ProductoReporte.put("Total recaudado",ventaTotalxProducto);
		ProductoReporte.put("Cantidad total vendida",cantidadTotalxProducto);
		
		VentasMap.put("producto", ProductoReporte);
		
		return VentasMap;
	}

	//Obtiene todos los Pedidos/ventas del sistema
	@Transactional
	public List<Map<String, Map<String, Object>>> getVentasTotales(Date Datetime){
		
		List<Productos> ListProductos = ProductosRepository.findAll();
		
		List<Map<String, Map<String, Object>>> VentasMap = new ArrayList<>();
		
		for (Productos productos : ListProductos) {
			VentasMap.add(getVentasByProduct(productos.getId(),Datetime));
		}
		return VentasMap;
	}
	
	
	//Obtiene el pedido segun su id
	@Transactional(readOnly = true)
	public Pedidos getPedidoById(int PedidoID){
		return pedidosRepository.findById(PedidoID).orElse(null);
	}
	
	//Guarda el pedido en la base de datos
	@Transactional
	public Pedidos guardarPedido(Pedidos Pedido){
		
		return pedidosRepository.saveAndFlush(Pedido);
	}

	//Elimina el pedido de la base de datos
	@Transactional
	public void eliminarPedido(int PedidoID) {
		pedidosRepository.deleteById(PedidoID);
	}
}
