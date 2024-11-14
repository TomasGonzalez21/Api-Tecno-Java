package com.example.TpFinalJava.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TpFinalJava.data.TransferenciasRepository;
import com.example.TpFinalJava.dto.TransferenciasDTO;
import com.example.TpFinalJava.dto.TransferenciasDtoMapper;
import com.example.TpFinalJava.model.Transferencias;

@Service
public class TransferenciasService {
	

	private final TransferenciasRepository TransferenciasRepository;
	private final TransferenciasDtoMapper TransferenciasDtoMapper;
	
	
	public TransferenciasService(TransferenciasRepository TransferenciasRepository, TransferenciasDtoMapper TransferenciasDtoMapper) {
		this.TransferenciasRepository = TransferenciasRepository;
		this.TransferenciasDtoMapper = TransferenciasDtoMapper;
	}
	

	@Transactional(readOnly = true)
	public List<TransferenciasDTO> getTransferenciasByUser(int IdUsuario){
		
		return TransferenciasRepository.findTransferenciasUsuario(IdUsuario)
				 					   .stream()
				 					   .map(TransferenciasDtoMapper::toDto)
				 					   .collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public TransferenciasDTO getTransferenciaById(int TransferenciaID){
		return TransferenciasRepository.findById(TransferenciaID)
				   					   .map(TransferenciasDtoMapper::toDto)
				   					   .orElse(null);
	}
	
	public Transferencias enviarTransferencia(Transferencias Transferencia){
		try {			
			//TransferenciasRepository.ConcretarTransferencia(Transferencia.getUsuarioEmisor().getId(), Transferencia.getMonto(), Transferencia.getUsuarioReceptor().getId());
		    
			return TransferenciasRepository.saveAndFlush(Transferencia);
			
		} catch (Exception e) {
			return null; 
		}
	}

	@Transactional
	public void eliminarTransferencia(int TransferenciaID) {
		TransferenciasRepository.deleteById(TransferenciaID);
	}
}