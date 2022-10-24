/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.*;
import vista.ModificarInformacion;
import vista.Menu;

/**
 * Controlador de Curso
 * @author Maverick Madrigal, Andrey Salamanca, Estefani Valverde
 * @version (10/20/2022)
 */
   
public class ControladorModificarInfo implements ActionListener {
    public ModificarInformacion vista;
    private ArrayList<Curso> cursos;

    public Menu vistaMenu;
    
    /**
     * Constructor del Constrolador de Curso
     * @param vista Vista Actual
     * @param pVistaAnterior Vista Anterios
     * @param pListaCursos Lista de Cursos
     * @param pListaEscuelas  Lista de Escuelas
     */
    public ControladorModificarInfo(ModificarInformacion vista, Menu pVistaAnterior, ArrayList<Curso> pListaCursos) {
        this.vista = vista;
        this.cursos = pListaCursos;
  
        vistaMenu = pVistaAnterior;
        
        this.vista.BttnDeleteRequisito.addActionListener(this);
        this.vista.BttnDeleteCursoPE.addActionListener(this);
        this.vista.BttnDeleteCurso.addActionListener(this);
        
    }
    
    @Override
    /**
     * Segun el evento de los botones se dirige a una nueva funcion 
     */
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Eliminar el requisito de un curso":
                //eliminarRequisito(); PENDITENTE ELIMINAR ESCUELA SE PUEDE BORRAR
                break;
            case "Eliminar un curso de un plan de estudios":
                eliminarCursoPlanEst();
                break;
            case "Eliminar un curso ":
                eliminarCurso();
                break;
            case "Regresar":
                regresar();
                break;
            default:
                break;
        }
    }
    /**
     * Registra el codigo del curso, leyendo lo opcion en los labels
     */
    
    private void eliminarCurso() {
        
        String codigoC = vista.txtCodigo.getText();

        if(codigoC.isEmpty()){
            JOptionPane.showMessageDialog(null,"Por favor ingrese todos los valores");
        }
       
        else{
            ControladorXML_Curso xmlcurso = new ControladorXML_Curso();
            xmlcurso.eliminarCurso(codigoC);
            JOptionPane.showMessageDialog(null, "Curso eliminado con exito");
        }    
    }
    
    private void eliminarCursoPlanEst() {
        
        String codigoC = vista.txtCodigo.getText();

        if(codigoC.isEmpty()){
            JOptionPane.showMessageDialog(null,"Por favor ingrese todos los valores");
        }
       
        else{
            ControladorXML_PlanEstudio xmlPlan = new ControladorXML_PlanEstudio();
            xmlPlan.eliminarPlanEstudio(codigoC);
            JOptionPane.showMessageDialog(null, "Plan de estudio eliminado con exito");
        }    
    }
 
    private void regresar() {
        vista.setVisible(false);
        vistaMenu.setVisible(true);
    }
    
}
