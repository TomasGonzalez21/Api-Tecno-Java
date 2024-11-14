package com.example.TpFinalJava.dto;

import java.util.Date;


public record EnviosDTO(	 
		 Integer id
		 ,Integer NroPedido
		 ,Integer Id_usuario
		 ,String LugarEnvio
		 ,Date FechacreacionEnvio
		 ,boolean Enviado
		 ,boolean Recibido
		 ,Date FechaRecibido
		 ,Date FechaEnvio
		) {
	 
}

