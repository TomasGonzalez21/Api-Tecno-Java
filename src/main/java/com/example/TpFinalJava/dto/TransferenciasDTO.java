package com.example.TpFinalJava.dto;

import java.util.Date;

public record TransferenciasDTO(	 
		 Integer id
		,float Monto
		,Integer UsuarioEmisor
		,String UsernameEmisor
		,Integer UsuarioReceptor
		,String UsernameReceptor
		,Date FechaTransferencia
		) {
	 
}
