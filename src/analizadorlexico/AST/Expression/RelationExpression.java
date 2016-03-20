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
public class RelationExpression extends Expression {

    Expression Exp1;
    Expression Exp2;
    String RelationOperator;

    public RelationExpression(Expression Exp1, Expression Exp2, String RelationOperator) {
        this.Exp1 = Exp1;
        this.Exp2 = Exp2;
        this.RelationOperator = RelationOperator;
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

    public String getRelationOperator() {
        return RelationOperator;
    }

    public void setRelationOperator(String RelationOperator) {
        this.RelationOperator = RelationOperator;
    }
    
    
}
