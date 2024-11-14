package com.example.TpFinalJava.utilidades;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneradorFacturas {

	
	 public static byte[] generarFactura(int nropedido, String[][] detalle,float total,Date date) throws IOException {

         	// Crea el documento
	        PDDocument document = new PDDocument();
	        PDPage page = new PDPage();
	        document.addPage(page);

	        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
	         	// Crea el cuerpo del documento
	            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
	            contentStream.beginText();
	            
	            contentStream.newLineAtOffset(50, 750);
	            contentStream.showText("Factura nro "+nropedido);
	            contentStream.endText();

	            //Agrega la data
	            drawTable(contentStream, 50, 700, new String[]{"Descripcion", "Cantidad", "Precio", "Total"}, detalle);

	            contentStream.beginText();
	            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	            contentStream.newLineAtOffset(50, 500);
	            contentStream.showText("Total: $"+total);
	            contentStream.endText();
	            
	            //Agrega la fecha
	            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	            contentStream.beginText();
	            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	            contentStream.newLineAtOffset(50, 480);
	            contentStream.showText("Fecha: " + dateFormat.format(date));
	            contentStream.endText();
	        }

	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        document.save(baos);
	        document.close();
	        

	        return baos.toByteArray();

	    }

	    private static void drawTable(PDPageContentStream contentStream, float x, float y, String[] headers, String[][] data) throws IOException {
	        final int rows = data.length;
	        final int cols = headers.length;
	        final float rowHeight = 20f;
	        final float tableWidth = 500f;
	        final float colWidth = tableWidth / (float) cols;
	        final float cellMargin = 5f;

	        // Inseta los headers
	        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	        float tableYPosition = y;
	        float tableXPosition = x;
	        for (String header : headers) {
	            contentStream.beginText();
	            contentStream.newLineAtOffset(tableXPosition + cellMargin, tableYPosition - cellMargin);
	            contentStream.showText(header);
	            contentStream.endText();
	            tableXPosition += colWidth;
	        }

	        // Inserta el contenido de la tabla
	        tableYPosition -= rowHeight;
	        for (int i = 0; i < rows; i++) {
	            tableXPosition = x;
	            for (int j = 0; j < cols; j++) {
	                contentStream.beginText();
	                contentStream.newLineAtOffset(tableXPosition + cellMargin, tableYPosition - cellMargin);
	                contentStream.showText(data[i][j]);
	                contentStream.endText();
	                tableXPosition += colWidth;
	            }
	            tableYPosition -= rowHeight;
	        }
	    }

}
