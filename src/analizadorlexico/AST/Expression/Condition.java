/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Expression;

/**
 *
 * @author kbarahona
 */
public class Condition {
    private Expression Exp;

    public Condition(Expression Exp) {
        this.Exp = Exp;
    }

    public Expression getExp() {
        return Exp;
    }

    public void setExp(Expression Exp) {
        this.Exp = Exp;
    }
    
}
