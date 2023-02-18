package org.RetoProgramacion;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface Excel {
    static ArrayList<Double> leerNotasExcel(int i) {
        try {
            FileInputStream archivo = new FileInputStream("../Datos Estudiantes.xlsx");
            Workbook libro = new XSSFWorkbook(archivo);
            Sheet hoja = libro.getSheetAt(0);
            int columnaIndex = 1;
            ArrayList<Double> notas = new ArrayList<>();
            int filaIndex = i+1; // Indice de la segunda fila
            Row fila = hoja.getRow(filaIndex);

            String valorCelda = fila.getCell(columnaIndex).toString();
            if (valorCelda.contains(",")) {
                valorCelda = valorCelda.replace(",", ".");
            }
            double nota1 = Double.parseDouble(valorCelda);
            notas.add(Math.round(nota1 * 100.0) / 100.0);

            valorCelda = fila.getCell(columnaIndex + 1).toString();
            if (valorCelda.contains(",")) {
                valorCelda = valorCelda.replace(",", ".");
            }
            double nota2 = Double.parseDouble(valorCelda);
            notas.add(Math.round(nota2 * 100.0) / 100.0);

            valorCelda = fila.getCell(columnaIndex + 2).toString();
            if (valorCelda.contains(",")) {
                valorCelda = valorCelda.replace(",", ".");
            }
            double nota3 = Double.parseDouble(valorCelda);
            notas.add(Math.round(nota3 * 100.0) / 100.0);

            archivo.close();

            return notas;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String leerCodigoExcel(int i) {
        try {
            FileInputStream archivo = new FileInputStream("../Datos Estudiantes.xlsx");
            Workbook libro = new XSSFWorkbook(archivo);
            Sheet hoja = libro.getSheetAt(0);
            int columnaIndex = 0;
            int filaIndex = i+1; // Indice de la segunda fila
            Row fila = hoja.getRow(filaIndex);
            String Codigo;
            Codigo = fila.getCell(columnaIndex).getStringCellValue();
            archivo.close();

            return Codigo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
