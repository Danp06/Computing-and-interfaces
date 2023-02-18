package org.RetoProgramacion;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


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
            System.out.print("Ingresa un número entero entre 1 y 10: ");
            if (Entrada.hasNextInt()) {
                n = Entrada.nextInt();
                // Verificación del rango del número
                if (n > 0 && n <= 30) break;
                else System.out.println("Entrada inválida, por favor ingresa un número entero entre 1 y 30");
            } else {
                System.out.println("Entrada inválida, por favor ingresa un número entero entre 1 y 30");
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
     static Double verificarNotas(Double n) {
         // Ciclo infinito hasta que se ingrese un número válido
         // Verificación del rango del número
         if (n <= 0.0 && n >= 5.0)
             System.out.println("Entrada inválida, Revisa el archivo excel e ingresa un número entre 0.0 y 5.0");
        return n;
    }

    /**
     * Método para crear una lista de estudiantes.
     *
     * @param n El número de estudiantes que se deben agregar a la lista.
     * @return La lista de estudiantes.
     */
     static ArrayList<Estudiante> listEstudiantes(int n) {
        ArrayList<Estudiante> list = new ArrayList<>();
        ArrayList<Double> notas;
         if (n == 0) {
             System.out.println("ERROR!!! Por favor ingresa primeros la cantidad estudiantes antes de elegir esta opcion.");
             return null;
         }
        String codigo;
        double notaP, notaQ, notaT, notaD;
        boolean aprobo;
         // Ciclo para agregar los datos de cada estudiante a la lista

        for (int i = 0; i < n; i++) {
            codigo = Excel.leerCodigoExcel(i);
            notas = Excel.leerNotasExcel(i);
            notaP = verificarNotas(notas.get(0));
            notaQ = verificarNotas(notas.get(1));
            notaT = verificarNotas(notas.get(2));
            notaD = Double.parseDouble(String.format("%.3f", (notaP * 0.5 + notaQ * 0.3 + notaT * 0.2)));
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
     static int cantidadAprobados(ArrayList<Estudiante> x){
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
     static double promedioDefinitiva(ArrayList<Estudiante> x, int n){
        double prom = 0;
        for (Estudiante i:x) prom += i.getNotaDefinitiva();
        return prom / n;
    }

    static double desviacionDefinitiva(ArrayList<Estudiante> x){
        ArrayList<Double> notas = new ArrayList<Double>();
        for (Estudiante i:x){
            notas.add(i.getNotaDefinitiva());
        }
        // Calculo de la media
        double media = notas.stream()
                .collect(Collectors.averagingDouble(Double::doubleValue));

        // Calculo de la suma de los cuadrados de las diferencias entre los datos y la media
        double sumOfSquares = notas.stream()
                .mapToDouble(num -> Math.pow(num - media, 2))
                .sum();

        // Calculo de la desviación estándar
        double desviacionEstandar = Math.sqrt(sumOfSquares / (notas.size() - 1));

        System.out.println("Desviación estándar: " + desviacionEstandar);

        return desviacionEstandar;
    }

    static void ListaMaxMin(ArrayList<Estudiante> x) {
        List<Double> notasMax = Arrays.asList(0.0, 0.0, 0.0);
        List<Double> notasMin = Arrays.asList(5.0,5.0,5.0);
        List<String> codigoMax = Arrays.asList("", "", "");
        List<String> codigoMin = Arrays.asList("", "", "");

        for (Estudiante e : x) {
            double nota1 = e.getNotaParcial();
            double nota2 = e.getNotaQuices();
            double nota3 = e.getNotaTalleres();

            // Buscar notas máximas y los estudiantes que las tienen
            if (nota1 > notasMax.get(0)) {
                notasMax.set(0, nota1);
                codigoMax.set(0, e.getCodigo());
            }
            if (nota2 > notasMax.get(1)) {
                notasMax.set(1, nota2);
                codigoMax.set(1, e.getCodigo());
            }
            if (nota3 > notasMax.get(2)) {
                notasMax.set(2, nota3);
                codigoMax.set(2, e.getCodigo());
            }

            // Buscar notas mínimas y los estudiantes que las tienen
            if (nota1 < notasMin.get(0)) {
                notasMin.set(0, nota1);
                codigoMin.set(0, e.getCodigo());
            }
            if (nota2 < notasMin.get(1)) {
                notasMin.set(1, nota2);
                codigoMin.set(1, e.getCodigo());
            }
            if (nota3 < notasMin.get(2)) {
                notasMin.set(2, nota3);
                codigoMin.set(2, e.getCodigo());
            }
        }

        System.out.println("Notas máximas:");
        System.out.println("Nota Parcial: " + notasMax.get(0) + " - Estudiantes: " + codigoMax.get(0));
        System.out.println("Nota Quices: " + notasMax.get(1) + " - Estudiantes: " + codigoMax.get(1));
        System.out.println("Nota Talles: " + notasMax.get(2) + " - Estudiantes: " + codigoMax.get(2));

        System.out.println("Notas mínimas:");
        System.out.println("Nota Parcial: " + notasMin.get(0) + " - Estudiantes: " + codigoMin.get(0));
        System.out.println("Nota Quices: " + notasMin.get(1) + " - Estudiantes: " + codigoMin.get(1));
        System.out.println("Nota Talleres: " + notasMin.get(2) + " - Estudiantes: " + codigoMin.get(2));
    }

    /**
     * Método para crear una lista de los estudiantes que tienen 5 en el curso.
     * Ademas imprime un mensaje con el codigo del estudiante.
     *
     * @param x lista de estudiantes en el curso
     */
    static void listEstudiantesCinco(ArrayList<Estudiante> x) {
        if (x == null) {
            System.out.println("ERROR!!! Por favor ingresa primeros la cantidad estudiantes " +
                    "antes de elegir esta opcion.");
        } else {
            boolean found = false;
            for (Estudiante i : x) {
                if (i.getNotaDefinitiva() == 5.0) {
                    System.out.println("\nEl estudiante con codigo " + i.getCodigo()
                            + " obtuvo como definitiva la nota de: " + i.getNotaDefinitiva());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Nadie en el curso obtuvo un 5.0 como definitiva");
            }
        }
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
                8. Conocer la desviacion estandar de la definitiva
                9. Lista ordenada de mayor a menor por promedio
                10. Consultar la maxima y minima nota de cada item
                11. Salir del menu.
                """);
        }

    /**
     * Método para verificar que la opcion escogida en el menu sea correcta.
     *
     * @return Un numero entre 1 y 9.
     */
    static int opcionMenu() {
        int n;
        // Ciclo para agregar los datos de cada estudiante a la lista
        while (true) {
            System.out.print("Porfavor elige una opcion:  ");
            if (Entrada.hasNextInt()) {
                n = Entrada.nextInt();
                // Verificación del rango del número
                if (n >= 0 && n <= 11) break;
                else System.out.println("Entrada inválida, por favor ingresa un número entero entre 1 y 11");
            } else {
                System.out.println("Entrada inválida, por favor ingresa un número entero entre 1 y 11");
                Entrada.next();
            }
        }
        return n;
    }
}
