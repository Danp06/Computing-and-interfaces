package org.RetoProgramacion;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    private static boolean verificarLista(ArrayList<Estudiante> Lista, int n) {
        if (Lista == null || Lista.isEmpty() || n == 0) {
            System.out.println("ERROR!!! Por favor ingresa primeros la cantidad estudiantes " +
                    "antes de elegir esta opcion.");
            return false;
        }
        return true;
    }

    /**
     * Método principal que ejecuta el programa.
     *
     * @param args los argumentos que se pasan al programa al ejecutarlo.
     */
    public static void main(String[] args) {
        int n = 0, opcion = 0;
        long inicio = System.currentTimeMillis(), noCountTime = 0;
        ArrayList<Estudiante> Lista = null;
        while (opcion!=11){
            Curso.menu();
            opcion = Curso.opcionMenu();
            System.out.println("");
            switch (opcion){
                case 1: long stoptime1 = System.currentTimeMillis();
                        n = Curso.verificarNEstudiantes();
                        long restart1 = System.currentTimeMillis();
                        noCountTime += restart1 - stoptime1;
                        break;
                case 2: long stoptime2 = System.currentTimeMillis();
                        Lista = Curso.listEstudiantes(n);
                        long restart2 = System.currentTimeMillis();
                        noCountTime += restart2 - stoptime2;
                    break;
                case 3: if (!verificarLista(Lista, n)) break;
                    for (Estudiante i:Lista) {
                        i.mensajeAprobado();
                    }
                    break;
                case 4: if (!verificarLista(Lista, n)) break;
                    System.out.println("Numero de aprobados es: " + Curso.cantidadAprobados(Lista));
                    break;
                case 5: if (!verificarLista(Lista, n)) break;
                    System.out.println("Numero de reaprobados es: " + (n-Curso.cantidadAprobados(Lista)));
                    break;
                case 6: if (!verificarLista(Lista, n)) break;
                    System.out.println("\nEl promedio de las notas definitivas del curso es: " + Curso.promedioDefinitiva(Lista,n));
                    break;
                case 7: if (!verificarLista(Lista, n)) break;
                    Curso.listEstudiantesCinco(Lista);
                    break;
                case 8: if (!verificarLista(Lista, n)) break;
                    Curso.desviacionDefinitiva(Lista);
                    break;
                case 9: if (!verificarLista(Lista, n)) break;
                    Lista.sort(Comparator.comparing(Estudiante::getNotaDefinitiva).reversed());
                    System.out.println(".::LISTA ORDENADA POR DEFINITIVA DE MAYOR A MENOR::.");
                    for (int i = 0; i < Lista.size();i++) {
                        System.out.println((i+1) + ".Estudiante: " + Lista.get(i).getCodigo() + " - " + Lista.get(i).getNotaDefinitiva());
                    }
                    break;
                case 10: if (!verificarLista(Lista, n)) break;
                    Curso.ListaMaxMin(Lista);
                    break;
                case 11:
                    System.out.println("Has salido del menu :)");
                    long fin = System.currentTimeMillis();
                    System.out.println("Duracion: " + (((fin-inicio)-noCountTime)/1000 + "seg"));
                    break;
                default: opcion = 0;
            }
        }
    }
}
