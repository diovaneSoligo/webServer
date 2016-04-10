/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            
            
            //Busca os dados do clima via XML
            BuscaDadosXML XML = new BuscaDadosXML();
            Tempo T = XML.T;
            
            //mostra pagina web no navegador
            pagina pag = new pagina(T ,s);
            
            
            s.close();
        }//fecha WHILE
        
    }
    
}
