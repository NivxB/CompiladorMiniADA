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
public class MultExpression extends MathematicalExpression {
    MathematicalExpression Exp1;
    MathematicalExpression Exp2;
    String Operator;

    public MultExpression(MathematicalExpression Exp1, MathematicalExpression Exp2, String Operator) {
        this.Exp1 = Exp1;
        this.Exp2 = Exp2;
        this.Operator = Operator;
    }

    public MathematicalExpression getExp1() {
        return Exp1;
    }

    public void setExp1(MathematicalExpression Exp1) {
        this.Exp1 = Exp1;
    }

    public MathematicalExpression getExp2() {
        return Exp2;
    }

    public void setExp2(MathematicalExpression Exp2) {
        this.Exp2 = Exp2;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String Operator) {
        this.Operator = Operator;
    }
    
    
    
}
