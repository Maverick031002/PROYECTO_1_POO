
package controlador;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import vista.*;
import modelo.*;


/**
 * Controlador de menu que se encarga de lee los botones para ir a nueva ventana
 * @author Andrey Salamanca Campos, Manerick Madrigal, Estefani Valverde
 * @version (10/18/2022)
 */
public class ControladorMenu implements ActionListener {
    public Menu vistaMenu;
    public ArrayList<Escuela> escuelas;
    public ArrayList<Curso> cursos;
    public ArrayList<PlanEstudio> planes;
     
    
    /**
     * Controlador de menu, segun los botones se dirige a una nueva ventana
     * @param ventana 
     */
    public ControladorMenu(Menu ventana){
        vistaMenu = ventana;
        escuelas = new ArrayList<Escuela>();
        cursos = new ArrayList<Curso>();
        planes = new ArrayList<PlanEstudio>();
        
        
        
        

        this.vistaMenu.ButtonRegistroEscuela.addActionListener(this);
        this.vistaMenu.ButtonRegistroCurso.addActionListener(this);
        this.vistaMenu.ButtonRegistroPlanesEstudio.addActionListener(this);
        this.vistaMenu.ButtonAsignarReCorrequisitos.addActionListener(this);
        
        this.vistaMenu.ButtonCosultasAdicionales.addActionListener(this);
        this.vistaMenu.ButtonModificarInfo.addActionListener(this);
        this.vistaMenu.ButtonCosultasAdicionales.addActionListener(this);
        this.vistaMenu.ButtonGenerarEstadistica.addActionListener(this);
        
        this.vistaMenu.ButtonSalir.addActionListener(this);

    }

    @Override
    /**
     * Lee los botons y dirige a una funcion 
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Registro de Escuela o Área Académica":
                iniciarVentanaRegistrarEscuela();
                break;
            case "Registro de Curso":
                iniciarVentanaRegistrarCurso();
                break;
            case "Registrar Planes de Estudio":
                iniciarVentanaRegistrarPlanEstudio();
                break;
            case "Asignar requisitos y correquisitos a un curso":
                inicarVentanaAsignarRequisitosCorrequisitos();
                break;
            case "Consular Plan de Estudio":
                //inicarVentanaConsutaPlanEstudio();
                break;
            case "Modificar Informacion":
                iniciarVentanaModificarInformacion();
                break;
            case "Consultas Adicionales":
                //iniciarVentanaConsultasAdicionales();
                break;
            case "Generar Estadisticas":
                iniciaVentanaGenerarEstadistica();
            case "Salir":
                System.exit(0);
                break;
            default:
                break;
        }
    }
    
    /**
     * Abre la ventana de Registrar Escuela o Centro Academico
     */
    public void iniciarVentanaRegistrarEscuela() {
        vistaMenu.setVisible(false);
        AddEscuela ventanaAddEscuela = new AddEscuela();
        ControladorEscuela ctrlAddEscuela = new ControladorEscuela(ventanaAddEscuela,vistaMenu, escuelas);
        ctrlAddEscuela.vista.setVisible(true);
        ctrlAddEscuela.vista.setLocationRelativeTo(null);
    }
    
    /**
     * Abre ventana de Registrar un curso
     */
    public void iniciarVentanaRegistrarCurso() {
        // si no hay escuelas registradas no puede registrar un curso
        ControladorXML_Escuela xml = new ControladorXML_Escuela();
        escuelas = xml.leerXML();
        if(escuelas == null){
            JOptionPane.showMessageDialog(null,"Error, no hay escuelas registradas");
        }
        else{
            vistaMenu.setVisible(false);
            AddCurso ventanaAddCurso = new AddCurso();
            ControladorCurso ctrlAddCurso = new ControladorCurso(ventanaAddCurso,vistaMenu ,cursos ,escuelas);
            ctrlAddCurso.vista.setVisible(true);
            ctrlAddCurso.vista.setLocationRelativeTo(null);
        }
    }
    
    /**
     * Abre ventana de Registrar un plan de estudios
     */
    public void iniciarVentanaRegistrarPlanEstudio() {
        // si no hay escuelas registradas no puede registrar un plan  de estudio
        ControladorXML_Escuela xml = new ControladorXML_Escuela();
        escuelas = xml.leerXML();
        if(escuelas == null){
            JOptionPane.showMessageDialog(null,"Error, no hay escuelas registradas");
        }
        else{
            vistaMenu.setVisible(false);
            AddPlanEstudio ventanaAddPlanEstudio = new AddPlanEstudio();
            ControladorPlanEstudio ctrlAddPlanEstudio = new ControladorPlanEstudio(ventanaAddPlanEstudio
                    ,vistaMenu ,planes,escuelas);
            ctrlAddPlanEstudio.vista.setVisible(true);
            ctrlAddPlanEstudio.vista.setLocationRelativeTo(null);
        }
    }
    
    public void inicarVentanaAsignarRequisitosCorrequisitos() {
        // si no hay escuelas registradas no puede registrar un plan  de estudio
        ControladorXML_Curso xml = new ControladorXML_Curso();
        cursos = xml.leerXML();
        if(cursos == null){
            JOptionPane.showMessageDialog(null,"Error, no hay cursos registradas");
        }
        else{
            vistaMenu.setVisible(false);
            AddRequisitoCorrequisito ventanaAddRequisitoCorrequisito = new AddRequisitoCorrequisito();
            ControladorCorReq ctrlAddReCorrequisito = new ControladorCorReq(ventanaAddRequisitoCorrequisito,
                    vistaMenu ,planes,escuelas,cursos);
            ctrlAddReCorrequisito.vista.setVisible(true);
            ctrlAddReCorrequisito.vista.setLocationRelativeTo(null);
        }
    }
    
    public void iniciarVentanaModificarInformacion(){
        vistaMenu.setVisible(false);
        ModificarInformacion ventanaModificarInfo = new ModificarInformacion();
        ventanaModificarInfo.setVisible(true);
    } 
    public void iniciaVentanaGenerarEstadistica(){
        vistaMenu.setVisible(false);
        GenerarEstadisticas ventanaEstadistica = new GenerarEstadisticas();
        ventanaEstadistica.setVisible(true);  
    }
}
