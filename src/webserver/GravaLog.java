/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author diovaneS
 */
public class GravaLog {
    
    
    GravaLog(String DadosLog) {
        
        
        try{
            System.out.println("Dentro de GravaLog pra gravar no TXT...");
            System.out.println("Vai gravar no TXT - > "+DadosLog);
            
            File Arquivo = new File ("arquivoLog.txt");
            
            FileWriter fileWriter = new FileWriter(Arquivo, true);
            BufferedWriter escrever = new BufferedWriter(fileWriter);
            escrever.write(DadosLog);
            escrever.newLine();
            
            escrever.close();
            fileWriter.close();
            
            
        }
        catch(Exception e){
            System.out.println("Erro ao escrever no arquivo");
        }
    }
    

    
}
