
package webserver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DOM{
    public static void main(String args[]){
        
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //contrutor de documento
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            //fazer o passe do documento XML para  doc
            Document doc = builder.parse("http://developers.agenciaideias.com.br/tempo/xml/santa%20maria-RS");
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
        }

}

}

