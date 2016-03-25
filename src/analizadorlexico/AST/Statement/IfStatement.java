/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Statement;

import analizadorlexico.AST.Expression.Condition;

/**
 *
 * @author kbarahona
 */
public class IfStatement extends Statement{
    Condition Con;
    Statement Stat;
    IfStatement ElsIf;


    

    
     public IfStatement(Condition Con, Statement Stat, Statement ElsIf) {
        this.Con = Con;
        this.Stat = Stat;
        this.ElsIf = (IfStatement) ElsIf;
    }

    public IfStatement(Statement Stat) {
        this.Stat = Stat;
    }

    public IfStatement() {
    }
    
    
}
