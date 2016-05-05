/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.TypeCheck;

/**
 *
 * @author Kevin Barahona
 */
public class DoubleType extends Type{

    public DoubleType() {
        super("DOUBLE");
    }
    
    @Override
    public boolean compare(Type value) {
        return (value instanceof DoubleType && value.TYPE.equals("DOUBLE"));
    }
}
