

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.eclipse.jdt.internal.compiler.ast.Javadoc;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author northpole
 */
public class XmlCreator {

    
    private ArrayList names;
    private String xml;
    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private LinkedList<Element> elements;
    private Element rootEl;
    private Document doc;
    private HashMap els;
    
    public XmlCreator(){
        els=new HashMap();
        names=new ArrayList();
     elements=new LinkedList();
      docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc=(Document) docBuilder.newDocument();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public void addRootElement(String name){
        this.rootEl=this.doc.createElement(name);
        this.doc.appendChild(this.rootEl);
         }
    
    public void addNormalElement(String name){
        
        Element tmp=doc.createElement(name);
        elements.add(tmp);
        names.add(name);
        rootEl.appendChild(tmp);
    }
    
    public void addChildElement(String name,String parentElement){
        Element tmp=doc.createElement(name);
       elements.add(tmp);
        names.add(name);
        elements.get(names.indexOf(name)).appendChild(tmp);
        }
    
    /*Does not support multiple elemetns with same name
     @returns:nothing*/
    public void setAttributeToElement(String elName,String attrName,String attrType,Object attribute){
    int i=names.indexOf(elName);
    elements.get(i).setAttribute(attrName, attribute.toString());
    }
    
    
    /*@arguments NodesText,ParentElement*/
    public void addTextNode(String text,String parentElement){
    
        elements.get(names.indexOf(parentElement)).appendChild(doc.createTextNode(text));
    }
    
    @Override
    public String toString(){
        
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(this.xml);
            
            try {
                transformer.transform(source, result);
            } catch (TransformerException ex) {
                Logger.getLogger(XmlCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XmlCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return xml;}

}

