/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.statics;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



/**
 *
 * @author hieup
 */
public class Convertor {

    
    private Convertor(){
        
    }
    
    public static String getValueFromXMLTag(String xmlString, String tag) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(xmlString));
        Document doc = builder.parse(src);
        return doc.getElementsByTagName(tag).item(0).getTextContent();
    }
    
}
