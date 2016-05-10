/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Expression;

import analizadorlexico.TypeCheck.Type;

/**
 *
 * @author kbarahona
 */
public abstract class Expression {
    private Type Type;

   

    public void setType(Type Type) {
        this.Type = Type;
    }
    
    public Expression(){
        
    }
}
