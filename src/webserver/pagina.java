/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author diovaneS
 */
public class pagina {

        Date data=new Date();
        SimpleDateFormat x= new SimpleDateFormat("dd/MM/yyyy");
    
    pagina(Tempo T,Socket s) throws IOException {
            s.getOutputStream().write("<html><head><title>Previsão Tempo</title><link rel=\"shortcut icon\" href='".getBytes("ISO-8859-1"));
            s.getOutputStream().write(T.getImagem().getBytes("ISO-8859-1"));
            s.getOutputStream().write("'/></head><body style='background-color:rgb(148, 194, 245);text-align: center;border:2px rgb(35, 129, 234) solid;margin-left:50px;margin-right:50px;margin-top:20px;margin-botton:20px'>".getBytes("ISO-8859-1"));
            
            s.getOutputStream().write("<br><h4>Previsão do tempo para Santa Maria em ".getBytes("ISO-8859-1"));
            s.getOutputStream().write(x.format(data).getBytes("ISO-8859-1"));
            
            s.getOutputStream().write("</h4><img style='width: 135px;' src='".getBytes("ISO-8859-1"));
            s.getOutputStream().write(T.getImagem().getBytes("ISO-8859-1"));
            s.getOutputStream().write("'><h6>XML fonte atualizado em: ".getBytes("ISO-8859-1"));
            s.getOutputStream().write(T.getData_hora().getBytes("ISO-8859-1"));
            s.getOutputStream().write("</h6>Estado: ".getBytes("ISO-8859-1"));
            s.getOutputStream().write(T.getDescricao().getBytes("ISO-8859-1"));
            s.getOutputStream().write("<br>Temperatura:  ".getBytes("ISO-8859-1"));
            s.getOutputStream().write(T.getTemperatura().getBytes("ISO-8859-1"));
            s.getOutputStream().write(" °C <br> Visibilidade: ".getBytes("ISO-8859-1"));
            s.getOutputStream().write(T.getVisibilidade().getBytes("ISO-8859-1"));
            s.getOutputStream().write("<br>Nascer do Sol: ".getBytes("ISO-8859-1"));
            s.getOutputStream().write(T.getNascer_do_sol().getBytes("ISO-8859-1"));
            s.getOutputStream().write("<br>Por do Sol: ".getBytes("ISO-8859-1"));
            s.getOutputStream().write(T.getPor_do_sol().getBytes("ISO-8859-1"));
            s.getOutputStream().write("<br>Pressão do ar: ".getBytes("ISO-8859-1"));
            s.getOutputStream().write(T.getPressao().getBytes("ISO-8859-1"));
            s.getOutputStream().write(" || Status: ".getBytes("ISO-8859-1"));
            s.getOutputStream().write(T.getPressao_status().getBytes("ISO-8859-1"));
            s.getOutputStream().write("<br>Velocidade do vento: ".getBytes("ISO-8859-1"));
            s.getOutputStream().write(T.getVento_velocidade().getBytes("ISO-8859-1"));
            s.getOutputStream().write("<br>Umidade do ar: ".getBytes("ISO-8859-1"));
            s.getOutputStream().write(T.getUmidade().getBytes("ISO-8859-1"));
            s.getOutputStream().write("<br><br><br>".getBytes("ISO-8859-1"));
            
            
            s.getOutputStream().write("</body></html>".getBytes("ISO-8859-1"));
    }
    
    
}
