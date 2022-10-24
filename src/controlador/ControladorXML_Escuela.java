/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.Escuela;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author Maverick Madrigal, Estefani Valverde, Andrey Salamanca
 * @version 22/10/2022
 */
public class ControladorXML_Escuela {
    
    public ControladorXML_Escuela() {
    }

    /**
     * Descripcion lee lo que hay dentro de un xml
     * @return un arraylist
     */
    public ArrayList<Escuela> leerXML() {
        try {
            File archivo = new File("escuelas.xml");

            ArrayList<Escuela> lista = new ArrayList<Escuela>();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(archivo);
            document.getDocumentElement().normalize();

            NodeList listaEscuelas = document.getElementsByTagName("ESCUELA");
            
            for (int i = 0; i < listaEscuelas.getLength(); i++) {
                Node nodo = listaEscuelas.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    String nombre = (element.getElementsByTagName("nombre").item(0).getTextContent());
                    String codigo = (element.getElementsByTagName("codigo").item(0).getTextContent());
                    
                    lista.add(new Escuela(nombre, codigo));
                }
            }
            return lista;

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    //valida si el archivo existe o se encuentra vacio
    /**
     * Descripcion:Funcion que valida la existencia de atos guardados
     * @return un documento
     */
    public Document validarExistencia(){
        try{
            File archivo = new File("escuelas.xml");
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            
            document.getDocumentElement().normalize();
            return document;
        }catch(Exception e){
            return null;
        }
    }
    /**
     * Descripcion: Funcion que escribe los datos en un xml
     * @param nombre Escuela qie se regsitrara en el plan de estudio
     * @param codigo Codigo de 2 caracteres que identificara la escuela
     */
    public void escribirXML(String nombre, String codigo){
        try {
            Element raiz = null;
            Document document = validarExistencia();
            if(document != null){
                raiz = document.getDocumentElement();
            }else{
                //si el archivo no existe o esta en blanco, se construye desde 0
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                DOMImplementation implementation = builder.getDOMImplementation();
                document = implementation.createDocument(null, "escuelas.xml", null);
                document.setXmlVersion("1.0");
                raiz = document.getDocumentElement();
            }
            
            Element itemNode = document.createElement("ESCUELA");
                
            Element pNombre = document.createElement("nombre");
            Text nombreData =document.createTextNode("" +  nombre);
            pNombre.appendChild(nombreData);
            itemNode.appendChild(pNombre);
            
            Element pCodigo = document.createElement("codigo");
            Text codigoData =document.createTextNode(codigo);
            pCodigo.appendChild(codigoData);
            itemNode.appendChild(pCodigo);
            
            raiz.appendChild(itemNode);
            
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("escuelas.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
        } catch(Exception e) {
            System.out.println("Error en escritura de xml");
        }
    }
    
    /**
     * Descripcion: Funcion que elimina una escuela
     * @param pNombre nombre de la escuela 
     * @return un booleano
     */
    
    public boolean eliminarEscuela(String pNombre){
        try {
            Element raiz = null;
            Document document = validarExistencia();
            if(document != null){
                raiz = document.getDocumentElement();
            }else{
                //si el archivo no existe o esta en blanco, se construye desde 0
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                DOMImplementation implementation = builder.getDOMImplementation();
                document = implementation.createDocument(null, "escuelas.xml", null);
                document.setXmlVersion("1.0");
                raiz = document.getDocumentElement();
            }
            
            NodeList listaEscuelas = document.getElementsByTagName("ESCUELA");
            Element element = null;
            boolean encontrado = false;
            for (int i = 0; i < listaEscuelas.getLength(); i++) {
                Node nodo = listaEscuelas.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    element = (Element) nodo;
                    String nombre = (element.getElementsByTagName("nombre").item(0).getTextContent());
                    if(nombre.equals(pNombre)){
                        encontrado = true;
                        break;
                    }
                }
            }
            if(!encontrado){
                return false;
            }
            
            Node borrado = raiz.removeChild(element);
            if(borrado == null){
                return false;
            }
            
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("escuelas.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            return true;
            
        } catch(Exception e) {
            System.out.println("Error en escritura de xml");
            return false;
        }
    }
    
}
