package com.example.TpFinalJava.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TpFinalJava.dto.EnviosDTO;
import com.example.TpFinalJava.service.EnviosService;
import com.example.TpFinalJava.utilidades.Datetime;

@RestController
@RequestMapping("/Envios")

public class EnviosController {
	
	 @Autowired
	private EnviosService enviosService ;

	
	@GetMapping(path = "/ListadoEnvios")
	public  ResponseEntity<?> getAllEnvios() {
		try {
			List<EnviosDTO> enviosList = enviosService.getEnvios();
			return new ResponseEntity<>(enviosList, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("Error al obtener los datos: /n/t"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping(path = "/ByUser")
	public  ResponseEntity<?> getPedidosByUser(int idUsuario) {
		try {
			List<EnviosDTO> enviosList = enviosService.getEnviosByUser(idUsuario);
			return new ResponseEntity<>(enviosList, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("Error al obtener los datos: /n/t"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(path = "/IdEnvio")	
	public ResponseEntity<?> getIdEnvio(int id) {
		try {
			EnviosDTO Envio = enviosService.getEnvioById(id);
			return new ResponseEntity<>(Envio, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("Error al obtener los datos: /n/t"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path="/CrearEnvio")
	public ResponseEntity<?> crearEnvio(int NroPedido,int id_usuario,String lugarEnvio){

		EnviosDTO envio = new EnviosDTO(0,NroPedido,id_usuario,lugarEnvio,Datetime.getDate(),false,false,null,null);
		try {
			enviosService.guardarEnvio(envio);
			return new ResponseEntity<>("El envio se ha creado correctamente", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al obtener los datos: /n/t"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = "/ActualizarEnviado")
	public ResponseEntity<?> ActualizarEnviado(int Envio_id) {
		try {
			enviosService.ActualizarEnviado(Envio_id);
			return new ResponseEntity<>("El envio se ha modificado correctamente", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("Error al obtener los datos: /n/t"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping(path = "/ActualizarRecibido")
	public ResponseEntity<?> ActualizarRecibido(int Envio_id) {
		try {
			enviosService.ActualizarRecibido(Envio_id);
			return new ResponseEntity<>("El envio se ha modificado correctamente", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("Error al obtener los datos: /n/t"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
