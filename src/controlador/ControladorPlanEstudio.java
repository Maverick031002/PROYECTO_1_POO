
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

/**
 *
 * @author Maverick Madrigal, Estefani Valverde, Andrey Salamanca
 * @version 22-10-2022

 */

public class ControladorPlanEstudio implements ActionListener{
    public AddPlanEstudio vista;
    private ArrayList<PlanEstudio> planes;
    private ArrayList<Escuela> escuelas;
    public Menu vistaMenu;

    
    /**
     * Controlador del plan de estudios
     * @param vista
     * @param pVistaAnterior
     * @param pListaPlanes
     * @param pListaEscuelas 
     */
    public ControladorPlanEstudio(AddPlanEstudio vista, Menu pVistaAnterior, ArrayList<PlanEstudio> pListaPlanes,
            ArrayList<Escuela> pListaEscuelas) {
        this.vista = vista;
        this.planes = pListaPlanes;
        this.escuelas = pListaEscuelas;
        vistaMenu = pVistaAnterior;
        
        this.vista.ButtonRegresar.addActionListener(this);
        this.vista.ButtonRegistrarCPE.addActionListener(this);
        GeneraCombo();
    }
    
    /**
     * Lee las escuelas para agregarlas al combobox
     */
    private void GeneraCombo(){
        
        ControladorXML_Escuela xml = new ControladorXML_Escuela();
        escuelas = xml.leerXML();
        for(Escuela escuela: escuelas){
            vista.ComboEscuelapPlan.addItem(escuela.getNombre());   
        }
        
    } 
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Registrar curso al plan de estudios":
                registrarPlanEstudio();
                break;

            case "Regresar":
                regresar();
                break;
            default:
                break;
        }
    }
    /**
     * Funcion que registra una escuela en un archivo xml
     */
    private void registrarPlanEstudio() {
        
     
        String escuelaPC = vista.ComboEscuelapPlan.getSelectedItem().toString();
        String numeroPE = vista.TextCodigoPlanE.getText();
        String vigenciaPC = vista.TextVigenciaPlanE.getText();
        String codigoCurso = vista.TextCodigoCurso.getText();
        String bloque = vista.ComboBloque.getSelectedItem().toString();
        
        if(numeroPE.isEmpty() ||vigenciaPC.isEmpty()||codigoCurso.isEmpty()){
            JOptionPane.showMessageDialog(null,"Por favor ingrese todos los valores");
        }
        else if(codigoCurso.length() > 6){
            JOptionPane.showMessageDialog(null,"Error, el codigo debe tener 2 letras y 4 números");
        }
        /*else if (codigoC instanceof String){
           
            JOptionPane.showMessageDialog(null,"Error, el codigo del curso debe de ser un  valor entero");
        }*/
        else{
            int numeroTemp = Integer.parseInt(numeroPE);
            
            
            
            ControladorXML_PlanEstudio xml = new ControladorXML_PlanEstudio();
            xml.escribirXML(escuelaPC, bloque, vigenciaPC, codigoCurso, bloque);
            //agregado del curso
            ControladorXML_Curso c = new ControladorXML_Curso();
            ArrayList<Curso> cursos = c.leerXML();
            for(Curso curso: cursos){
                if(curso.getCodigoC().equals(codigoCurso)){
                    xml.agregarCursos(curso, numeroPE);
                }
            }
            JOptionPane.showMessageDialog(null, "Plan de Estudio registradO con éxito");
        }
           
    }
    
    /**
     * Funcion que hace que devuelve una ventana
     */
    
    private void regresar() {
        vista.setVisible(false);
        vistaMenu.setVisible(true);
    }
    
    
}
