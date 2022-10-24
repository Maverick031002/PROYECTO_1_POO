/*
 * cursos:
 * nombre
 * codigo (2 caracteres, 4 numeros)
 * cantidad creditos ( 0 - 4)
 * cantidad de horas lectivas (1-5)
 * asociación con los cursos que son requisitos y correquisitos package proyect1;
 */
package modelo;

/**
 * Clase curso
 * @author Andrey Salamanca, Estefani Valverde, Maverick Madrigal
 * @version (10/10/2022)
 */
public class Curso {
    private String escuelaPC;
    private String nombreC;
    private String codigoC;
    private String creditos;
    private String horas;
    
    /**
     * Constructor for objects of class Curso
     * @param pEscuelaPC Escuela propietaria del curso
     * @param pNombreC Nombre del curso
     * @param pCodigoC Codigo del curso 
     * @param pCreditos Creditos
     * @param pHoras  Holas Lectivas
     */

    public Curso(String pEscuelaPC, String pNombreC, String pCodigoC, String pCreditos, String pHoras) {
        escuelaPC = pEscuelaPC;
        nombreC = pNombreC;
        codigoC = pCodigoC;
        creditos = pCreditos;
        horas = pHoras;
    }
    
    /**
     * 
     * @return Metodos Accesorios
     */
    public String getEscuelaPC() {
        return escuelaPC;
    }

    public String getNombreC() {
        return nombreC;
    }

    public String getCodigoC() {
        return codigoC;
    }

    public String getCreditos() {
        return creditos;
    }

    public String getHoras() {
        return horas;
    }

    public void setEscuelaPC(String escuelaPC) {
        this.escuelaPC = escuelaPC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public void setCodigoC(String codigoC) {
        this.codigoC = codigoC;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }
     
    
    
    
}
