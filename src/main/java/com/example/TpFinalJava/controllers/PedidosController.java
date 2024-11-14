package com.example.TpFinalJava.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TpFinalJava.dto.ProductosDTO;
import com.example.TpFinalJava.model.Pedidos;
import com.example.TpFinalJava.model.Productos;
import com.example.TpFinalJava.service.PedidosService;
import com.example.TpFinalJava.service.ProductosService;
import com.example.TpFinalJava.utilidades.Datetime;
import com.google.gson.Gson;

@RestController
@RequestMapping("/Pedidos")

public class PedidosController {
	
	 @Autowired
	private PedidosService PedidosService;
	 @Autowired
	private ProductosService ProductosService;
	 
	private final Gson gson = new Gson();
	
	@GetMapping(path = "/AllPedidos")
	public ResponseEntity<?> getAllPedidos() {
		try {
			List<Pedidos> pedidos = PedidosService.getPedidos();		
			
            // Return success response
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return new ResponseEntity<>("Error al obtener el pedido" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping(path = "/ByUser")
	public ResponseEntity<?> getPedidosByUser(int idUsuario) {
		try {
			List<Pedidos> pedidos = PedidosService.getPedidosByUser(idUsuario);		
			
            // Return success response
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return new ResponseEntity<>("Error al obtener el pedido" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping(path = "/IdPedido")	
	public ResponseEntity<?> getPedido(int id) {
		try {
			Pedidos pedido = PedidosService.getPedidoById(id);		
			
            // Return success response
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return new ResponseEntity<>("Error al obtener el pedido" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}	
	
	@PostMapping(path = "/CrearPedido")
	public ResponseEntity<?> guardarPedidos(String detalle, int UsuarioId) {
		try {
			ProductosDTO[] productosDTOList = gson.fromJson(detalle, ProductosDTO[].class);
			
			boolean condicionesPedido = true;
			
			for (ProductosDTO productoPedido : productosDTOList) {
				Productos producto = ProductosService.getProductoById(productoPedido.id());
				long cantidad = productoPedido.cantidad().longValue();
				
				if(cantidad < 0 || producto == null || cantidad > producto.getStock()){					
					condicionesPedido = false;
				}	
			}
			
			if(condicionesPedido == true ) {
				ProductosService.ActualizarStockPostCompra(productosDTOList);
	            // Guardar pedido
				Pedidos pedido = PedidosService.guardarPedido(new Pedidos(detalle.replaceAll("\\s+", ""),UsuarioId, ProductosService.ObtenerTotal(productosDTOList),Datetime.getDate()));

	            // Return success response
	            return new ResponseEntity<>(pedido.getId_Pedido(), HttpStatus.CREATED);
				
			}
            return new ResponseEntity<>("Error al crear el pedido", HttpStatus.INTERNAL_SERVER_ERROR);
			
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return new ResponseEntity<>("Error al crear el pedido" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@PutMapping
	public Pedidos actualizarPedidos(Pedidos Pedido) {
		return PedidosService.guardarPedido(Pedido);
	}
	@DeleteMapping
	public void eliminarPedido(int PedidoId) {
		PedidosService.eliminarPedido(PedidoId);
	
	}
}
