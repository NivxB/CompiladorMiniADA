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
        String params[] = new String[5];
        params[0] = "-destdir";
        params[1] = "src/analizadorlexico/";
        params[2] = "-parser";
        params[3] = "parser";
        params[4] = "src/analizadorlexico/Gramar.cup";
        try {
            java_cup.Main.main(params);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
