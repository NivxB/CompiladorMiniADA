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
public class SequenceStatement extends Statement{
    Statement ThisStatement;
    Statement NextSequenceStatement;

    public SequenceStatement(Statement ThisStatement, Statement NextSequenceStatement) {
        this.ThisStatement = ThisStatement;
        this.NextSequenceStatement = NextSequenceStatement;
    }

    public Statement getThisStatement() {
        return ThisStatement;
    }

    public void setThisStatement(Statement ThisStatement) {
        this.ThisStatement = ThisStatement;
    }

    public Statement getNextSequenceStatement() {
        return NextSequenceStatement;
    }

    public void setNextSequenceStatement(SequenceStatement NextSequenceStatement) {
        this.NextSequenceStatement = NextSequenceStatement;
    }
    
    
    
}
