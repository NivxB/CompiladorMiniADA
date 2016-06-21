/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import analizadorlexico.FinalCode.FinalCode;
import analizadorlexico.FinalCode.MemoryControl;
import analizadorlexico.IntermediateCode.IntermediateCode;
import analizadorlexico.IntermediateCode.Operation;
import analizadorlexico.SemanticAnalysis.SemanticAnalysis;
import analizadorlexico.TypeCheck.VoidType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Main {

    public static void main(String[] args) throws Exception {
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        // TODO code application logic here
        Lexer lexer = null;
        try {
            lexer = new Lexer(new FileReader("./SenoYas.adb"));
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
        IntermediateCode intermediateCode = new IntermediateCode(p.FINALOBJECT, semantic);
        if (!SemanticAnalysis.hasError) {
            System.out.println("NO ERROR");
            MemoryControl.initMessage();
            intermediateCode.generate();
            FileWriter writer1 = new FileWriter("./intermediateCode.BP");
            for (Operation str : intermediateCode.getCodeOperations()) {
                writer1.write(str.getStringValue() + "\n");
            }
            writer1.close();
            //System.out.println(intermediateCode.getStringRepresentation());
            FinalCode finalCode = new FinalCode(semantic, intermediateCode);
            finalCode.initialGeneration();
            FileWriter writer = new FileWriter("./finalCode.s");
            for (String str : finalCode.getFinalCode()) {
                writer.write(str);
            }
            writer.close();
        } else {
            System.err.println("Program has errors\nterminating compile");
        }

        mapper.writeValue(new File("./AST.json"), p.FINALOBJECT);
        //mapper.writeValue(new File("./TABLE.json"), semantic.getRoot());

        //for (Symbol sim : symbolList){
        //System.out.println(sim.toString());
        //}
    }
}
