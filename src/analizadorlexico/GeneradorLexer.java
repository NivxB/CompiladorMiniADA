/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.io.File;

/**
 *
 * @author Carlos
 */
public class GeneradorLexer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File hola = new File("./src/analizadorlexico/Lexer.flex");
        jflex.Main.generate(hola);
    }
    
}
