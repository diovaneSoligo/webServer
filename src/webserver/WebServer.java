/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author aluno
 */
public class WebServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
//pega a hora e data no acesso
        
        Date data=new Date();
        SimpleDateFormat x= new SimpleDateFormat("dd/MM/yyyy || HH:mm:ss");
        
        Tempo T = new Tempo();
        /***************************************************************************/
        //Importação dos dados XML --INICIO--
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //contrutor de documento
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            //fazer o passe do documento XML para  doc
            Document doc = builder.parse("http://developers.agenciaideias.com.br/tempo/xml/santa%20maria-RS");
            
            //recebe o documento do passe e pega o elemento pela tag
            NodeList ListaTempo = doc.getElementsByTagName("agora");
            
            //retorna quantos elementos tem na lista tempo
        //    int tamanholista = ListaTempo.getLength();
            
            //percorre
        //    for (int i = 0; i < tamanholista; i++) {//ver se precisa realmente deste for
                
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
            //    }
                                    }
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Importação dos dados XML --FIM--
        /**************************************************************************/
        
        
        
        ServerSocket ss = new ServerSocket(80);//porta padrão do html ...
        //WebServer       
        while(true){
            Socket s = ss.accept();
            String remoteIp = s.getInetAddress().getHostAddress();
            
            //todo tramamento da , reconhecer requisição
            
            /*tratar tudo em thrad*/
            
            byte[]  b =new byte[1024]; //cria 1204 possições    ||| byte array
            
            int num = s.getInputStream().read(b);//se retornar -1 ja tava vazio se mais leu tudo que precisava
            
            if (num>0){
                        //System.out.println(new String(b, 0, num, "ISO-8859-1"));
                        System.out.println("-> "+x.format(data)+" || IP CONEXÃO EXTERNA: "+remoteIp+"\n");
                      }
            
            //tratar requisição
            
            //mostra pagina web no navegador
            pagina pag = new pagina(T ,s);
            
            
            s.close();
        }//fecha WHILE
        
    }
    
}
