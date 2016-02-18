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


/**
 *
 * @author Carlos
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Reader read = new BufferedReader(new FileReader("./ADA.txt"));
        Lexer lexer = new Lexer(read);
        List<Symbol> symbolList = new ArrayList<Symbol>();
        while(true){
            Symbol token = lexer.yylex();
            if (token.isUnknown()){
                System.err.print(token);
                //break;
            }else{
                symbolList.add(token);
            }
            if(token.isEOF()){
                break;
            }
            System.out.print(token);
        }
        
        //for (Symbol sim : symbolList){
            //System.out.println(sim.toString());
        //}
                
        
    }
}
