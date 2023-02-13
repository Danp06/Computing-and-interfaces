package org.RetoProgramacion;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static Scanner Entrada = new Scanner(System.in);


    /**
     * Método principal que ejecuta el programa.
     *
     * @param args los argumentos que se pasan al programa al ejecutarlo.
     */
    public static void main(String[] args) {

        int n = 0, opcion = 0;
        ArrayList<Estudiante> Lista = null;
        while (opcion!=8){
            Curso.menu();
            opcion = Curso.opcionMenu();
            System.out.println("");
            switch (opcion){
                case 1: n = Curso.verificarNEstudiantes(); break;
                case 2: Lista = Curso.listEstudiantes(n);
                    break;
                case 3: for (Estudiante i:Lista) {
                    i.mensajeAprobado();} break;
                case 4: System.out.println("Numero de aprobados es: " + Curso.cantidadAprobados(Lista)); break;
                case 5: System.out.println("Numero de reaprobados es: " + (n-Curso.cantidadAprobados(Lista))); break;
                case 6: System.out.println("\nEl promedio de las notas definitivas del curso es: "
                        + Curso.promedioDefinitiva(Lista,n)); break;
                case 7: Curso.listEstudiantesCinco(Lista); break;
                case 8:
                    System.out.println("Has salido del menu :)");break;
                default: opcion = 0;
            }
        }
    }
}
