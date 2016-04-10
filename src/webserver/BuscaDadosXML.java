/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author diovaneS
 */
public class BuscaDadosXML {
    
    Tempo T = new Tempo();
    
    BuscaDadosXML() {
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //contrutor de documento
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            //fazer o passe do documento XML para  doc
            Document doc = builder.parse("http://developers.agenciaideias.com.br/tempo/xml/santa%20maria-RS");
            
            //recebe o documento do passe e pega o elemento pela tag
            NodeList ListaTempo = doc.getElementsByTagName("agora");
            
                //pra cada elemento encontrado é feito um nó
                Node agora = ListaTempo.item(0);
                
                //verifica se o nó agora é do tipo elemento, se for do tipo element é pq tem coisa dentro dele
                if(agora.getNodeType() == Node.ELEMENT_NODE){
                        //faz a conversão para elemento
                        Element Agora = (Element) agora;//converte o nó agora para um elemento agora
                        
                        String AGORA = Agora.getAttribute("agora");
                        
                        //Pega todos os nós filhos do elemento agora e coloca numa lista
                        NodeList agoraFilhos = Agora.getChildNodes();
                        
                        //pega o tamanho da lista agoraFilhos
                        int taf = agoraFilhos.getLength();
                        
                        for (int j = 0; j < taf; j++) {//percorre os filhos de agora
                                    Node noFilho = agoraFilhos.item(j);
                                    
                                    //verifica se o noFilho é do tipo Element
                                    if(noFilho.getNodeType() == Node.ELEMENT_NODE){
                                                Element elementoFilho = (Element) noFilho;
                                                
                                                switch(elementoFilho.getTagName()){
                                                
                                                    case "data_hora":
                                                        T.setData_hora(elementoFilho.getTextContent());
                                                        break;
                                                        
                                                    case "descricao":
                                                        T.setDescricao(elementoFilho.getTextContent());
                                                        break;
                                                        
                                                    case "temperatura":
                                                        T.setTemperatura(elementoFilho.getTextContent());
                                                        break;
                                                        
                                                    case "umidade":
                                                        T.setUmidade(elementoFilho.getTextContent());
                                                        break;
                                                        
                                                    case "visibilidade":
                                                        T.setVisibilidade(elementoFilho.getTextContent());
                                                        break;
                                                        
                                                    case "vento_velocidade":
                                                        T.setVento_velocidade(elementoFilho.getTextContent());
                                                        break;
                                                        
                                                    case "vento_direcao":
                                                        T.setVento_direcao(elementoFilho.getTextContent());
                                                        break;
                                                      
                                                    case "pressao":
                                                        T.setPressao(elementoFilho.getTextContent());
                                                        break;
                                                        
                                                    case "pressao_status":
                                                        T.setPressao_status(elementoFilho.getTextContent());
                                                        break;
                                                        
                                                    case "nascer_do_sol":
                                                        T.setNascer_do_sol(elementoFilho.getTextContent());
                                                        break;
                                                        
                                                    case "por_do_sol":
                                                        T.setPor_do_sol(elementoFilho.getTextContent());
                                                        break;
                                                        
                                                    case "imagem":
                                                        T.setImagem(elementoFilho.getTextContent());
                                                        break;
                                                        
                                                    }
                                                }
                                            }
                                    }
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
