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
public class ConditionExpression {

    Expression Exp1;
    Expression Exp2;
    String ConditionOperator;

    public ConditionExpression(Expression Exp1, Expression Exp2, String ConditionOperator) {
        this.Exp1 = Exp1;
        this.Exp2 = Exp2;
        this.ConditionOperator = ConditionOperator;
    }

    public Expression getExp1() {
        return Exp1;
    }

    public void setExp1(Expression Exp1) {
        this.Exp1 = Exp1;
    }

    public Expression getExp2() {
        return Exp2;
    }

    public void setExp2(Expression Exp2) {
        this.Exp2 = Exp2;
    }

    public String getConditionOperator() {
        return ConditionOperator;
    }

    public void setConditionOperator(String ConditionOperator) {
        this.ConditionOperator = ConditionOperator;
    }

}
