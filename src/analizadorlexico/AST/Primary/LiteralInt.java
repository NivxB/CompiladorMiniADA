/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Primary;

/**
 *
 * @author kbarahona
 */
public class LiteralInt extends Primary{
    private int value;

    public LiteralInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    @Override
    public String getStringType() {
        return "INTEGER";
    }

    
}
