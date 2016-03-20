/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Expression;

import analizadorlexico.AST.Primary.*;

/**
 *
 * @author kbarahona
 */
public class PrimaryMathExpression extends MathematicalExpression{
    Primary value;

    public PrimaryMathExpression(String ID, ListPrimary Params) {
        value = new FunctionCall(ID,Params);
    }
    
    public PrimaryMathExpression(Integer val){
        value = new LiteralInt(val);
    }
    
    public PrimaryMathExpression(String id){
        value = new ID(id);
    }
    
}
