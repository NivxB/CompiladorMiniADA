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
public class AsignationStatement extends Statement{
    private String ID;
    private Expression Exp;
    
    public AsignationStatement(String ID, Expression Exp) {
        this.ID = ID;
        this.Exp = Exp;
    }
    

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Expression getExp() {
        return Exp;
    }

    public void setExp(Expression Exp) {
        this.Exp = Exp;
    }
    
}
