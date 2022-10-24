
package controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
import modelo.PlanEstudio;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Clase Controlador XML de Plan de Estudio
 * @author Maverick Madrigal, Andrey Salamanca, Estefani Valverde
 * @version (10/20/2022
 */
public class ControladorXML_PlanEstudio {
   
    
    public ControladorXML_PlanEstudio() {
    }
    
    /**
     * Funcion que lee el XML de plan de estudio
     * @return List
     */
    public ArrayList<PlanEstudio> leerXML() {
        try {
            File archivo = new File("planesEstudio.xml");

            ArrayList<PlanEstudio> lista = new ArrayList<PlanEstudio>();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(archivo);
            document.getDocumentElement().normalize();

            NodeList listaPlanEstudios = document.getElementsByTagName("PLAN_ESTUDIO");
            
            for (int i = 0; i < listaPlanEstudios.getLength(); i++) {
                Node nodo = listaPlanEstudios.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    String escuela = (element.getElementsByTagName("escuela").item(0).getTextContent());
                    String plan = (element.getElementsByTagName("numeroPlan").item(0).getTextContent());
                    String vigencia = (element.getElementsByTagName("fechaVigencia").item(0).getTextContent());
                    String bloque = (element.getElementsByTagName("bloque").item(0).getTextContent());
                    try{
                    lista.add(new PlanEstudio(escuela, Integer.parseInt(plan), vigencia, bloque));
                    }catch(Exception e){
                        System.out.println("Numero de plan no valido");
                        return null;
                    }
                }
            }
            return lista;

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    //valida si el archivo existe o se encuentra vacio
    /**
     * Valida si exite un archivo xml, sino lo crea
     * @return 
     */
    public Document validarExistencia(){
        try{
            File archivo = new File("planesEstudio.xml");
            
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
     * Funcion que escribe en le XML
     * @param escuela Escuela propietaria del plan 
     * @param plan Codigo del plan de estudios
     * @param vigencia Vigencia del plan de estudios
     * @param codigo Codigo del curso que forma parte del plan 
     * @param bloque  Bloque 
     * 
     */
    public void escribirXML(String escuela, String plan, String vigencia, String codigo, String bloque){
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
                document = implementation.createDocument(null, "planesEstudio.xml", null);
                document.setXmlVersion("1.0");
                raiz = document.getDocumentElement();
            }
            
            Element itemNode = document.createElement("PLAN_ESTUDIO");
                
            Element pEscuela = document.createElement("escuela");
            Text escuelaData =document.createTextNode("" +  escuela);
            pEscuela.appendChild(escuelaData);
            itemNode.appendChild(pEscuela);
            
            Element pPlan = document.createElement("numeroPlan");
            Text planData =document.createTextNode(plan);
            pPlan.appendChild(planData);
            itemNode.appendChild(pPlan);
            
            Element pFechaVigencia = document.createElement("fechaVigencia");
            Text vigenciaData =document.createTextNode(vigencia);
            pFechaVigencia.appendChild(vigenciaData);
            itemNode.appendChild(pFechaVigencia);
            
            Element pCursos = document.createElement("CURSOS");
            itemNode.appendChild(pCursos);
            
            Element pBloque = document.createElement("bloque");
            Text bloqueData =document.createTextNode(bloque);
            pBloque.appendChild(bloqueData);
            itemNode.appendChild(pBloque);
            
            raiz.appendChild(itemNode);
            
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("planesEstudio.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
        } catch(Exception e) {
            System.out.println("Error en escritura de xml");
        }
    }
    
    /**
     * Funcion que agrega el curso al xml
     * @param pCurso Codigo del curso que forma parte del plan 
     * @param pCodigoPlan Codigo del plan de estudios
     */
    
    public void agregarCursos(Curso pCurso, String pCodigoPlan){
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
                document = implementation.createDocument(null, "planesEstudio.xml", null);
                document.setXmlVersion("1.0");
                raiz = document.getDocumentElement();
                JOptionPane.showMessageDialog(null, "No se puede agregar cursos...");
                return;
            }
            
            NodeList listaPlanEstudios = document.getElementsByTagName("PLAN_ESTUDIO");
            Element element = null;
            boolean encontrado = false;
            for (int i = 0; i < listaPlanEstudios.getLength(); i++) {
                Node nodo = listaPlanEstudios.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    element = (Element) nodo;
                    String codigoPlan = (element.getElementsByTagName("numeroPlan").item(0).getTextContent());
                    
                    if(pCodigoPlan.equals(codigoPlan)){
                        encontrado = true;
                        break;
                    }
                    
                }
            }
            
            if(!encontrado){
                JOptionPane.showMessageDialog(null, "El curso indicado no existe");
                return;
            }
            
            element = (Element)element.getElementsByTagName("CURSOS");
            
            try{
                NodeList listaCursos = element.getElementsByTagName("Curso");
                //validar que no se repita el curso
                for (int i = 0; i < listaCursos.getLength(); i++) {
                    Node nodo = listaCursos.item(i);

                    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                        element = (Element) nodo;
                        String codigoCurso = (element.getElementsByTagName("codigo").item(0).getTextContent());
                    
                        if(pCodigoPlan.equals(pCurso.getCodigoC())){
                            JOptionPane.showMessageDialog(null, "El curso ya se encuentra registrado");
                            return;
                        }
                    
                    }
                }
                
            }catch(Exception e){
                e.getMessage();
                return;
            }
            
            Element itemNode = document.createElement("Curso");
                
            Element pEscuela = document.createElement("escuela");
            Text escuelaData =document.createTextNode("" +  pCurso.getEscuelaPC());
            pEscuela.appendChild(escuelaData);
            itemNode.appendChild(pEscuela);
            
            Element pNombre = document.createElement("nombre");
            Text nombreData =document.createTextNode(pCurso.getNombreC());
            pNombre.appendChild(nombreData);
            itemNode.appendChild(pNombre);
            
            Element pCodigo = document.createElement("codigo");
            Text codigoData =document.createTextNode(pCurso.getCodigoC());
            pCodigo.appendChild(codigoData);
            itemNode.appendChild(pCodigo);
            
            Element pCreditos = document.createElement("creditos");
            Text creditosData =document.createTextNode(pCurso.getCreditos());
            pCreditos.appendChild(creditosData);
            itemNode.appendChild(pCreditos);
            
            Element pHoras = document.createElement("horas");
            Text horasData =document.createTextNode(pCurso.getHoras());
            pHoras.appendChild(horasData);
            itemNode.appendChild(pHoras);
            
            element.appendChild(itemNode);
            
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("planesEstudio.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(null, "Curso agregado con exito");
            
        } catch(Exception e) {
            System.out.println("Error en escritura de xml");
        }
    }
    
    /**
     * 
     * @param pCodigoPlan Codigo del plan de estudios
     * @return boolean
     */
    public boolean eliminarPlanEstudio(String pCodigoPlan){
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
                document = implementation.createDocument(null, "planesEstudio.xml", null);
                document.setXmlVersion("1.0");
                raiz = document.getDocumentElement();
            }
            
            NodeList listaPlanEstudios = document.getElementsByTagName("PLAN_ESTUDIO");
            Element element = null;
            boolean encontrado = false;
            for (int i = 0; i < listaPlanEstudios.getLength(); i++) {
                Node nodo = listaPlanEstudios.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    element = (Element) nodo;
                    String plan = (element.getElementsByTagName("numeroPlan").item(0).getTextContent());
                    if(plan.equals(pCodigoPlan)){
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
            Result result = new StreamResult(new java.io.File("planesEstudio.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            return true;
            
        } catch(Exception e) {
            System.out.println("Error en escritura de xml");
            return false;
        }
    }
    
}

