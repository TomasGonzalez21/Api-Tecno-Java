package com.example.TpFinalJava.controllers;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.TpFinalJava.model.Transferencias;
import com.example.TpFinalJava.model.Usuarios;
import com.example.TpFinalJava.service.TransferenciasService;
import com.example.TpFinalJava.service.UsuariosService;
import com.example.TpFinalJava.utilidades.Datetime;

import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("/Transferencias")

public class TransferenciasController {
	
	 @Autowired
	private TransferenciasService TransferenciasService ;
	 @Autowired
	private UsuariosService UsuariosService ;
	 @Autowired
	private EntityManager em;
		
	@GetMapping(path = "/GetTransferenciasUsuario")
	public ResponseEntity<?> getAllTransferencias(int IdUsuario) {
		try {			
			return new ResponseEntity<>(TransferenciasService.getTransferenciasByUser(IdUsuario), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al obtener los datos de las transferencias" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	        
		}
	}
	
	@GetMapping(path = "/GetTransferenciaId")	
	public ResponseEntity<?> getTransferenciasById(int id) {
		try {			
			return new ResponseEntity<>(TransferenciasService.getTransferenciaById(id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error al obtener los datos de la transferencia" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	        
		}
	}
	
	@PostMapping(path="/EnviarTransferencia")
	public ResponseEntity<?> crearTransferencia(float monto,int usuarioEmisorId,int usuarioReceptorId ) {
		
		//Transformo el monto en un decimal de 2 cifras
		BigDecimal bd = new BigDecimal(monto);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		
		try {
			//verifico que el monto no sea inferior a 0
			if(bd.floatValue() > 0) {
				//creo una nueva transferencia 
				Transferencias Transferencias = new Transferencias(0,bd.floatValue(), em.find(Usuarios.class, usuarioEmisorId), em.find(Usuarios.class, usuarioReceptorId), Datetime.getDate());
				
				
				//Actualizo el saldo del emisor
				if(!UsuariosService.UpdateSaldoUsuario(em.find(Usuarios.class, usuarioEmisorId), bd.floatValue(), 1))
		            return new ResponseEntity<>("Saldo insuficiente", HttpStatus.INTERNAL_SERVER_ERROR);
				
				//Actualizo el saldo del receptor
				UsuariosService.UpdateSaldoUsuario(em.find(Usuarios.class, usuarioReceptorId), bd.floatValue(), 2);
				
				//Concreto la transferencia
				TransferenciasService.enviarTransferencia(Transferencias);
				
				//Retorno los datos de la transferencia 
	            return new ResponseEntity<>(TransferenciasService.getTransferenciaById(Transferencias.getId()), HttpStatus.CREATED);
				
			}
			//retorno un mensaje con error en el monto
			return new ResponseEntity<>("Monto incorrecto", HttpStatus.INTERNAL_SERVER_ERROR);
			
			
        } catch (Exception e) {
        	
            // Envio el error al cliente
            return new ResponseEntity<>("Error al enviar la transferencia" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}
