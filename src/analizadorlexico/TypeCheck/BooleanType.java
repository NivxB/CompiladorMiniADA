/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.TypeCheck;

/**
 *
 * @author Carlos
 */
public class BooleanType extends Type{

    public BooleanType(String TYPE) {
        super("BOOLEAN");
    }

    @Override
    public boolean compare(Type value) {
        return (value instanceof BooleanType && value.TYPE.equals("BOOLEAN"));
    }
    
}
