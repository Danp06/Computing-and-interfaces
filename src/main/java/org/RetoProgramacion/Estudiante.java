package org.RetoProgramacion;

/**
 *  La clase Estudiante representa a un estudiante con sus notas y el estado en que se encuentra
 */
public class Estudiante {
    /**
     * Tiene como parametro Un String que es el Codigo, Variables Double que representas la nota del Parcial,
     * Quices, Talleres y Definitiva. Adicionalmente tiene una Variable Boolean que nos dice el estado que se
     * encuentra en el curso
     */
    public String Codigo;
    public double notaParcial, notaQuices, notaTalleres, notaDefinitiva;
    public boolean Aprobo;

    /**
     *
     * Constructor que crea un nuevo estudiante con los atributos mencionados anterior mente
     * @param codigo
     * @param notaParcial
     * @param notaQuices
     * @param notaTalleres
     * @param notaDefinitiva
     * @param aprobo
     */
    public Estudiante(String codigo, double notaParcial, double notaQuices, double notaTalleres,
                      double notaDefinitiva, boolean aprobo) {
        Codigo = codigo;
        this.notaParcial = notaParcial;
        this.notaQuices = notaQuices;
        this.notaTalleres = notaTalleres;
        this.notaDefinitiva = notaDefinitiva;
        Aprobo = aprobo;
    }

    /**
     * Getter del Codigo
     * @return el codigo del estudiante
     */
    public String getCodigo() {
        return Codigo;
    }

    /**
     * Getter de la nota del Parcial
     * @return la nota del parcial del estudiante
     */
    public double getNotaParcial() {
        return notaParcial;
    }

    /**
     * Getter de la nota de los Quices
     * @return la nota de los quices del estudiante
     */
    public double getNotaQuices() {
        return notaQuices;
    }

    /**
     * Getter de la nota de los talleres
     * @return la nota de los talleres del estudiante
     */
    public double getNotaTalleres() {
        return notaTalleres;
    }

    /**
     * Getter de la nota definitiva
     * @return la nota definitiva del estudiante
     */
    public double getNotaDefinitiva() {
        return notaDefinitiva;
    }

    /**
     * Funciion IsAprobo
     * @return El estado True or False que significa si aprobo o no el curso
     */
    public boolean isAprobo() {
        return Aprobo;
    }

    /**
     * Funcion que retorna un mensaje con el Codigo del estudiante, La definitiva que obtuvio del curso
     * y si aprobo o no el curso.
     */
    public void mensajeAprobado() {
        if (this.Aprobo){
            System.out.println("\nEl estudiante con codigo " + this.Codigo
                    + " obtuvo como definitiva la nota de: " + this.notaDefinitiva
                    + "\nPor lo que APROBO la materia\n" );
        }else{
            System.out.println("El estudiante con codigo " + this.Codigo
                    + " obtuvo como definitiva la nota de: " + this.notaDefinitiva
                    + "\nPor lo que NO APROBO la materia\n" );
        }
    }
}