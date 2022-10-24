
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

/**
 * Controlador de Curso
 * @author Maverick Madrigal, Andrey Salamanca, Estefani Valverde
 * @version (10/20/2022)
 */
public class ControladorCurso implements ActionListener {
    public AddCurso vista;
    private ArrayList<Curso> cursos;
    private ArrayList<Escuela> escuelas;
    public Menu vistaMenu;
    
    /**
     * Constructor del Constrolador de Curso
     * @param vista Vista Actual
     * @param pVistaAnterior Vista Anterios
     * @param pListaCursos Lista de Cursos
     * @param pListaEscuelas  Lista de Escuelas
     */
    public ControladorCurso(AddCurso vista, Menu pVistaAnterior, ArrayList<Curso> pListaCursos,
            ArrayList<Escuela> pListaEscuelas) {
        this.vista = vista;
        this.cursos = pListaCursos;
        this.escuelas = pListaEscuelas;
        vistaMenu = pVistaAnterior;
        
        this.vista.ButtonRegistrar.addActionListener(this);
        this.vista.ButtonLimpiar.addActionListener(this);
        this.vista.ButtonRegresar.addActionListener(this);
        GeneraCombo();
    }
    /**
     * Genera el combo box, lee las escuelas para poder seleccionarlas
     */
    private void GeneraCombo(){
        ControladorXML_Escuela lista = new ControladorXML_Escuela();
        escuelas = lista.leerXML();
        for(Escuela escuela: escuelas){
           vista.ComboEscuelapCurso.addItem(escuela.getNombre());   
        }
        
        
    } 
    @Override
    /**
     * Segun el evento de los botones se dirige a una nueva funcion 
     */
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Registrar":
                registrarCodigo();
                break;
            case "Limpiar campos":
                limpiarCampos();
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
    
    private void registrarCodigo() {
        
        String labelID = vista.LabelID.getText();
        System.out.println(labelID);
        String escuelaC = vista.ComboEscuelapCurso.getSelectedItem().toString();
        String codigoC = vista.TextCodigoCurso.getText();
        String nombreC = vista.txtNombreCurso.getText();
        String creditos = vista.ComboCreditos.getSelectedItem().toString();
        String horas = vista.ComboHorasLectivas.getSelectedItem().toString();
        
        if(nombreC.isEmpty() ||codigoC.isEmpty()){
            JOptionPane.showMessageDialog(null,"Por favor ingrese todos los valores");
        }
        else if(codigoC.length() > 4){
            JOptionPane.showMessageDialog(null,"Error, el codigo solo puede tener 4 digitos");
        }
        /*else if (codigoC instanceof String){
           
            JOptionPane.showMessageDialog(null,"Error, el codigo del curso debe de ser un  valor entero");
        }*/
        else{
            ControladorXML_Curso xml = new ControladorXML_Curso();
            xml.escribirXML(escuelaC, nombreC,labelID + codigoC, creditos, horas);
            JOptionPane.showMessageDialog(null, "Curso registrado con éxito");
        }
           
    }
    
    private void limpiarCampos(){
        vista.TextCodigoCurso.setText(null);
        vista.txtNombreCurso.setText(null);
    }
    private void regresar() {
        vista.setVisible(false);
        vistaMenu.setVisible(true);
    }
    
            

    
}
