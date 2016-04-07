/*
 * To change this license header, choose License Headers in Project Properties.
 
Trabalho de Sistemas Operacionais - Graduação em Sistemas Para Internet 4º semestre
        1º semestre de 2016 - UFSM

Característica da aplicação:

Desenvolver  um  Web  Server  que implemente  o  método  GET  (http)e  que  disponibilize  uma única 
página de boas vindas com as seguintes características:

-A  página  de  boas  vindas  deverá  conter  informações  meteorológicas  locais  como: 
temperatura,    umidade,    precipitação,    e    outras    características    que    se    julgar 
conveniente;

-Deverá  registrar  para  cada  acesso,o endereço IP de  origem  da  requisiçãoem  um arquivo de log;

-Deverá  ser  implementado  com  múltiplas  threads,  onde  cada  requisição  ao  servidor será 
tratada em uma thread exclusiva;

-A cada 30 segundos as informações meteorológicas serão atualizadas, de forma que a página ficará
bloqueada enquanto isto ocorre;

-As informações meteorológicas podem ser obtidas de forma randômicaou a partir de um 
webservice   (INMET,   INPE,   etc..).   Para obter   a   nota   integral   neste   item,   a 
implementação deverá ser com webservice.

 */
package webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author // Diovane Soligo // Alisson Trindade
 */
public class WebServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(80);
 //WebServer       
        while(true){
            Socket s = ss.accept();
            
            
            String remoteIp = s.getInetAddress().getHostAddress();
            //todo tramamento da , reconhecer requisição
            
            /*tratar tudo em thrad*/
            
            byte[]  b =new byte[1024]; //cria 1204 possições    ||| byte array
            
            int num = s.getInputStream().read(b);//se retornar -1 ja tava vazio se mais leu tudo que precisava
            
            if (num>0){
            System.out.println(new String(b, 0, num, "ISO-8859-1"));
            System.out.println("IP CONEXÃO EXTERNA: " +remoteIp +"\n************************************\n\n");
            }
            
            //tratar requisição
            
            s.getOutputStream().write("<html><head></head><body>OLÁ MUNDO!!!</body></html>".getBytes("ISO-8859-1"));
            
            s.close();
            
            
        }
    }
    
}
