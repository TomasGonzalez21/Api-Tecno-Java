package com.example.TpFinalJava.dto;


import java.text.ParseException;

import org.springframework.stereotype.Component;

import com.example.TpFinalJava.data.PedidosRepository;
import com.example.TpFinalJava.data.UsuariosRepository;
import com.example.TpFinalJava.model.Envios;


@Component
public class EnviosDtoMapper{
	
	private final UsuariosRepository UsuariosRepository;
	private final PedidosRepository PedidosRepository;
	
	public EnviosDtoMapper(UsuariosRepository UserRepository,PedidosRepository PedidosRepository) {
		this.UsuariosRepository = UserRepository;
		this.PedidosRepository = PedidosRepository;
	}
	
	public EnviosDTO toDto(Envios Envios) {
		
        return new EnviosDTO(
        		  Envios.getId_Envio()
        		 ,Envios.getNroPedido().getId_Pedido()
				 ,Envios.getId_usuario().getId()
				 ,Envios.getLugarEnvio()
				 ,Envios.getFechaEnvio()
				 ,Envios.isEnviado()
				 ,Envios.isRecibido()
				 ,Envios.getFecha_envio_recibido()
				 ,Envios.getFecha_envio_enviado()
				);
    }
	
    public Envios toEntity(EnviosDTO EnviosDTO) throws ParseException {
        return new Envios(
        		 EnviosDTO.id()
        		,EnviosDTO.FechacreacionEnvio()
        		,PedidosRepository.findById(EnviosDTO.NroPedido()).orElse(null)
        		,EnviosDTO.LugarEnvio()
        		,EnviosDTO.Enviado()
        		,EnviosDTO.Recibido()
        		,UsuariosRepository.findById(EnviosDTO.Id_usuario()).orElse(null));
    }
}
