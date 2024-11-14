package com.example.TpFinalJava.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.TpFinalJava.model.Productos;
import com.example.TpFinalJava.service.ProductosService;
import com.itextpdf.io.codec.Base64;

import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/Productos")

public class ProductosController {
	
	 @Autowired
	private ProductosService productosService;
		
	@GetMapping(path = "/AllProductos")
	public ResponseEntity<?> getAllProductos() {		
		try {
            return new ResponseEntity<>(productosService.getProductos(), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la solicitud" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/IdProducto")	
	public ResponseEntity<?> getProducto(int Id) {
		try {
            return new ResponseEntity<>(productosService.getProductoById(Id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la solicitud" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/CrearProducto" ,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> guardarProductos(

			@RequestParam("id") int id,
			@RequestParam("stock") int stock,
			@RequestParam("precio") int precio,
			@RequestParam("nombre") String nombre,
			@RequestParam("activo") boolean activo,
			@RequestParam("descripcion") String descripcion,
            @RequestPart("imagen") MultipartFile imagen
			//@RequestBody Productos Producto
			) throws IOException {
		
		   byte[] imageBytes = imagen.getBytes();
		   
	        // Save the imageBytes to a file or process it as needed

	        // Example: Create a Productos object
		   Productos Producto = new Productos(nombre,descripcion,precio,stock,activo,Base64.encodeBytes(imageBytes));
	        //Productos producto = new Productos(id, stock, precio, nombre, activo, descripcion, imageBytes);

		
		if(Producto.getPrecio()<0 || Producto.getStock()<0)
			return new ResponseEntity<>("Error en el ingreso de datos, revise los datos ingresados", HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			productosService.guardarProducto(Producto);
			return new ResponseEntity<>("El producto se creo correctamente", HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la solicitud" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = "/ActualizarProducto")
	public ResponseEntity<?> actualizarProductos(@RequestBody Productos Producto) {
		if(productosService.getProductoById(Producto.getId()) == null)
			return new ResponseEntity<>("El producto no existe", HttpStatus.INTERNAL_SERVER_ERROR);
		
		if(Producto.getPrecio()<0 || Producto.getStock()<0)
			return new ResponseEntity<>("Error en el ingreso de datos, revise los datos ingresados", HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			
			productosService.actualizarProducto(Producto);
			return new ResponseEntity<>("El producto se actualizo correctamente", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la solicitud" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = "/ActivarProducto")
	public ResponseEntity<?> ActivarProducto(int Id, boolean activar) {
		
		if(productosService.getProductoById(Id) == null)
			return new ResponseEntity<>("El producto no existe", HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			productosService.activarProducto(Id, activar);
			return new ResponseEntity<>("El producto se activo correctamente", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la solicitud" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PutMapping(path = "/ActualizarStock")
	public ResponseEntity<?>  actualizarStock(int Id ,long stock) {
		
		Productos producto = productosService.getProductoById(Id);
		
		if(producto == null)
			return new ResponseEntity<>("El producto no existe", HttpStatus.INTERNAL_SERVER_ERROR);
		
		if(stock<0)
			return new ResponseEntity<>("Error en el ingreso de datos, revise los datos ingresados", HttpStatus.INTERNAL_SERVER_ERROR);

		try {
			productosService.ActualizarStock(producto,stock);
			return new ResponseEntity<>("El stock se actualizo correctamente", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la solicitud" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path = "/EliminarProducto")
	public ResponseEntity<?> eliminarProducto(int productoId) {		
		try {
			productosService.eliminarProductos(productoId);
			return new ResponseEntity<>("El producto se elimino correctamente", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la solicitud" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	
	}
}
