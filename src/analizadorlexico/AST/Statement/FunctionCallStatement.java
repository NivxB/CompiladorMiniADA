/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Statement;

import analizadorlexico.AST.Primary.FunctionCall;
import analizadorlexico.AST.Primary.Primary;

/**
 *
 * @author kbarahona
 */
public class FunctionCallStatement extends Statement{
    private FunctionCall call;

    public FunctionCallStatement(Primary call) {
        this.call = (FunctionCall) call;
    }
   
    
}
