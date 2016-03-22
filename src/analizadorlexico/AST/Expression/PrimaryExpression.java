/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Expression;

import analizadorlexico.AST.Primary.*;

/**
 *
 * @author kbarahona
 */
public class PrimaryExpression extends Expression{
    Primary value;

    public PrimaryExpression(Primary v) {
        value = v;
    }    
}
