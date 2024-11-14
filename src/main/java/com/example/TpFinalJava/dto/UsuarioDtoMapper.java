package com.example.TpFinalJava.dto;

import org.springframework.stereotype.Service;

import com.example.TpFinalJava.model.Usuarios;

@Service
public class UsuarioDtoMapper{
	public UsuarioDTO toDto(Usuarios Usuarios) {
		// TODO Auto-generated method stub
		return new UsuarioDTO(
						 Usuarios.getId()
						,Usuarios.getUsername()
						,Usuarios.getRol().getId()
						,Usuarios.getRol().getRol()
						,Usuarios.getEmail()
						,Usuarios.getSaldo()
						,Usuarios.isActivo()
						);
	}
	
	public Usuarios toEntity(UsuarioDTO UsuarioDTO) {
		// TODO Auto-generated method stub
		return new Usuarios();
	}
	

}
