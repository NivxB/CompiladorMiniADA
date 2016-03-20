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
public class ForStatement extends Statement{
   AsignationStatement Asig;
   Statement Stat;
   Expression Exp;

    public ForStatement(AsignationStatement Asig, Statement Stat, Expression Exp) {
        this.Asig = Asig;
        this.Stat = Stat;
        this.Exp = Exp;
    }

    public AsignationStatement getAsig() {
        return Asig;
    }

    public void setAsig(AsignationStatement Asig) {
        this.Asig = Asig;
    }

    public Statement getStat() {
        return Stat;
    }

    public void setStat(Statement Stat) {
        this.Stat = Stat;
    }

    public Expression getExp() {
        return Exp;
    }

    public void setExp(Expression Exp) {
        this.Exp = Exp;
    }
   
   
   
   
}
