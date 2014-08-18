/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

/**
 *
 * @author Abhishek
 */

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
 
public class ReadXMLFile {

    
   
 
  public ArrayList readXMLFile(String path){
 
      Node nNode = null;
      Element eElement = null;
      ArrayList<String> arrayList  = null;
      try {
        System.out.print("ABH ========== > "+path);
	File fXmlFile = new File(path+"/resources/xml/db.xml");
       
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile); 
	doc.getDocumentElement().normalize(); 
        
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        
        // Get Database provider Name
        NodeList selectedNodeList = doc.getElementsByTagName("selected-provider");    
        nNode = selectedNodeList.item(0);
        eElement = (Element) nNode;
        System.out.println("driver : " + eElement.getElementsByTagName("provider-name").item(0).getTextContent());
        String selectedprovider = eElement.getElementsByTagName("provider-name").item(0).getTextContent();
        System.out.println("-------------selectedprovider---------------"+selectedprovider);
        
        
	NodeList nList = doc.getElementsByTagName("provider");
 
	System.out.println("----------------------------");
 
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		nNode = nList.item(temp);
 
		
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			eElement = (Element) nNode;
                        if(selectedprovider.trim().equalsIgnoreCase(eElement.getAttribute("id"))){   
                            
                            arrayList = new ArrayList();
//                            System.out.println("Staff id : " + eElement.getAttribute("id"));
//                            System.out.println("driver : " + eElement.getElementsByTagName("driver").item(0).getTextContent());
//                            System.out.println("connectionString : " + eElement.getElementsByTagName("connectionString").item(0).getTextContent());
//                            System.out.println("user Name : " + eElement.getElementsByTagName("user").item(0).getTextContent());
//                            System.out.println("pawssword : " + eElement.getElementsByTagName("password").item(0).getTextContent());
//                            
                            arrayList.add(eElement.getAttribute("id"));
                            arrayList.add(eElement.getElementsByTagName("driver").item(0).getTextContent());
                            arrayList.add(eElement.getElementsByTagName("connectionString").item(0).getTextContent());
                            arrayList.add(eElement.getElementsByTagName("user").item(0).getTextContent());
                            arrayList.add(eElement.getElementsByTagName("password").item(0).getTextContent());
                            
                            
                        }
                        
		}
	}
        
    } catch (Exception e) {
	e.printStackTrace();
    }
    return  arrayList;
  }
 
}