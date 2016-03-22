/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Statement;

import analizadorlexico.AST.Expression.Expression;

/**
 *
 * @author kbarahona
 */
public class ReturnStatement extends Statement {
    private Expression retVal;
    public ReturnStatement(Statement NextStatement) {
        super(NextStatement);
    }

    public ReturnStatement(Expression retVal, Statement NextStatement) {
        super(NextStatement);
        this.retVal = retVal;
    }
    
    
}
