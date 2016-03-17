/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Carlos
 */
public class Main {
    public static void main(String[] args) {
        // TODO code application logic here
        Lexer lexer = null;
        try {
            lexer = new Lexer(new FileReader("./ADA.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        parser p = new parser(lexer);
       
        
        //for (Symbol sim : symbolList){
            //System.out.println(sim.toString());
        //}
                
        
    }
}
