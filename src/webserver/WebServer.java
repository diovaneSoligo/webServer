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
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebServer {
    
/*******************************************************************************/    
    static class Buffer {
        Queue<Tempo> queue = new LinkedList<Tempo>();
    }
    
/*******************************************************************************/
    static class XML implements Runnable {
        Buffer buffer;
        
        XML(Buffer buffer) {
            this.buffer = buffer;
        }
        
        @Override
        public void run() {
           do{
               System.out.println("\n\nVai atualizar o XML ...\n");
               BuscaDadosXML XML = new BuscaDadosXML();
               Tempo T = XML.T;
               
               synchronized (buffer) { 
                   
                            buffer.queue.add(T); // Adiciona os dados sobre a previsão do tempo
                            buffer.notify();
                            System.out.println("Atualizou as informações do tempo !!!\n");
                        
                            try {
                                System.out.println("Vai dormir por 10 segundos ... \n");
                                Thread.sleep(10000);//dorme por 30 segundos, depois realiza a tualização do XML
                                }
                            catch (InterruptedException e) {
                                }
                            System.out.println("acordou...\n");
                        buffer.queue.remove();
                        buffer.notify();
                    }
           }while(true);
        }
    }
    
/*******************************************************************************/    
    static class Pagina implements Runnable{
        Buffer buffer;
        Socket s;
        
        Pagina(Buffer buffer, Socket s) {
            this.buffer = buffer;
            this.s = s;
        }
        
        @Override
        public void run() {
            System.out.println("está em class Pagina...\n");
            
            try {
                
                String remoteIp = s.getInetAddress().getHostAddress();
                byte[]  b =new byte[1024]; //cria 1204 possições    ||| byte array
                int num = s.getInputStream().read(b);//se retornar -1 ja tava vazio se mais leu tudo que precisava
                if (num>0){
                        
                        Date data=new Date();
                        SimpleDateFormat x= new SimpleDateFormat("dd/MM/yyyy || HH:mm:ss");
                        String log = "-> "+x.format(data)+" || IP CONEXÃO EXTERNA: "+remoteIp;
                        
                        System.out.println(log+"\n");
                        
                        //gravar no log, chamar a classe GravaLog passando a string log
                      }
                
                    Tempo T = buffer.queue.peek();
                    
                    if(T == null){//se o buffer tiver vazio, entao espera pq ta atualizando
                        try {
                            System.out.println("está esperando informações serem atualizadas...\n");
                                buffer.wait(); // Espera de as informações serem colocadas no Buffer
                                
                            }
                        catch (InterruptedException e) {
                            }
                        System.out.println("SAIU DE  esperando informações serem atualizadas...\n");
                    }else{
                        pagina pag = new pagina(T ,s);
                    }
                    
            s.close();
            
            } catch (IOException ex) {
                Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
/*******************************************************************************/
    public static void main(String[] args) throws IOException {
        
        Buffer buffer = new Buffer();
        
        //abrir o aruivo TXT aqui, caso nao exista, deve-se criar o mesmo
        
        XML xml = new XML(buffer);
        
        new Thread(xml).start(); // Thread XML criada
        ServerSocket ss = new ServerSocket(80); //porta padrão do html ...
        
        while(true){ //para cada requisição será criada uma nova thread
            Socket s = ss.accept();
            Pagina pag = new Pagina(buffer, s/*txtLog*/ );//passar o arquivo txt aberto para a class Pagina
            new Thread(pag).start(); // Thread pagina criada 
        }

    }
    
}
