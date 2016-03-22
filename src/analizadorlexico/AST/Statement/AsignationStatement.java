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

    public AsignationStatement(String ID, Expression Exp,Statement Next) {
        super(Next);
        this.ID = ID;
        this.Exp = Exp;
    }
    
    public AsignationStatement(String ID, Expression Exp) {
        super(null);
        this.ID = ID;
        this.Exp = Exp;
    }
    
    public AsignationStatement(Statement Actual, Statement Next){
        this(((AsignationStatement)Actual).ID,((AsignationStatement)Actual).Exp,Next);        
    };
    
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
