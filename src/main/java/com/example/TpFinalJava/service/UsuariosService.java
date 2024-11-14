package com.example.TpFinalJava.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TpFinalJava.data.RolesRepository;
import com.example.TpFinalJava.data.UsuariosRepository;
import com.example.TpFinalJava.dto.UsuarioDTO;
import com.example.TpFinalJava.dto.UsuarioDtoMapper;
import com.example.TpFinalJava.model.Usuarios;

@Service
public class UsuariosService {
	
	private final UsuariosRepository userRepository;
	
	private final UsuarioDtoMapper UsuarioDtoMapper;
	
	private final RolesRepository RolesRepository;
    
	public UsuariosService(UsuariosRepository UserRepository,UsuarioDtoMapper UsuarioDtoMapper,RolesRepository RolesRepository) {
		this.userRepository = UserRepository;
		this.UsuarioDtoMapper = UsuarioDtoMapper;
		this.RolesRepository = RolesRepository;
	}
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> getUsuarios(){
		
		return userRepository.findAll()
							 .stream()
							 .map(UsuarioDtoMapper::toDto)
							 .collect(Collectors.toList())
				;
	}

	@Transactional(readOnly = true)
	public UsuarioDTO getUsuarioById(int id){
		return userRepository.findById(id)
				 .map(UsuarioDtoMapper::toDto)
				 .orElse(null);
	}

	@Transactional(readOnly = true)
	public UsuarioDTO getUsuarioByUsername(String username){
		return userRepository.userValidation(username)
				 .map(UsuarioDtoMapper::toDto)
				 .orElse(null);
	}
	
	@Transactional
	public boolean UpdateSaldoUsuario(Usuarios usuario, float montoTransferencia, int emisor_receptor) {
		float saldoActual = usuario.getSaldo();

		
		
		
		//verifico que papel cumple en la operacion, si es receptor o es emisor
		if(emisor_receptor == 1) {
			//Verifico que el rol no sea de admin y el monto no sea mayor al saldo de la cuenta
			if(usuario.getRol().getId() != 1 && montoTransferencia>saldoActual) {
				return false;
			}
			//si el usuario emite la transferencia se le descuenta del saldo
			float saldoFinal = saldoActual - montoTransferencia;
			usuario.setSaldo(saldoFinal);
		}
		
		else if(emisor_receptor == 2){
			//si el usuario recibe la transferencia se le agrega al saldo
			float saldoFinal = saldoActual + montoTransferencia;
			usuario.setSaldo(saldoFinal);
		}
		userRepository.saveAndFlush(usuario);
		return true;
	}
	
	
	
	@Transactional(readOnly = true)
	public Usuarios Login(String Username, String Password){
		return userRepository.ExisteUsuario(Username,Password);
	}
	
	@Transactional(readOnly = true)
	public boolean existeUsuario(String username) {
		
		Usuarios validarUsuario = userRepository.userValidation(username).orElse(null);
		if(validarUsuario != null)
			return true;
		
		return false;
	}
	
	@Transactional(readOnly = true)
	public boolean existeUsuarioID(int id) {
		
		Usuarios validarUsuario = userRepository.findById(id).orElse(null);
		if(validarUsuario != null)
			return true;
		
		return false;
	}
	
	@Transactional
	public Usuarios guardarUsuario(Usuarios usuario){
		
		return userRepository.saveAndFlush(usuario);
	}
	

	@Transactional
	public Usuarios dehabilitarUsuario(int id, boolean habilitar){
		Usuarios usuario = userRepository.findById(id).orElse(null);
		if(usuario == null)
			return null;
		
		usuario.setActivo(habilitar);
		
		return userRepository.saveAndFlush(usuario);
	}

	@Transactional
	public Usuarios actualizarUsuario(int id,String username,String password, int RolID, String email){
		
		
		Usuarios usuario = userRepository.findById(id).orElse(null);
		if(usuario == null)
			return null;
		
		usuario.setUsername(username);
		if(password != null || password != "editarUsuario")
			usuario.setPassword(password);
		
		usuario.setRol(RolesRepository.findById(RolID).orElse(null));
		
		usuario.setEmail(email);
		
		return userRepository.saveAndFlush(usuario);
	}

	@Transactional
	public void eliminarUsuario(int usuarioId) {
		userRepository.deleteById(usuarioId);
	}
}
