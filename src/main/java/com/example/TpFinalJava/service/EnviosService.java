package com.example.TpFinalJava.service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TpFinalJava.data.EnviosRepository;
import com.example.TpFinalJava.dto.EnviosDTO;
import com.example.TpFinalJava.dto.EnviosDtoMapper;
import com.example.TpFinalJava.model.Envios;
import com.example.TpFinalJava.utilidades.Datetime;


@Service
public class EnviosService {
	
	private final EnviosRepository enviosRepository;
	private final EnviosDtoMapper EnviosDtoMapper;
	
	public EnviosService(EnviosRepository enviosRepository, EnviosDtoMapper EnviosDtoMapper) {
		this.enviosRepository = enviosRepository;
		this.EnviosDtoMapper = EnviosDtoMapper;
	}
	
	//Obtiene todos los envios
	@Transactional(readOnly = true)
	public List<EnviosDTO> getEnvios(){
		return enviosRepository.findAll().stream().map(EnviosDtoMapper::toDto).collect(Collectors.toList());
	}

	//Obtiene todos los envios segun el usuario
	@Transactional(readOnly = true)
	public List<EnviosDTO> getEnviosByUser(int idUsuario){
		return enviosRepository.findEnviobyUsername(idUsuario).stream().map(EnviosDtoMapper::toDto).collect(Collectors.toList());
	}

	//Obtiene todos los envios segun el id del envio
	@Transactional(readOnly = true)
	public EnviosDTO getEnvioById(int envioID){
		return enviosRepository.findById(envioID).map(EnviosDtoMapper::toDto).orElse(null);
	}
	

	//Guarda el envio en la base de datos
	@Transactional
	public Envios guardarEnvio(EnviosDTO envioNuevo) throws ParseException{
		
		return enviosRepository.saveAndFlush(EnviosDtoMapper.toEntity(envioNuevo));

	}	

	//Actualiza el estado ENVIADO del envio en la base de datos
	@Transactional
	public void ActualizarEnviado(int Envio_id) {

		//Verifica que el envio exista
		Envios envioExistente = enviosRepository.findById(Envio_id).orElse(null);
		
		if(envioExistente != null){
			//Si el envio existe
			envioExistente.setEnviado(true);
			envioExistente.setFecha_envio_enviado(Datetime.getDate());
			
			enviosRepository.saveAndFlush(envioExistente);
		}
		
	}

	//Actualiza el estado RECIBIDO del envio en la base de datos
	@Transactional
	public void ActualizarRecibido(int Envio_id) {
		

		//Verifica que el envio exista

		Envios envioExistente = enviosRepository.findById(Envio_id).orElse(null);
		
		if(envioExistente != null){
			//Si el envio existe
			envioExistente.setRecibido(true);
			envioExistente.setFecha_envio_recibido(Datetime.getDate());
			
			enviosRepository.saveAndFlush(envioExistente);
		}
		
	}

	//Elimina al envio en la base de datos
	@Transactional
	public void eliminarEnvio(int envioID) {
		enviosRepository.deleteById(envioID);
	}
}
