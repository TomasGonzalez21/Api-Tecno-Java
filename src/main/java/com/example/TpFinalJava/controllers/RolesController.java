package com.example.TpFinalJava.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TpFinalJava.model.Roles;
import com.example.TpFinalJava.service.RolesService;
@RestController
@RequestMapping("/Roles")

public class RolesController {
	@Autowired
	private RolesService RolesService;
	
	@PostMapping(path = "/crearRol")
	
	public ResponseEntity<?> crearRol(int idRol, String NombreRol) {	
		try {
			Roles rol = new Roles(idRol, NombreRol);
			RolesService.guardarRol(rol);
			
			return new ResponseEntity<>(rol, HttpStatus.ACCEPTED);			
		} catch (Exception e) {
			return new ResponseEntity<>("Error al obtener los datos solicitados \n"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@GetMapping(path = "/getRoles")
	public ResponseEntity<?> getRoles() {
		try {
			
			List<Roles> roles = RolesService.getRoles();
			return new ResponseEntity<>(roles, HttpStatus.ACCEPTED);			
		} catch (Exception e) {
			return new ResponseEntity<>("Error al obtener los datos solicitados \n"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
