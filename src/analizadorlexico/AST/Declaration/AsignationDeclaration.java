/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Declaration;

import analizadorlexico.AST.Expression.Expression;
import analizadorlexico.AST.Primary.Primary;

/**
 *
 * @author Kevin Barahona
 */
public class AsignationDeclaration extends Declaration{
    private Declaration SimpleDeclaration;
    private Expression value;

    public AsignationDeclaration(Declaration SimpleDeclaration, Expression value) {
        this.SimpleDeclaration = SimpleDeclaration;
        this.value = value;
    }

    public Declaration getSimpleDeclaration() {
        return SimpleDeclaration;
    }

    public void setSimpleDeclaration(Declaration SimpleDeclaration) {
        this.SimpleDeclaration = SimpleDeclaration;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }


    
    
}
