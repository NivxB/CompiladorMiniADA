/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Statement;

/**
 *
 * @author kbarahona
 */
public abstract class Statement {
    
    protected Statement NextStatement;

    public Statement(Statement NextStatement) {
        this.NextStatement = NextStatement;
    }

    
}