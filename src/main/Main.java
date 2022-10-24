package main;

import controlador.*;
import vista.*;
/**
 * Clase main capaz de correr las ventana principales
 * @author Maverick Madrigal, Estefani Valverde, Andrey Salamanca 
 * 
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Iniciando Gestor de Planes de Estudio");
        Menu ventana = new Menu();
        ControladorMenu ctrl = new ControladorMenu(ventana);
        ctrl.vistaMenu.setVisible(true);
        ctrl.vistaMenu.setLocationRelativeTo(null);
    }
}
