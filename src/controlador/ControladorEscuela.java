
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Escuela;
//import vista.AddEscuela;
import vista.*;
/**
 *
 * @author Maverick Madrigal, Estefani Valverde, Andrey Salamanca
 * @version 20-10-2022
 */
public class ControladorEscuela implements ActionListener{
    public AddEscuela vista;
    private ArrayList<Escuela> escuelas ;
    public Menu vistaMenu;

    /**
     * Controlador de escuela 
     * @param vista
     * @param pVistaAnterior
     * @param pListaEscuelas 
     */
    public ControladorEscuela(AddEscuela vista, Menu pVistaAnterior,ArrayList<Escuela> pListaEscuelas){
        this.vista= vista;
        this.vistaMenu = pVistaAnterior;
        this.escuelas = pListaEscuelas;
        //this.model = model;
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnLimpiarCampos.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Registrar":
                registrarEscuela();
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
     * Funcion que registra una escuela
     */
    private void registrarEscuela() {
        
        /*for (Escuela escuela : escuelas) {
            if(escuela.getNombre().equals(vista.txtNombre.getText())){
                JOptionPane.showMessageDialog(null, "Ya existe una escuela con ese nombre");
                return;
            }
        }*/
        String codigo = vista.txtCodigo.getText();
        String nombre = vista.txtNombre.getText();
        if(nombre.isEmpty() ||codigo.isEmpty()){
            JOptionPane.showMessageDialog(null,"Por favor ingrese todos los valores");
        }
        else if(codigo.length() > 2){
            JOptionPane.showMessageDialog(null,"Error, el codigo solo puede tener 2 digitos");
        }
        else if (esMinuscula(codigo)){
           
            JOptionPane.showMessageDialog(null,"Error, el codigo solo puede ser en mayuscula");
        }
        else{
            ControladorXML_Escuela xml = new ControladorXML_Escuela();
            xml.escribirXML(nombre, codigo);
            JOptionPane.showMessageDialog(null, "Escuela registrada con éxito");
        
        }
            
        
    }
       
    /**
     * Funcion que limopia los campos de un espacio de texto
     */
    private void limpiarCampos(){
        vista.txtNombre.setText(null);
        vista.txtCodigo.setText(null);
    }
    /**
     * Funcion que devuelve una ventana
     */
    private void regresar() {
        vista.setVisible(false);
        vistaMenu.setVisible(true);
        
    }
    /**
     * Funcion que valida si los carcateres es minuscula
     * @param valor
     * @return 
     */
    public static boolean esMinuscula(String valor){
        return valor.equals(valor.toLowerCase());
    }
}
