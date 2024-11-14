package com.example.TpFinalJava.controllers;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.TpFinalJava.service.PedidosService;
import com.example.TpFinalJava.utilidades.Datetime;
import com.example.TpFinalJava.utilidades.GeneradorArchivoExcel;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;



@RestController
@RequestMapping("/Productos")

public class ReportesController {
	@Autowired
	private PedidosService PedidosService;
	@Autowired 
	private Gson gson;
	
	@SuppressWarnings("static-access")
	@GetMapping(path = "/VentasPorProducto")
	
	public ResponseEntity<?> VentasPorProducto(int idProducto, String Datetime) {	
		
		Datetime dt = new Datetime();		
		try {
			if(dt.fromString(Datetime).after(dt.getDate())) {
				return new ResponseEntity<>("La fecha indicada es mayor a la fecha actual", HttpStatus.NOT_ACCEPTABLE);
			}
			Map<String, Map<String, Object>> ventasTotales = PedidosService.getVentasByProduct(idProducto, dt.fromString(Datetime));

		    String Json = gson.toJson(ventasTotales, Map.class);
			
			return new ResponseEntity<>(Json, HttpStatus.ACCEPTED);			
		} catch (Exception e) {
			return new ResponseEntity<>("Error al obtener los datos solicitados \n"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@SuppressWarnings("static-access")
	@GetMapping(path = "/VentasTotales")
	public ResponseEntity<?> VentasTotales(String Datetime) {
		Datetime dt = new Datetime();	
		
    	try {
			if(dt.fromString(Datetime).after(dt.getDate())) {
				return new ResponseEntity<>("La fecha indicada es mayor a la fecha actual", HttpStatus.NOT_ACCEPTABLE);
				}    		

			List<Map<String, Map<String, Object>>> ventasTotales = PedidosService.getVentasTotales(dt.fromString(Datetime));
    		
            byte[] excelBytes = GeneradorArchivoExcel.generarReporte(ventasTotales);
            

         	// Build the HTTP response with the Excel content
         	HttpHeaders headers = new HttpHeaders();
         	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
         	headers.setContentDispositionFormData("attachment", "sample.xlsx");

         	return ResponseEntity.ok()
         						 .headers(headers)
         						 .body(excelBytes);
			
		}  catch (Exception e) {
			return new ResponseEntity<>("Error al obtener los datos solicitados \n"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	

	@SuppressWarnings("static-access")
	@GetMapping(path = "/VentasTotales2")
	public ResponseEntity<?> VentasTotalesData(String Datetime) {
		
		Datetime dt = new Datetime();		
		try {
			if(dt.fromString(Datetime).after(dt.getDate())) {
				return new ResponseEntity<>("La fecha indicada es mayor a la fecha actual", HttpStatus.NOT_ACCEPTABLE);
			}
			List<Map<String, Map<String, Object>>> ventasTotales = PedidosService.getVentasTotales(dt.fromString(Datetime));
			
			return new ResponseEntity<>(ventasTotales, HttpStatus.ACCEPTED);			
		} catch (Exception e) {
			return new ResponseEntity<>("Error al obtener los datos solicitados \n"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    	
}
