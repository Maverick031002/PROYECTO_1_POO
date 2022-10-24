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
import modelo.Curso;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Controlador XML
 * @author Maverick Madrigal, Andrey Salamanca, Estefani Valverde
 * @version (10/22/2022)
 */
public class ControladorXML_Curso {
    
    public ControladorXML_Curso() {
    }
    
    public ArrayList<Curso> leerXML() {
        try {
            File archivo = new File("cursos.xml");

            ArrayList<Curso> lista = new ArrayList<Curso>();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(archivo);
            document.getDocumentElement().normalize();

            NodeList listaCursos = document.getElementsByTagName("CURSO");
            
            for (int i = 0; i < listaCursos.getLength(); i++) {
                Node nodo = listaCursos.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    String escuela = (element.getElementsByTagName("escuela").item(0).getTextContent());
                    String nombre = (element.getElementsByTagName("nombre").item(0).getTextContent());
                    String codigo = (element.getElementsByTagName("codigo").item(0).getTextContent());
                    String creditos = (element.getElementsByTagName("horas").item(0).getTextContent());
                    String horas = (element.getElementsByTagName("creditos").item(0).getTextContent());
                    
                    lista.add(new Curso(escuela, nombre, codigo, creditos, horas));
                }
            }
            return lista;

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    //valida si el archivo existe o se encuentra vacio
    public Document validarExistencia(){
        try{
            File archivo = new File("cursos.xml");
            
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
     * Funcion que escribe en el un archivo xml
     * @param escuela Escuela propietaria del curso
     * @param nombre Nombre del curso
     * @param codigo Codigo del curso
     * @param creditos Creditos 
     * @param horas  Horas lectivas
     */
    public void escribirXML(String escuela, String nombre, String codigo, String creditos, String horas){
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
                document = implementation.createDocument(null, "cursos.xml", null);
                document.setXmlVersion("1.0");
                raiz = document.getDocumentElement();
            }
            
            Element itemNode = document.createElement("CURSO");
                
            Element pEscuela = document.createElement("escuela");
            Text escuelaData =document.createTextNode("" +  escuela);
            pEscuela.appendChild(escuelaData);
            itemNode.appendChild(pEscuela);
            
            Element pNombre = document.createElement("nombre");
            Text nombreData =document.createTextNode(nombre);
            pNombre.appendChild(nombreData);
            itemNode.appendChild(pNombre);
            
            Element pCodigo = document.createElement("codigo");
            Text codigoData =document.createTextNode(codigo);
            pCodigo.appendChild(codigoData);
            itemNode.appendChild(pCodigo);
            
            Element pCreditos = document.createElement("creditos");
            Text creditosData =document.createTextNode(creditos);
            pCreditos.appendChild(creditosData);
            itemNode.appendChild(pCreditos);
            
            Element pHoras = document.createElement("horas");
            Text horasData =document.createTextNode(horas);
            pHoras.appendChild(horasData);
            itemNode.appendChild(pHoras);
            
            raiz.appendChild(itemNode);
            
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("cursos.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
        } catch(Exception e) {
            System.out.println("Error en escritura de xml");
        }
    }
    
    /**
     * Funcion que elimina un curso
     * @param pCodigoCurso
     * @return boolean 
     */
    public boolean eliminarCurso(String pCodigoCurso){
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
                document = implementation.createDocument(null, "cursos.xml", null);
                document.setXmlVersion("1.0");
                raiz = document.getDocumentElement();
            }
            
            NodeList listaCursos = document.getElementsByTagName("CURSO");
            Element element = null;
            boolean encontrado = false;
            for (int i = 0; i < listaCursos.getLength(); i++) {
                Node nodo = listaCursos.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    element = (Element) nodo;
                    String codigo = (element.getElementsByTagName("codigo").item(0).getTextContent());
                    if(codigo.equals(pCodigoCurso)){
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
            Result result = new StreamResult(new java.io.File("cursos.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            return true;
            
        } catch(Exception e) {
            System.out.println("Error en escritura de xml");
            return false;
        }
    }
    
}
