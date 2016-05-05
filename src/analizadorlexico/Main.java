/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import analizadorlexico.SemanticAnalysis.SemanticAnalysis;
import analizadorlexico.TypeCheck.VoidType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Main {
    public static void main(String[] args) throws Exception {

        // TODO code application logic here
       Lexer lexer = null;
            try {
                lexer = new Lexer(new FileReader("./ADA.txt"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
//mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
            parser p = new parser(lexer);
            p.parse();
            SemanticAnalysis semantic = new SemanticAnalysis(p.FINALOBJECT);
            mapper.writeValue(new File("./AST.json"), p.FINALOBJECT);
            mapper.writeValue(new File("./TABLE.json"), semantic.getRoot());
       
        
        //for (Symbol sim : symbolList){
            //System.out.println(sim.toString());
        //}
                
        
    }
}
