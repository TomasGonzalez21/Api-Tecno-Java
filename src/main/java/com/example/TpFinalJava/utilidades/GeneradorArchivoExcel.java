package com.example.TpFinalJava.utilidades;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GeneradorArchivoExcel {

    public static byte[] generarReporte(List<Map<String, Map<String, Object>>> ventasTotales) {
        Workbook workbook = new XSSFWorkbook();

        try {
            // Crea el documento
            Sheet sheet = workbook.createSheet("Sheet1");

            // Crea el header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Codigo Producto");
            headerRow.createCell(1).setCellValue("Producto");
            headerRow.createCell(2).setCellValue("Cantidad total vendida");
            headerRow.createCell(3).setCellValue("Precio unitario");
            headerRow.createCell(4).setCellValue("Total recaudado");

            int rowNum = 1;
            for (Map<String, Map<String, Object>> dataInforme : ventasTotales) {
            	//Agrega la data
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue((Integer) dataInforme.get("producto").get("Codigo Producto"));
                dataRow.createCell(1).setCellValue((String) dataInforme.get("producto").get("Producto"));
                dataRow.createCell(2).setCellValue((Integer) dataInforme.get("producto").get("Cantidad total vendida"));
                dataRow.createCell(3).setCellValue((Long) dataInforme.get("producto").get("Precio unitario"));
                dataRow.createCell(4).setCellValue((Float) dataInforme.get("producto").get("Total recaudado"));
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            return baos.toByteArray();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
                // Handle the exception or log it
                e.printStackTrace();
            }
        }
    }
}
