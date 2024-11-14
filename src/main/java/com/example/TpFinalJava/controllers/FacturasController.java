package com.example.TpFinalJava.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TpFinalJava.model.Pedidos;
import com.example.TpFinalJava.service.PedidosService;
import com.example.TpFinalJava.utilidades.GeneradorFacturas;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/GeneradorPDF")
public class FacturasController {
	
	 @Autowired
	private PedidosService PedidosService;

	private final Gson gson = new Gson();
	
    @GetMapping("/generarFactura")
    public ResponseEntity<byte[]> generatePdf(int idPedido) throws IOException {
        try {

        	Pedidos pedido = PedidosService.getPedidoById(idPedido);	

            // Parse the detalle JSON string into a list of maps
            List<Map<String, String>> detallePedidoList = gson.fromJson(
                    pedido.getDetalle(),
                    new TypeToken<List<Map<String, String>>>() {}.getType()
            );

            // Convert the list of maps to the required array structure
            String[][] detallePedido = new String[detallePedidoList.size()][];
            for (int i = 0; i < detallePedidoList.size(); i++) {
                Map<String, String> item = detallePedidoList.get(i);
                
                
                detallePedido[i] = new String[]{
                		item.get("nombre"),
                		item.get("cantidad"),
                		item.get("precio"),
                		Integer.toString(Integer.parseInt(item.get("cantidad")) * Integer.parseInt(item.get("precio"))),
                };
            }
			
			
            // Generate the PDF
            byte[] pdfBytes = GeneradorFacturas.generarFactura(pedido.getId_Pedido(),detallePedido,pedido.getMontoTotal(),pedido.getFechaPedido());

            // Build the HTTP response with the PDF content
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "factura_pedido_"+idPedido+".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
  
    }
}
