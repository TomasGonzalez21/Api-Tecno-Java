package com.example.TpFinalJava.dto;


import org.springframework.stereotype.Component;

import com.example.TpFinalJava.model.Transferencias;

@Component
public class TransferenciasDtoMapper{
	
	public TransferenciasDTO toDto(Transferencias Transferencias) {
		
        return new TransferenciasDTO(
        		  Transferencias.getId()
				 ,Transferencias.getMonto()
				 ,Transferencias.getUsuarioEmisor().getId()
				 ,Transferencias.getUsuarioEmisor().getUsername()
				 ,Transferencias.getUsuarioReceptor().getId()
				 ,Transferencias.getUsuarioReceptor().getUsername()
				 ,Transferencias.getFechaTransferencia()
				);
    }

    public Transferencias toEntity(TransferenciasDTO TransferenciasDTO) {
        return new Transferencias();
    }
}
