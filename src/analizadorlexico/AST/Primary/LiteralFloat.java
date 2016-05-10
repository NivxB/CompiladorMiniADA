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
public class LiteralFloat extends Primary{
    private float value;

    public LiteralFloat(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
    @Override
    public String getStringType() {
        return "FLOAT";
    }

    
}
