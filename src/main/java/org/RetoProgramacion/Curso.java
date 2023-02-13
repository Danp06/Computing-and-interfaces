package org.RetoProgramacion;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *La interface Curso representa e provee toda la informacion basica de los estudiantes dentro del curso
 */
 interface Curso {
    Scanner Entrada = new Scanner(System.in);

    /**
     * Método para verificar el número de estudiantes.
     *
     * @return El número de estudiantes válido (entero entre 1 y 40).
     */
     static int verificarNEstudiantes() {
        int n;
        while (true) {
            // Ciclo infinito hasta que se ingrese un número válido
            System.out.print("Ingresa un número entero entre 1 y 40: ");
            if (Entrada.hasNextInt()) {
                n = Entrada.nextInt();
                // Verificación del rango del número
                if (n > 0 && n <= 40) break;
                else System.out.println("Entrada inválida, por favor ingresa un número entero entre 1 y 40");
            } else {
                System.out.println("Entrada inválida, por favor ingresa un número entero entre 1 y 40");
                Entrada.next();
            }
        }
        return n;
    }

    /**
     * Método para verificar las notas de los estudiantes.
     *
     * @return El la nota del estudiante entre 0.0 y 5.0
     */
     static double verificarNotas() {
        double n;
         // Ciclo infinito hasta que se ingrese un número válido
        while (true) {
            if (Entrada.hasNextDouble()) {
                n = Entrada.nextDouble();
                // Verificación del rango del número
                if (n >= 0.0 && n <= 5.0) {
                    break;
                } else {
                    System.out.println("Entrada inválida, por favor ingresa un número entre 0.0 y 5.0");
                }
            } else {
                System.out.println("Entrada inválida, por favor ingresa un número entre 0.0 y 5.0");
                Entrada.next();
            }
        }
        return n;
    }

    /**
     * Método para crear una lista de estudiantes.
     *
     * @param n El número de estudiantes que se deben agregar a la lista.
     * @return La lista de estudiantes.
     */
     static @NotNull ArrayList<Estudiante> listEstudiantes(int n) {
        ArrayList<Estudiante> list = new ArrayList<>();
        String codigo;
        double notaP, notaQ, notaT, notaD;
        boolean aprobo;
         // Ciclo para agregar los datos de cada estudiante a la lista
        for (int i = 0; i < n; i++) {
            System.out.print("\nDijite el codigo del estudiante: ");
            codigo = Entrada.next();
            System.out.print("Digite la nota del parcial: ");
            notaP = verificarNotas();
            System.out.print("Digite la nota del quiz: ");
            notaQ = verificarNotas();
            System.out.print("Digite la nota del taller: ");
            notaT = verificarNotas();
            notaD = (notaP * 0.5 + notaQ * 0.3 + notaT * 0.2);
            aprobo = aproboMateria(notaD);
            Estudiante alumno = insertarDatosAlumno(codigo, notaP, notaQ, notaT, notaD, aprobo);
            list.add(alumno);
        }
        return list;
    }

    /**
     * Crea un nuevo objeto de la clase Estudiante con los atributos especificados
     * y lo retorna.
     *
     * @param a Código del estudiante
     * @param b Nota del parcial
     * @param c Nota del quiz
     * @param d Nota del taller
     * @param e Nota final
     * @param f Si aprobó o no la materia
     * @return Un nuevo objeto de la clase Estudiante
     */
    @Contract("_, _, _, _, _, _ -> new")
     static @NotNull Estudiante insertarDatosAlumno(String a, Double b, Double c, Double d, double e, boolean f) {
        return new Estudiante(a, b, c, d, e, f);
    }

    /**
     * Metodo que retorna el estado que se encuentra en el curso
     *
     * @param x La nota definitiva
     * @return True para arpobado o False para Reprobado
     */
     static boolean aproboMateria(double x) {
        if (x >= 3.0) {return true;}
        return false;
    }

    /**
     * Metodo recorre la lista de estudiantes para decir cuantos aprobaron
     *
     * @param x lista de estudiantes en el curso
     * @return La cantidad de estudiantes que aprovaron el curso
     */
     static int cantidadAprobados(@NotNull ArrayList<Estudiante> x){
        int n = 0;
        // Foreach que recorre la lista de estudiantes
        for (Estudiante i :x){
            // Si estado del esturiante es True suma 1 a n que es una variable acumulativa
            if(i.isAprobo()){
                n++;
            }
        }
        return n;
    }

    /**
     * Metodo que calcula el promedio general del curso
     *
     * @param x lista de estudiantes en el curso
     * @param n Cantidad de estudiantes en el curso
     * @return Promedio general del curso
     */
     static double promedioDefinitiva(@NotNull ArrayList<Estudiante> x, int n){
        double prom = 0;
        for (Estudiante i:x) prom += i.getNotaDefinitiva();
        return prom / n;
    }

    /**
     * Método para crear una lista de los estudiantes que tienen 5 en el curso.
     * Ademas imprime un mensaje con el codigo del estudiante.
     *
     * @param x lista de estudiantes en el curso
     * @return La lista de estudiantes con 5.
     */
     static @NotNull ArrayList<Estudiante> listEstudiantesCinco(@NotNull ArrayList<Estudiante> x){
        ArrayList<Estudiante> list = new ArrayList<>();

        for(Estudiante i:x){
         if (i.getNotaDefinitiva()==5.0){
             list.add(i);
             System.out.println("\nEl estudiante con codigo " + i.getCodigo()
                     + " obtuvo como definitiva la nota de: " + i.getNotaDefinitiva());}
        }
        return list;
    }

    /**
     *  Metodo para mostrar el menu
     */
     static void menu(){
        System.out.println("""
                _______________________________.::MENU::._________________________________

                1. Ingresar la cantidad de estudiantes del curso.
                2. Ingresar las notas de los estudiantes.
                3. Conocer la definitiva y si aprobaron o no el curso los estudiantes.
                4. Conocer la cantidad de estudiantes que APROBARON el curso.
                5. Conocer la cantidad de estudiantes que NO APROBARON el curso.
                6. Conocer el promedio general de las notas definitivas.
                7. Conocer los estudiantes que tuvieron 5.0 en la definitiva.
                8. Salir del menu.
                """);
        }

    /**
     * Método para verificar que la opcion escogida en el menu sea correcta.
     *
     * @return Un numero entre 1 y 8.
     */
    static int opcionMenu() {
        int n;
        // Ciclo para agregar los datos de cada estudiante a la lista
        while (true) {
            System.out.print("Porfavor elige una opcion:  ");
            if (Entrada.hasNextInt()) {
                n = Entrada.nextInt();
                // Verificación del rango del número
                if (n >= 0 && n <= 8) break;
                else System.out.println("Entrada inválida, por favor ingresa un número entero entre 1 y 8");
            } else {
                System.out.println("Entrada inválida, por favor ingresa un número entero entre 1 y 8");
                Entrada.next();
            }
        }
        return n;
    }
}
