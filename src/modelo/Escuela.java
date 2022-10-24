/*
* Cada escuela o area academica tiene:
 * nombre
 * codigo (2 caracteres) EJ: IC, TC
 */
package modelo;

/**
 * Clase curso
 * @author Andrey Salamanca, Estefani Valverde, Maverick Madrigal
 * @version (10/10/2022)
 */
public class Escuela {
    private String nombre;
    private String codigo;
    
    
    /**
     * Constructor for objects of class Escuela
     * @param pNombre Nombre
     * @param pCodigo Codigo
     */
    public Escuela(String pNombre, String pCodigo) {
        nombre = pNombre;
        codigo = pCodigo;
    }
    /**
     * 
     * @return Metodos Accesorios
     */
    
    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    public void setCodigo(String pCodigo) {
        codigo = pCodigo;
    }
    
    
    
}
