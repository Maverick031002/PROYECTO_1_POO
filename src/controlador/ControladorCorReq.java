/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.*;
import vista.AddRequisitoCorrequisito;
import vista.Menu;

/**
 *
 * @author User
 */
public class ControladorCorReq implements ActionListener {
    public AddRequisitoCorrequisito vista;
    private ArrayList<Escuela> escuelas;
    private ArrayList<PlanEstudio> planes;
    private ArrayList<Curso> cursos;
    public Menu vistaMenu;

    
    /**
     * Controlador del plan de estudios
     * @param vista vista actual
     * @param pVistaAnterior vista anterios
     * @param pListaPlanes lista de planes de estudios
     */
    public ControladorCorReq(AddRequisitoCorrequisito vista, Menu pVistaAnterior,
            ArrayList<PlanEstudio> pListaPlanes, ArrayList<Escuela> pListaEscuelas,
            ArrayList<Curso> pListaCursos) {
        this.vista = vista;
        this.planes = pListaPlanes;
        this.escuelas = pListaEscuelas;
        this.cursos = pListaCursos;
        vistaMenu = pVistaAnterior;
        
        this.vista.BttnRequisito.addActionListener(this);
        this.vista.BttnCorrequisito.addActionListener(this);
        this.vista.BttnRegresar.addActionListener(this);
        GeneraComboEscuela();
        GeneraComboCodigo();
        GeneraComboRequisito();
        GeneraComboCorrequisito();
    }
    public void GeneraComboEscuela(){
        ControladorXML_Escuela lista = new ControladorXML_Escuela();
        escuelas = lista.leerXML();
        for(Escuela escuela: escuelas){
           vista.CmbBxEscuela.addItem(escuela.getNombre());   
        }
    }
    public void GeneraComboCodigo(){
        
        ControladorXML_Escuela lista = new ControladorXML_Escuela();
        escuelas = lista.leerXML();
        for(Escuela escuela: escuelas){
           vista.CmbBxEscuela.addItem(escuela.getNombre());   
        }
    }
    public void GeneraComboRequisito(){
        
        ControladorXML_Curso listaC = new ControladorXML_Curso();
        cursos = listaC.leerXML();
        for(Curso curso: cursos){
           vista.CmbBxCodigoCurso.addItem(curso.getCodigoC());   
        } 
    }
    public void GeneraComboCorrequisito(){
        ControladorXML_Curso listaC = new ControladorXML_Curso();
        cursos = listaC.leerXML();
        for(Curso curso: cursos){
           vista.CmbBxCodigoCurso.addItem(curso.getCodigoC());   
        } 
    }
    
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Registrar requisto":
                registrarRequisito();
                break;
            case "Registrar correquisito":
                registrarCorrequisito();
                break;
            case "Regresar":
                cerrar();
                break;
            default:
                break;
        }
    }
    
    public void registrarRequisito(){
        
    }
    public void registrarCorrequisito(){
        
    }
    public void cerrar(){
        
    }
    
}
