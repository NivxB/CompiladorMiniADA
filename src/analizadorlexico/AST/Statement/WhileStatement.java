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
public class WhileStatement extends Statement{
    Condition Con;
    Statement Stat;

  
 
    
    public WhileStatement(Condition Con, Statement Stat) {
        this.Con = Con;
        this.Stat = Stat;
    }

    public Condition getCon() {
        return Con;
    }

    public void setCon(Condition Con) {
        this.Con = Con;
    }

    public Statement getStat() {
        return Stat;
    }

    public void setStat(Statement Stat) {
        this.Stat = Stat;
    }
    
    
}
