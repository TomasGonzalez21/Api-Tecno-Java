package com.example.TpFinalJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TpFinalJava.model.Usuarios;
import com.example.TpFinalJava.service.UsuariosService;

import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/Usuarios")

public class UsuariosController {
	
	 @Autowired
	private UsuariosService usuariosService;
	 
	 
	@GetMapping(path = "/AllUsers")
	public ResponseEntity<?> getAllUsers() {		
		try {
            return new ResponseEntity<>(usuariosService.getUsuarios(), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la solicitud" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(path = "/Id")
	public ResponseEntity<?> getUser(int id) {		
		try {
            return new ResponseEntity<>(usuariosService.getUsuarioById(id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la solicitud" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(path = "/SaldoUserId")
	public ResponseEntity<?> getSaldoUserId(int id) {		
		try {
            return new ResponseEntity<>(usuariosService.getUsuarioById(id).saldo(), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la solicitud" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(path = "/Username")
	public ResponseEntity<?> getUser(String Username) {		
		try {
            return new ResponseEntity<>(usuariosService.getUsuarioByUsername(Username), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la solicitud" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping(path = "/Login")
	public ResponseEntity<?> Login(String username, String password) {
		try {
			Usuarios usuario= usuariosService.Login(username,password);
			if(usuario == null) 
				return new ResponseEntity<>("Datos de logeo erroneos", HttpStatus.INTERNAL_SERVER_ERROR);
			
            return new ResponseEntity<>(usuario, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la solicitud" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping(path="/crearUsuario")
	public ResponseEntity<?> guardarUsuario(@RequestBody Usuarios usuario) {
		
		if (usuariosService.existeUsuario(usuario.getUsername())) {
			return new ResponseEntity<>("El username ya esta tomado", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		try {
			usuario.setSaldo(0);
			Usuarios usuarioCreado = usuariosService.guardarUsuario(usuario);
			
            return new ResponseEntity<>("Se creo exitosamente el usuario "+usuarioCreado.getUsername(), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error al crear el usuario" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		

	}
	
	
	@PutMapping(path="/deshabilitarUsuario")
	public ResponseEntity<?> deshabilitarUsuario(int idUsuario, boolean habilitar) {
		
		if (!usuariosService.existeUsuarioID(idUsuario)) {
			return new ResponseEntity<>("El usuario no existe", HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		try {
			usuariosService.dehabilitarUsuario(idUsuario,habilitar);
	         return new ResponseEntity<>("El usuario se deshabilito exitosamente", HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error al deshabilitar el usuario" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		

	}
		
	@PutMapping(path="/actualizarUsuario")
	public ResponseEntity<?> actualizarUsuario(int idUsuario, String username, String email, String password, int RolID, boolean cambiarPassword) {
		
		if (!usuariosService.existeUsuarioID(idUsuario)) {
			return new ResponseEntity<>("El usuario no existe", HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		try {
			if(password != null ||(password == null && cambiarPassword == false)) {
				usuariosService.actualizarUsuario(idUsuario,username,password,RolID,email);
	            return new ResponseEntity<>("El usuario se modifico exitosamente", HttpStatus.OK);
				
			}
			else if(password == null && cambiarPassword == true ) {
				password = "ContraseñaProvisional";
				usuariosService.actualizarUsuario(idUsuario,username,password,RolID,email);
	            return new ResponseEntity<>("El usuario se modifico exitosamente con la contraseña provisional", HttpStatus.OK);
				
			}
            return new ResponseEntity<>("La contraseña no puede ser nula ", HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error al modificar el usuario" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		

	}
	@DeleteMapping(path="/eliminarUsuario")
	public ResponseEntity<?> eliminarusuario(int idUsuario) {
		try {
			if (!usuariosService.existeUsuarioID(idUsuario)) {
				return new ResponseEntity<>("El usuario no existe", HttpStatus.INTERNAL_SERVER_ERROR);			
			}
			usuariosService.eliminarUsuario(idUsuario);
            return new ResponseEntity<>("El usuario se elimino exitosamente", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error al modificar el usuario" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
