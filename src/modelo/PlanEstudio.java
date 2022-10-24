
package modelo;

import java.util.ArrayList;

/**
 * numero de plan (4 digitos)
 * fecha de vigencia
 * serie de cursos que organizan en bloques de semestre
 * 
 */
/**
 * Clase Plan de Estudio
 * @author Andrey Salamanca, Estefani Valverde, Maverick Madrigal
 * @version (10/10/2022)
 */
public class PlanEstudio {
    private String escuelaPC;
    private int numeroPlan;
    private String fechaVigencia;
    private ArrayList<Curso> cursos;
    private String bloque;
    
    /**
     * Constructor for objects of class PlanEstudio
     * @param pEscuelaPC Escuela propietaria del plan
     * @param pNumeroPlan Codigo del plan de estudio
     * @param pFechaVigencia Vigencia del plan de estudios
     * @param pBloque  Bloque
     */
    public PlanEstudio(String pEscuelaPC, int pNumeroPlan, String pFechaVigencia,
            String pBloque) {
        escuelaPC = pEscuelaPC;
        numeroPlan = pNumeroPlan;
        fechaVigencia = pFechaVigencia;
        cursos = new ArrayList<Curso>();
        bloque = pBloque;
        
    }

    public String getEscuelaPC() {
        return escuelaPC;
    }

    public int getNumeroPlan() {
        return numeroPlan;
    }

    public String getFechaVigencia() {
        return fechaVigencia;
    }

    public ArrayList<Curso> getCodigoCurso() {
        return cursos;
    }

    public String getBloque() {
        return bloque;
    }

    public void setEscuelaPC(String pEscuelaPC) {
        escuelaPC = pEscuelaPC;
    }

    public void setNumeroPlan(int pNumeroPlan) {
        numeroPlan = pNumeroPlan;
    }

    public void setFechaVigencia(String pFechaVigencia) {
        fechaVigencia = pFechaVigencia;
    }

    public void setCodigoCurso(Curso pCurso) {
        cursos.add(pCurso);
    }

    public void setBloque(String pBloque) {
        bloque = pBloque;
    }

    
    
    
}
